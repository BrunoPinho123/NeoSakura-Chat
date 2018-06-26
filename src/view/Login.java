package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.UsuarioDao;
import model.Usuario;

@WebServlet("/login")
public class Login extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter out = response.getWriter();
		
		Usuario user = new Usuario();
		String login = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		
		user.setLogin(login);
		user.setSenha(senha);
		
		
		HttpSession session = request.getSession();
		
		
			try {
				user = new UsuarioDao().existe(user);
			} catch (ClassNotFoundException e) {
				response.setContentType("text/html");
		        PrintWriter out = response.getWriter();
		        out.println("Erro login <a href='login.html'>Entrar</a>");
		        out.close();
			}
			
			System.out.println(user);
			if(user == null)	{
				response.setContentType("text/html");
		        PrintWriter out = response.getWriter();
		        out.println("Erro login <a href='login.html'>Entrar</a>");
		        out.close();
			}
			
			System.err.println("passei");
				
			

			session.setAttribute("login_user", user.getLogin());
			session.setAttribute("user_name", user.getNickname());
			
			response.sendRedirect("chat");
	}
}
