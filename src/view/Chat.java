package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.FlistDao;
import controller.GrupoDao;
import controller.MensagemDao;
import model.Grupo;
import model.Mensagem;
import model.Usuario;

@WebServlet("/chat")
public class Chat extends HttpServlet{

	private static final long serialVersionUID = 1L;


	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		 
		HttpSession session = request.getSession(false); 

		
		if(session != null){
			String usuario = (String) session.getAttribute("user_name");
			
			String msg = request.getParameter("msg");
			
			if(msg != null && msg.length() > 0){
				Mensagem mensagem = new Mensagem();
				mensagem.setConteudo(request.getParameter("msg"));
				mensagem.setGid(4);
				mensagem.setUser_name(usuario);
				
				MensagemDao mdao = null;
				try {
					mdao = new MensagemDao();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mdao.adiciona(mensagem);
			}
			
		}else{
			response.sendRedirect("login.html");
		}
		
		
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\" />" );
		out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		out.println("<title>Neochat</title>");
	    out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
	     +"<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"css/main.css\" />"
	 +"</head>"
	 +"<body>"
	 + "<div class=\"row\">"
	 +"<div class=\"col col-menu\"> <div class=\"item\">"
         
         +"<br>"
         +"<img style=\"width: 70px\" src=\"img/logo2.0.png\">"
            
         +"<br>"
         +"<p style=\"font-size: 14; color: aqua; text-align: center\">" + (String) session.getAttribute("user_name") +"</p>"
         +"<br>"
        
         +"<hr style=\"height:1px; border:none; color:rgb(255, 255, 255); background-color:rgb(255, 255, 255); margin-top: 0px; margin-bottom: 0px; width: 80%; margin: 0 auto;\"/>"
         +"<br>"
         +"<p class=\"link\">"
             +"<a href=\"user_update.html\">Config. de Úsuario</a>"
         +"</p>" 
        
         +"<p class=\"link\">"
                     +"<a href=\"gp_update.html\">Config. de grupos</a>"
         +"</p>"
         +"<br>"
         +"<br>"
         +"<hr style=\"height:1px; border:none; color:rgb(255, 255, 255); background-color:rgb(255, 255, 255); margin-top: 0px; margin-bottom: 0px; width: 80%; margin: 0 auto;\"/>"
         +"<br>"
         +"<h4><p class=\"pg\">Grupos:</p></h4>");
        
	    String login = (String) session.getAttribute("login_user");
	    List<Grupo> grupos = new ArrayList<Grupo>();
	    try {
			grupos = new GrupoDao().mostrar(login);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    for(Grupo group : grupos) {
	    	out.println("<p class=\"pg\">" + group.getNome() + "</p>");
	    }
                        
     out.println("</div></div>"
    		 +"<div class=\"col col-content\"><div class=\"item\"><br><h2 style=\"color: rgb(191, 123, 255); padding-left: 1.2em\">Mensagens</h2>"
             +"<div id=\"scroll\">"
                 +"<ul>");
     	
     
	    Grupo grupo = new Grupo();
	    grupo.setGid(4);
	    
	    List<Mensagem> mensagens = new ArrayList<Mensagem>();
		
			try {
				mensagens = new MensagemDao().getLista(grupo);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	         		for(Mensagem mensag : mensagens){
	         			out.println("<li><h5>" + mensag.getUser_name() +"</h5> &nbsp" + mensag.getConteudo() + "</li>");
	         		}
	         		
	 out.println("</ul>"
			 +"</div>"
	         +"</div></div>"
	         +"<div class=\"col col-sidebar\"><div class=\"item\">"
	            
	             +"<h2><p class=\"pc\">Contatos</p></h2>");
	 	
	 		List<Usuario> usuarios = new ArrayList<Usuario>();
	 	
	 		try {
				 usuarios = new FlistDao().mostrar(login);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		
	 		for(Usuario usr : usuarios) {
	 			out.println("<p class=\"pc\">" + usr.getNickname() + "</p>");
	 		}
	            
	        out.println(" </div></div>"
	     +"</div>"
	     +"<div class=\"row\">" 
	     	+ "<form action=\"chat\" method=\"POST\">"
	         +"<div class=\"col\">"
	    
	         +"<input class=\"footer\" type=\"text\" name=\"msg\"maxlength=\"500\">"
	         +"</div>"
	         +"<div class=\"col\">"
	             +"<input class=\"footer-button\" type=\"submit\" value=\"Send\">"
	         +"</div>"
	         +"</form>"
	     +"</div>"
	 +"</body>"
	 +"</html>");

		}
}
