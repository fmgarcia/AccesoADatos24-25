package com.fran.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fran.jdbc.entidades.Evento;
import com.fran.jdbc.utilidades.JdbcUtils;
import com.github.javafaker.Faker;



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
	
	// Ejemplo de SQL injection. Introduce '1000 or 1=1' (sin las comillas)
	public static void ejemplo3() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca el Id a buscar");
		String id = sc.nextLine();
		try {
			ResultSet rs = JdbcUtils.devolverQuery("Select * from evento where id="+ id);
			while (rs.next()) {
				System.out.println(rs.getObject(1));
			}
			sc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Ejemplo Select con preparedStatement
	// Con esto soluciono sql injection
	public static void ejemplo4(int id) {
		try {
			Connection con = DriverManager.getConnection(url, usuario, password);
			PreparedStatement stmt = con.prepareStatement("select * from evento where id=?");
			stmt.setObject(1, id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
			System.out.println(rs.getInt(1)+" "+rs.getString(2));
			} 
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void insertarPersonas() {
		String sql = """
				INSERT INTO personas(nombre,email) 
				VALUES
				('Persona 1', 'email1@gmail.com'),
				('Persona 2', 'email2@gmail.com'),
				('Persona 3', 'email3@gmail.com'),
				('Persona 4', 'email4@gmail.com'),
				('Persona 5', 'email5@gmail.com'),
				('Persona 6', 'email6@gmail.com')
				""";
		JdbcUtils.conexionBbdd(url, usuario, password);
		JdbcUtils.ejecutarDML(sql);
		JdbcUtils.cerrarBbdd();
	}
	
	public static void insertarFaker()
	{
		Faker faker = new Faker();
		String sql = "INSERT INTO personas(nombre,email) VALUES ";
		for(int i=0;i<500;i++) {
			sql += "('" + faker.name().fullName().replace("'", "") + "','" + faker.internet().emailAddress() + "'),";
		}
		sql += "('" + faker.name().fullName().replace("'", "") + "','" + faker.internet().emailAddress() + "')";
		JdbcUtils.conexionBbdd(url, usuario, password);
		JdbcUtils.ejecutarDML(sql);
		JdbcUtils.cerrarBbdd();
		System.out.println("Proceso finalizado correctamente");
	}
	
	public static void ejemploCallable1() {
		try {
			Connection con = DriverManager.getConnection(url, usuario, password);
			CallableStatement cStmt = con.prepareCall(
					 "{call cantidadpersonas(?)}");
					 cStmt.registerOutParameter(1, Types.INTEGER);
					 cStmt.setString(1, "%Bayer%");
					 cStmt.execute();
					 int resultado = cStmt.getInt(1);
					 System.out.println("Resultado: " + resultado); 			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void llamarFunctionMedianteStatement() {
		int cantidad_personas = 0;
		try {
			JdbcUtils.conexionBbdd(url, usuario, password);
			ResultSet rs = JdbcUtils.devolverQuery("Select * from cantidadpersonas('%Bayer%')");
			while (rs.next()) {
				cantidad_personas = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtils.cerrarBbdd();
		System.out.println("La cantidad de personas es: " + cantidad_personas);
	}
	
	public static void llamarTotalPersonas() {
		int cantidad_personas = 0;
		try {
			JdbcUtils.conexionBbdd(url, usuario, password);
			ResultSet rs = JdbcUtils.devolverQuery("Select * from devolver_cantidad_personas()");
			while (rs.next()) {
				cantidad_personas = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtils.cerrarBbdd();
		System.out.println("La cantidad de personas es: " + cantidad_personas);
	}
	
	public static void llamarFunctionRegistro() {
		try {
			Connection con = DriverManager.getConnection(url, usuario, password);
			CallableStatement cstmt = con.prepareCall("{call listar_personas_out()}");
			ResultSet rs = cstmt.executeQuery();
			while (rs.next()) {
				System.out.println("El id: " + rs.getObject(1) + " se llama " + rs.getObject(2));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void llamarFunctionRegistroJbdcUtils() {
		try {
			JdbcUtils.conexionBbdd(url, usuario, password);
			ResultSet rs = JdbcUtils.resultSetCallableStatement("listar_personas_out()");
			while (rs.next()) {
				System.out.println("El id: " + rs.getObject(1) + " se llama " + rs.getObject(2));
			}
			JdbcUtils.cerrarBbdd();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void llamarProcedureGenerico() {
		JdbcUtils.conexionBbdd(url, usuario, password);
		int numero = (int)JdbcUtils.ejecutarCallableStatement("cantidadpersonas(?)",Types.INTEGER,"%Bayer%");
		System.out.println("La cantidad es: " + numero);
		JdbcUtils.cerrarBbdd();
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
		/*
		try {
			if (JdbcUtils.conexionBbdd(url, usuario, password)) {
				//System.out.println("Abro y cierro correctamente");
				ResultSet rs = JdbcUtils.devolverQuery("Select * from evento");
				while (rs.next()) {
					System.out.println(rs.getObject(1));
				}
				System.out.println("Registros modificados : " 
				+ JdbcUtils.ejecutarDML("Insert into evento(nombre,descripcion,precio,fecha) "
						+ "values('Evento4','descripción 4',10,'2024-10-23')"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		/*
		JdbcUtils.conexionBbdd(url, usuario, password);
		ejemplo3();			
		JdbcUtils.cerrarBbdd();*/
		//ejemplo4(3);
		/*
		try {
			JdbcUtils.conexionBbdd(url, usuario, password);
			//ResultSet rs = JdbcUtils.devolverPreparedStatement("select * from evento where id=?", 1);
			List<Object> parametros = new ArrayList<Object>();
			parametros.add(1);	
			ResultSet rs = JdbcUtils.devolverPreparedStatement("select * from evento where id=?", parametros);
			while (rs.next()) {
				System.out.println(rs.getObject(1) + " " + rs.getObject(2));
			}
			JdbcUtils.cerrarBbdd();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//insertarPersonas();
		//insertarFaker();
		//ejemploCallable1();
		//llamarFunctionMedianteStatement();
		//llamarTotalPersonas();
		//llamarFunctionRegistro();
		//llamarProcedureGenerico();
		llamarFunctionRegistroJbdcUtils();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
