package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class FlistDao {
	private Connection connection;
	
	public FlistDao() throws ClassNotFoundException{
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Usuario user, Usuario contato){
		String sql = "INSERT INTO Friends_list"
					+ " (login, contato)"
					+ " Values(?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, user.getLogin());
			stmt.setString(2, contato.getLogin());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void remove(Usuario user, Usuario contato){
		String sql = "DELETE FROM Friends_list"
					+ "WHERE login = ? AND contato = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, user.getLogin());
			stmt.setString(2, contato.getLogin());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delList(Usuario user){
		String sql = "DELETE FROM Friends_list"
				+ " WHERE login = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, user.getLogin());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Usuario buscar(String login) {
		String sql = "SELECT * FROM Usuario"
				+ " WHERE login = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, login);
			
			ResultSet rs = stmt.executeQuery();
			
			while	(rs.next())	{
				Usuario user = new Usuario();
				user.setLogin(rs.getString("login"));
				user.setNickname(rs.getString("user_name"));
				return user;
			}
			
			
			
			
			rs.close();
			stmt.close();
			return null;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Usuario> mostrar(String login){
		String sql = "SELECT contato FROM Friends_list" 
					+ " WHERE login = ? ";
		try {
			List<Usuario> users = new ArrayList<Usuario>();
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, login);
			
			ResultSet rs = stmt.executeQuery();
			
			while	(rs.next())	{
				//	criando	o	objeto	Usuario.
				
				
				users.add(buscar(rs.getString("contato")));
				
			}
			
			rs.close();
			stmt.close();
			return	users;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
