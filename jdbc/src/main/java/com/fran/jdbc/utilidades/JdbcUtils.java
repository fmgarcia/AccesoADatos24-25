package com.fran.jdbc.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
	
	static Connection con = null;
	static Statement statement = null;
	static ResultSet rs = null;
	
	public static boolean conexionBbdd(String url, String usuario, String password) {
		try {
			con = DriverManager.getConnection(url, usuario, password);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void cerrarBbdd() {
		try {
			if (con!= null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
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
	 * @return
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
