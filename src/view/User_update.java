package view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.UsuarioDao;
import model.Usuario;

/**
 * Servlet implementation class user_update
 */
@WebServlet("/atualizarUsuario")
public class User_update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); 

		
		if(session != null){
			
			UsuarioDao dao = null;
			try {
				dao = new UsuarioDao();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String nickname = request.getParameter("nickname");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			
			
			if(nickname.length() > 0 && login.length() > 0 && senha.length() > 0){
				Usuario user = new Usuario();
				user.setLogin(login);
				user.setSenha(senha);
				user.setNickname(nickname);
				
				dao.atualiza(user);
			}
			
			String dlogin = request.getParameter("dlogin");
			String dsenha = request.getParameter("dsenha");
			
			
			if(dlogin.length() > 0 && dsenha.length() > 0){
				Usuario user = new Usuario();
				user.setLogin(dlogin);
				user.setSenha(dsenha);
				
				try {
					if(dao.existe(user) != null)
						dao.remove(user);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			
			response.sendRedirect("chat");
		}else{
			response.sendRedirect("login.html");
		}
	}

}
