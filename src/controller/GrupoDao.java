package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Grupo;

public class GrupoDao {
	
	private Connection connection;
	
	public GrupoDao() throws ClassNotFoundException{
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void criar(String nome){
		String sql = "INSERT INTO Grupo" 
					+ "(nome)" + "VALUES(?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, nome);
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void alterar(Grupo grupo){
		String sql = "UPDATE Grupo SET nome = ?"
					+ "WHERE gid = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, grupo.getNome());
			stmt.setInt(2, grupo.getGid());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void apagar(int id){
		String sql = "DELETE FROM Grupo" 
					+ "WHERE gid = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Grupo> mostrar(String login){
		String sql = "SELECT * FROM Grupo" 
					+ " WHERE gid in "
					+ "(SELECT gid FROM Membros_list"
					+ " WHERE login = ?)";
		try {
			List<Grupo> grupos = new ArrayList<Grupo>();
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, login);
			
			ResultSet rs = stmt.executeQuery();
			
			while	(rs.next())	{
				//	criando	o	objeto	Grupo.
				Grupo grupo = new Grupo();
				grupo.setGid(rs.getInt("gid"));
				grupo.setNome(rs.getString("nome"));
				
				//	adicionando	o	objeto	a 	lista
				grupos.add(grupo);
				
			}
			
			rs.close();
			stmt.close();
			return	grupos;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
