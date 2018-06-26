package controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public	Connection	getConnection() throws ClassNotFoundException	{
		try	{
			Class.forName("org.postgresql.Driver");
			return	DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/Trabalho", "postgres", "123");
		}	
		catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
