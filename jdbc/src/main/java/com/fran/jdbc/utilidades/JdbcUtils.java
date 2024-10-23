package com.fran.jdbc.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
	
	static Connection con;
	static Statement statement;
	static ResultSet rs;
	
	public static boolean conexionBbdd(String url, String usuario, String password) {
		try {
			con = DriverManager.getConnection(url, usuario, password);
			statement = con.createStatement();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void cerrarBbdd() {
		try {
			if (con!= null && statement!=null && !con.isClosed() && !statement.isClosed()) {
				statement.close();
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Realiza consultas en la base de datos
	 * Operaciones SELECT 
	 * @param sql
	 * @return ResultSet de resultados de la Select
	 */
	public static ResultSet devolverQuery(String sql) {
		try {
			return statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Realiza actualizaciones en la base de datos
	 * Operaciones Insert, Update, Delete o cualquier otra del DDL 
	 * @param sql
	 * @return número de registros afectados
	 */
	public static int ejecutarDML(String sql) {
		try {
			return statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
