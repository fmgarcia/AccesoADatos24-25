package com.fran.jdbc.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
	
	static String url;
	static String usuario;
	static String password;
	static Connection con = null;
	static Statement statement = null;
	static ResultSet rs = null;
	
	public static boolean conexionBbdd(String url_con, String usuario_con, String password_con) {
		url = url_con;
		usuario = usuario_con;
		password = password_con;
		try {
			con = DriverManager.getConnection(url, usuario, password);
			statement = con.createStatement();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static void cerrarBbdd() {
		try {
			if (rs!=null && con!= null && !rs.isClosed() && !con.isClosed()) {
				statement.close();
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	
	
	
	
	
	
	
	
}
