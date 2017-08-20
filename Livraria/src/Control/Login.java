package Control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import Banco.UsuarioDao;
import Model.Carrinho;
import Model.Livro;
import Model.Usuario;

@WebServlet({"/Login", "/Email", "/Email2", "/carrinho.html"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request,response);
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			//Pegar a URL que foi executada
			String url = request.getServletPath();
			if(url.equalsIgnoreCase("/Login")){
				login(request,response);
			}else if(url.equalsIgnoreCase("/Email")){
				email(request,response);
			}else if(url.equalsIgnoreCase("/Email2")){
				email2(request,response);
			}else if(url.equalsIgnoreCase("/carrinho.html")){
				sessaoCarrinho(request,response);
			}else{
				response.sendRedirect("/");
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession();
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			UsuarioDao ud = new UsuarioDao();
			Usuario user = new Usuario();
			Carrinho carrinho = new Carrinho();
			user = ud.buscarUsuario(email, senha);
			if(ud.checkLogin(email, senha)){
					if(ud.checkAutenticador(email, senha)){
						session.setAttribute("logou", user);
						session.setAttribute("carrinho", carrinho);
						request.setAttribute("msg", "<div class='alert alert-dismissible alert-success'><button type='button' class='close' data-dismiss='alert'>&times;</button><strong>Logado com Sucesso!</strong></div>");
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}else{
						
						request.setAttribute("msg", "<div class='alert alert-dismissible alert-danger'><button type='button' class='close' data-dismiss='alert'>&times;</button><strong>Usuario Precisa ser Validado! Olhe seu email e click no link contido nele</strong></div>");
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
			}else{
				request.setAttribute("msg", "<div class='alert alert-dismissible alert-danger'><button type='button' class='close' data-dismiss='alert'>&times;</button><strong>Usuario Invalido</strong></div>");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("index.jsp");
		}
	}
	
	public void email(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			UsuarioDao ud = new UsuarioDao();
			Usuario user = new Usuario();
			user = ud.ultimoUsuario();
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("rafaelimaferreira@gmail.com", "eusoulindo 969410323"));
			email.setSSLOnConnect(true);
			email.setFrom("rafaelimaferreira@gmail.com");
			email.setSubject("Livraria");
			email.setMsg("Link para autenticação: http://localhost:8080/Livraria/Autenticar?aut=" + user.getId() +"&email=" + user.getEmail());
			email.addTo(user.getEmail());
			email.send();
			request.setAttribute("msg", "Cadastrado com Sucesso Autentique seu usuario com o link em seu email!");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}catch (EmailException ex){
			ex.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
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

	public void email2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try{
			@SuppressWarnings("unused")
			UsuarioDao ud = new UsuarioDao();
			Usuario user = new Usuario();
			Carrinho carrinho = new Carrinho();
			carrinho =(Carrinho) session.getAttribute("carrinho");
			user = carrinho.getUser();
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("rafaelimaferreira@gmail.com", "eusoulindo 969410323"));
			email.setSSLOnConnect(true);
			email.setFrom("rafaelimaferreira@gmail.com");
			email.setSubject("Livraria");
			email.setMsg("Compra Realizada com Sucesso !");
			email.addTo(user.getEmail());
			email.send();
			request.setAttribute("msg", "<div class='alert alert-dismissible alert-success'><button type='button' class='close' data-dismiss='alert'>&times;</button><strong>Compra realizada com sucesso! Seu email recebeu a confirmação da compra</strong></div>");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}catch (EmailException ex){
			ex.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}


