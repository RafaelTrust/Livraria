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
import Model.Usuario;

/**
 * Servlet implementation class ControleCarrinho
 */
@WebServlet({ "/ControleUsuario", "/Pegar"})
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
		}
