package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Grupo;
import model.Mensagem;

public class MensagemDao {
	
	private	Connection	connection;
	public	MensagemDao() throws ClassNotFoundException{
		this.connection	= new ConnectionFactory().getConnection();
	}
	
	
	public void adiciona(Mensagem msg){
		
		String sql = "INSERT INTO Mensagens" + 
					"(gid, user_name, conteudo)" + 
					" VALUES(?, ?, ?)";
	
		try	{
			//	prepared	statement	para	inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			//	seta	os	valores
			stmt.setInt(1, msg.getGid());
			stmt.setString(2, msg.getUser_name());
			stmt.setString(3, msg.getConteudo());
			
			//	executa
			stmt.execute();
			stmt.close();
		}	
		catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Mensagem> getLista(Grupo grupo){
		try {
			String sql = "SELECT gid, user_name, conteudo FROM Mensagens WHERE gid = ?";
			List<Mensagem> mensagens = new ArrayList<Mensagem>();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, grupo.getGid());
			
			ResultSet rs = stmt.executeQuery();
			
			while	(rs.next())	{
				//	criando	o	objeto	Contato.
				Mensagem msg = new Mensagem();
				msg.setGid(rs.getInt("gid"));
				msg.setUser_name(rs.getString("user_name"));
				msg.setConteudo(rs.getString("conteudo"));
				
				//	adicionando	o	objeto	à	lista
				mensagens.add(msg);
				
			}
			
			rs.close();
			stmt.close();
			return	mensagens;
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
} 