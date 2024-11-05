package com.fran.jdbc.utilidades;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class JdbcUtils {
	
	static Connection con;
	static Statement statement;
	static PreparedStatement stmt;
	static CallableStatement cstmt;
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
	
	/**
	 * Ejecuta un preparedStament con los parametros separados por comas.
	 * Debe haber tantos parametros como interrogaciones tenga la Select.
	 * @param sql La select con las interrogaciones en ella
	 * @param parametros Cualquier tipo de objeto
	 * @return ResultSet con los datos de la consulta
	 */
	public static ResultSet devolverPreparedStatement(String sql, Object...parametros) {
		return devolverPreparedStatement(sql, Arrays.asList(parametros));	
	}
	
	/**
	 * Ejecuta un preparedStament con los parametros en una lista.
	 * Debe haber tantos parametros como interrogaciones tenga la Select.
	 * @param sql La select con las interrogaciones en ella
	 * @param parametros Cualquier tipo de objeto
	 * @return ResultSet con los datos de la consulta
	 */
	public static ResultSet devolverPreparedStatement(String sql, List<Object> parametros) {
		if(countMatches(sql,'?')!=parametros.size())
			return null;
		try {
			stmt = con.prepareStatement(sql);
			for(int i=0;i<parametros.size();i++) {
				stmt.setObject(i+1, parametros.get(i));				
			}
			return stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static Object ejecutarCallableStatement(String metodo,int tipoDevuelto, Object... parametros) {
		if(countMatches(metodo,'?')!= parametros.length)
			return null;
		try {
			cstmt = con.prepareCall(
					 "{call " + metodo + "}");
			cstmt.registerOutParameter(1, tipoDevuelto);
			for(int i=1;i<=parametros.length;i++) {
				cstmt.setObject(i, parametros[i-1]);
			}
			cstmt.execute();
			return cstmt.getObject(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet resultSetCallableStatement(String metodo,Object... parametros) {
		if(countMatches(metodo,'?')!= parametros.length)
			return null;
		try {
			cstmt = con.prepareCall("{call " + metodo + "}");			
			for(int i=1;i<=parametros.length;i++) {
				cstmt.setObject(i, parametros[i-1]);
			}			
			return cstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	

	/**
	 * Método auxiliar para contar el número de ocurrencias de un caracter que le
	 * pasamos como parámetro
	 * @param cadena	Cadena donde buscar
	 * @param caracterBuscado Caracter buscado
	 * @return Número de veces que aparece el caracter en la cadena
	 */
	private static int countMatches(String cadena, char caracterBuscado) {
		return (int)cadena.chars().filter(e->e==caracterBuscado).count();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
