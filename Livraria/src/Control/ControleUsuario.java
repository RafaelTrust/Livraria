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
import Model.Endereco;
import Model.Livro;
import Model.Usuario;

@WebServlet({ "/ControleUsuario", "/cadastrar.html", "/buscar.html", "/Produtos", "/Autenticar", "/Deslogar", "/index.html", "/produtos.html", "/login.html", "/cadastro.html"})
public class ControleUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControleUsuario() {
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
			if(url.equalsIgnoreCase("/cadastrar.html")){
				cadastrar(request,response);
			
			}else if(url.equalsIgnoreCase("/buscar.html")){
				buscar(request,response);
			
			}else if(url.equalsIgnoreCase("/Autenticar")){
				autenticar(request,response);
			
			}else if(url.equalsIgnoreCase("/Deslogar")){
				deslogar(request,response);
			
			}else if(url.equalsIgnoreCase("/index.html")){
				request.getRequestDispatcher("index.jsp").forward(request, response);
			
			}else if(url.equalsIgnoreCase("/produtos.html")){
				request.getRequestDispatcher("produtos.jsp").forward(request, response);
			
			}else if(url.equalsIgnoreCase("/login.html")){
				request.getRequestDispatcher("login.jsp").forward(request, response);
			
			}else if(url.equalsIgnoreCase("/cadastro.html")){
				request.getRequestDispatcher("cadastro.jsp").forward(request, response);
				
			}else{
				response.sendRedirect("/");
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String nome = request.getParameter("nome");
		 String email = request.getParameter("email");
		 String senha = request.getParameter("senha");
		 String cpf = request.getParameter("cpf");
		 //Endereco
		 String estado = request.getParameter("estado");
		 String cidade = request.getParameter("cidade");
		 String end1 = request.getParameter("end1");
		 String complemento = request.getParameter("complemento");
		 String bairro = request.getParameter("bairro");
		 String cep = request.getParameter("cep");
		 //--------
		 String celular = request.getParameter("celular");
		 String telefone = request.getParameter("telefone");
		 
		 Endereco endereco = new Endereco();
		 endereco.setEstado(estado);
		 endereco.setCidade(cidade);
		 endereco.setEndereco(end1);
		 endereco.setComplemento(complemento);
		 endereco.setBairro(bairro);
		 endereco.setCep(cep);
		 
		 try{
			 UsuarioDao ud = new UsuarioDao();
			 boolean teste = ud.cadastrarEndereco(endereco);
			 int idend = ud.returIdend(endereco);
			 if(idend != 0){
				 if(teste){
					 endereco.setId(ud.returIdend(endereco));
					 Usuario user = new Usuario();
					 user.setNome(nome);
					 user.setEmail(email);
					 user.setSenha(senha);
					 user.setCpf(cpf);
					 user.setEndereco(endereco);
					 user.setCelular(celular);
					 user.setTelefone(telefone);
					 user.setAutenticador(0);
					 if(ud.cadastrar(user)){
						request.getRequestDispatcher("Email").forward(request, response);
					 }else{
						request.setAttribute("msg", "Falha ao Cadastrar");
						request.getRequestDispatcher("cadastro.jsp").forward(request, response);
					 }
				 }else{
					request.setAttribute("msg", "Falha ao Cadastrar");
					request.getRequestDispatcher("cadastro.jsp").forward(request, response);
				 }
			 }else{
				request.setAttribute("msg", "Falha ao Cadastrar");
				request.getRequestDispatcher("cadastro.jsp").forward(request, response);
			 }
		 }catch(Exception e){
				e.printStackTrace();
				response.sendRedirect("cadastro.jsp");
			} 
	}
	
	protected void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String nome = request.getParameter("nome");
			//Manter no campo nome o valor buscado
			request.setAttribute("nome", nome);
			
			UsuarioDao ud = new UsuarioDao();
			List<Livro> lista = ud.buscar("%"+nome+"%");
			if(lista.size() == 0){
			    request.setAttribute("msg", "Nenhum Livro Encontrado!!!");
			}
			
		request.setAttribute("lista", lista);
			
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg","Erro na busca");
		
		}finally{
			request.getRequestDispatcher("produtos.jsp").forward(request,response);
		}
	}
	
	protected void deslogar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		session.removeAttribute("logou");
		request.setAttribute("msg", "Usuario deslogado!");
		response.sendRedirect("index.jsp");
	}
	
	protected void autenticar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int aut = Integer.parseInt(request.getParameter("aut"));
		System.out.println("Autenticador: " + request.getParameter("aut"));
		String email = request.getParameter("email");
		System.out.println("Email: " + request.getParameter("email"));
		UsuarioDao ud = new UsuarioDao();
		try {
			if(ud.autenticar(aut, email)){
				ud.autenticado(aut);
				response.sendRedirect("index.jsp");
				request.setAttribute("msg", "<div class='alert alert-dismissible alert-success'><button type='button' class='close' data-dismiss='alert'>&times;</button><strong>Validado com Sucesso!</strong></div>");
			}else{
				response.sendRedirect("index.jsp");
				request.setAttribute("msg", "<div class='alert alert-dismissible alert-danger'><button type='button' class='close' data-dismiss='alert'>&times;</button><strong>Usuario n�o Encontrado!</strong></div>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
