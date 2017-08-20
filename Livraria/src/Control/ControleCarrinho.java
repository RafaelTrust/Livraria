package Control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Banco.UsuarioDao;
import Model.Carrinho;
import Model.Livro;
import Model.Usuario;

/**
 * Servlet implementation class ControleCarrinho
 */
@WebServlet({ "/ControleUsuario", "/Pegar", "/Gravar", "/Apagar", "/Carrinho", "/carrinho.html"})
public class ControleCarrinho extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControleCarrinho() {
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request,response);
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("logou") != null){
			request.setAttribute("log", "<a href='Deslogar'>Deslogar</a>");
		}else{
			request.setAttribute("log", "<a href='login.jsp'>Logar</a>");
		}
		
		try{
			String url = request.getServletPath();
			if(url.equalsIgnoreCase("/Pegar")){
				pegarCarrinho(request,response);
			
			}else if(url.equalsIgnoreCase("/Gravar")){
				gravaCarrinho(request,response);
				
			}else if(url.equalsIgnoreCase("/Apagar")){
				removerCarrinho(request,response);	
				
			}else if(url.equalsIgnoreCase("/Carrinho")){
				logar(request,response);
			
			}else if(url.equalsIgnoreCase("/carrinho.html")){
				sessaoCarrinho(request,response);
				
			}else{
				response.sendRedirect("/");
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
		protected void pegarCarrinho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			
			if(session.getAttribute("logou") != null){
				Usuario user = new Usuario();
				UsuarioDao ud = new UsuarioDao();
				Carrinho carrinho = new Carrinho();
				user = (Usuario)session.getAttribute("logou");
				int id = user.getId();
				
				List<Integer> idlivros;
				try {
					idlivros = ud.pegarIdlivro(id);
					carrinho = ud.pegarCarrinho(idlivros);
					carrinho.setUser(user);
				} catch (Exception e) {
					e.printStackTrace();
				}finally{				
					session.setAttribute("meucarrinho", carrinho);
				}
			}else{
				request.setAttribute("msg", "Usuario não logado!");
				response.sendRedirect("login.jsp");
			}
				
			}
		protected void gravaCarrinho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			Carrinho carrinho = new Carrinho();
			UsuarioDao ud = new UsuarioDao();
			Livro livro = new Livro();
			carrinho = (Carrinho)session.getAttribute("carrinho");
			int it = carrinho.qntdLivros(carrinho.getLivros());
			for (int i = 0; i < it ; i++){
				String id = Integer.toString(carrinho.getLivros().get(i).getId());
				livro = carrinho.getLivros().get(i);
				livro.setQntd(Integer.parseInt(request.getParameter("qtd"+id)));
				carrinho.updateItemCarrinho(livro);
			}
			
			
			try {
				if(ud.carrinho(carrinho)){
					response.sendRedirect("Email2");
				}else{
					request.setAttribute("msg", "Falha ao relizar a compra");
					response.sendRedirect("index.html");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		protected void removerCarrinho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			int id = Integer.parseInt(request.getParameter("remover"));
			UsuarioDao ud = new UsuarioDao();
			Carrinho carrinho = new Carrinho();
			Livro livro = new Livro();
			try {
				livro = ud.comprarLivro(id);
				carrinho = (Carrinho)session.getAttribute("carrinho");
				int index = (carrinho.getLivros().indexOf(livro)) * -1;
				carrinho.removeItemCarrinho(index);
				session.setAttribute("carrinho", carrinho);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				request.setAttribute("msg", "<div class='alert alert-dismissible alert-success'><button type='button' class='close' data-dismiss='alert'>&times;</button><strong>Livro removido com sucesso!</strong></div>");
				response.sendRedirect("carrinho.html");
			}
		}
		
		protected void logar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			int id = Integer.parseInt(request.getParameter("livro"));
			
			if(session.getAttribute("logou") != null){
				response.sendRedirect("carrinho.html?livro=" + id);
			}else{
				request.setAttribute("msg", "Usuario não logado!");
				response.sendRedirect("login.jsp");
			}
		}
		
		protected void sessaoCarrinho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			System.out.println("Livro: " + request.getParameter("livro"));
			Carrinho carrinho = new Carrinho();
			if(request.getParameter("livro") != null){
				int id = Integer.parseInt(request.getParameter("livro"));
				UsuarioDao ud = new UsuarioDao();
				Livro livro = new Livro();
				Usuario user = new Usuario();
				user = (Usuario)session.getAttribute("logou");
				try {
					livro = ud.comprarLivro(id);
					if(livro != null){
					carrinho = (Carrinho)session.getAttribute("carrinho");
					carrinho.setItemCarrinho(livro);
					carrinho.setUser(user);
					List<Livro> livros = carrinho.getLivros();
					
					session.setAttribute("livros", livros);
					session.setAttribute("carrinho", carrinho);
					}else{
						request.setAttribute("msg", "Livro não encontrado!");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					request.getRequestDispatcher("carrinho.jsp").forward(request,response);
				}
			}else{
				carrinho = (Carrinho)session.getAttribute("carrinho");
				session.setAttribute("livros", carrinho.getLivros());
				request.setAttribute("msg", "<div class='alert alert-dismissible alert-success'><button type='button' class='close' data-dismiss='alert'>&times;</button><strong>Livro removido com sucesso!</strong></div>");
				request.getRequestDispatcher("carrinho.jsp").forward(request, response);
			}
			
			
		}
	}
