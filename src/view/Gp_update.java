package view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.GrupoDao;
import model.Grupo;

/**
 * Servlet implementation class Gp_update
 */
@WebServlet("/alterarGrupo")
public class Gp_update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); 

		
		if(session != null){
			
			GrupoDao dao = null;
			try {
				dao = new GrupoDao();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String newgp = request.getParameter("newgp");
			String nomegp = request.getParameter("nomegp");
			String alteredgp = request.getParameter("alteredgp");
			int delgp = Integer.parseInt(request.getParameter("delgp"));
			if(newgp != null && newgp.length() > 0)
				dao.criar(newgp);
			
			if(nomegp != null && alteredgp != null && nomegp.length() > 0 && alteredgp.length() > 0){
				Grupo grupo = new Grupo();
				grupo.setGid(4);
				grupo.setNome(alteredgp);
				dao.alterar(grupo);
			}
			
			if(delgp > 0)
				dao.apagar(delgp);
			
			response.sendRedirect("chat");
		}else{
			response.sendRedirect("login.html");
		}
	}
}
	
	