package com.fran.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fran.jdbc.entidades.Evento;
import com.fran.jdbc.utilidades.JdbcUtils;

public class App {

	static String url = "jdbc:postgresql://localhost:5432/eventos";
	static String usuario = "postgres";
	static String password = "postgres";
	
	public static void ejemplo1() {
		try {
			Connection con = DriverManager.getConnection(url, usuario, password);
			Statement statement = con.createStatement();
			String sentenciaSQL = "SELECT nombre, descripcion FROM evento ORDER BY fecha";
			ResultSet rs = statement.executeQuery(sentenciaSQL);
			System.out.println("Nombre" + "\t" + "Descripción");
			System.out.println("-----------------------------------------");
			while (rs.next()) {
				System.out.println(rs.getString(1) + "\t " + rs.getString(2));
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static List<Evento> ejemplo2() {
		List<Evento> eventos = new ArrayList<Evento>();
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, usuario, password);
			Statement statement = con.createStatement();
			String sentenciaSQL = "SELECT * FROM evento";
			rs = statement.executeQuery(sentenciaSQL);			
			while (rs.next()) {
				eventos.add(new Evento(rs.getInt(1),
						rs.getString(2), 
						rs.getString(3), 
						rs.getDouble(4),
						rs.getDate(5).toLocalDate()));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs!=null && con!= null && !rs.isClosed() && !con.isClosed()) {
					rs.close();
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return eventos!=null?eventos:null;
	}

	public static void main(String[] args) {
		//ejemplo1();
		/*
		 * ejemplo2().stream() .filter(e->e.getPrecio()<13)
		 * .forEach(e->System.out.println(e));
		 */
//		List<Evento> eventos = ejemplo2(); // Tengo todos los eventos cargados en una lista
//		eventos.stream()
//		.filter(e->e.getPrecio()<13)
//		.forEach(e->System.out.println(e));
		if (JdbcUtils.conexionBbdd(url, usuario, password)) {
			JdbcUtils.cerrarBbdd();
			System.out.println("Abro y cierro correctamente");
		}
			
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
