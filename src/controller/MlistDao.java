package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Grupo;
import model.Usuario;

public class MlistDao {
	private Connection connection;
	
	MlistDao() throws ClassNotFoundException{
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Grupo grupo, Usuario user){
		String sql = "INSERT INTO Membros_list"
					+ "(gid, login)"
					+ "Values(?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, grupo.getGid());
			stmt.setString(2, user.getLogin());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void remove(Grupo grupo, Usuario user){
		String sql = "DELETE FROM Membros_list"
					+ "WHERE gid = ? AND login = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, grupo.getGid());
			stmt.setString(2, user.getLogin());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
