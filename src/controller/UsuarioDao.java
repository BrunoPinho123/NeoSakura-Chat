package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;

public class UsuarioDao {
	
	private Connection connection;
	
	public UsuarioDao() throws ClassNotFoundException{
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Usuario user){
		String sql = "INSERT INTO Usuario" +
					"(login, user_name, senha, status)" +
					"VALUES(?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getNickname());
			stmt.setString(3, user.getSenha());
			stmt.setBoolean(4, user.isStatus());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		}
	}
	
	public void remove(Usuario user) throws ClassNotFoundException{
		FlistDao dao = new FlistDao();
		dao.delList(user);
		
		String sql = "DELETE" + " FROM UsuarioWHERE login = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, user.getLogin());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void atualiza(Usuario user){
		String sql = "UPDATE Usuario SET login = ?, user_name = ?, senha = ?" +
					"WHERE login = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getNickname());
			stmt.setString(3, user.getSenha());
			stmt.setString(4, user.getLogin());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Usuario existe(Usuario user) throws ClassNotFoundException{
		Connection cn = new ConnectionFactory().getConnection();
		
		String sql = "SELECT * FROM Usuario WHERE login = ? AND senha = ?";
		
		try {
			PreparedStatement stmt = cn.prepareStatement(sql);
			
			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getSenha());
		
			ResultSet rs = stmt.executeQuery();
			
			
			int records = 0;
			
			while (rs.next()) {
				user.setNickname(rs.getString("user_name"));
				records += 1;
			}

			if (records <= 0) {
				return null;
			}
			
			while(rs.next()){
				Usuario aux = new Usuario();
				aux.setNickname(rs.getString("user_name"));
				user.setNickname(aux.getNickname());
				user.setLogin(rs.getString("login"));
				System.out.println(rs.getString("user_name"));
			}
			
			rs.close();
			stmt.close();
			cn.close();
			return user;
			
		} catch (SQLException e) {
			return null;			
		}
	}
	
	public void mudaStatus(Usuario user){
		String sql = "UPDATE Usuario SET status = ?" 
					+ "WHERE login = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setBoolean(1, user.isStatus());
			stmt.setString(2, user.getLogin());
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
