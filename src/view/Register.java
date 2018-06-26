package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.UsuarioDao;
import model.Usuario;

@WebServlet("/register")
public class Register extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter	out	=	response.getWriter();
		
		Usuario user = new Usuario();
		
		String login = request.getParameter("usuario");
		String nick = request.getParameter("nickname");
		String senha = request.getParameter("senha");
		
		user.setLogin(login);
		user.setNickname(nick);
		user.setSenha(senha);
		
		try {
			UsuarioDao dao = new UsuarioDao();
			dao.adiciona(user);
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		out.println("<html>");
		out.println("<body>");
		out.println("Contato	"	+	user.getNickname()	+
										"	adicionado	com	sucesso");								
		out.println("</body>");
		out.println("</html>");
	}
}
