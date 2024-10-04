package com.fran.xmljson.utilidades;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fran.xmljson.entidades.People;
import com.fran.xmljson.entidades.Tareas;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {
	
	/**
	 * Ejemplo de librería json-simple con un fichero profesor.json que se encuentra
	 * en los apuntes del tema
	 * 
	 * @param cadenaCompleta
	 */
	public static void leerJsonDesdeFichero(String cadenaCompleta) {
		Object obj;
		try {
			// parseado el fichero "profesor.json"
			obj = new JSONParser().parse(new FileReader(cadenaCompleta));
			// casteando obj a JSONObject
			JSONObject jo = (JSONObject) obj;
			// cogiendo el nombre y el apellido
			String nombre = (String) jo.get("nombre");
			String apellido = (String) jo.get("apellido");
			System.out.println(nombre);
			System.out.println(apellido);
			// cogiendo la edad como long
			long edad = (long) jo.get("edad");
			System.out.println(edad);
			
			// cogiendo direccion
			Map domicilio = ((Map) jo.get("domicilio"));
			// iterando direccion Map
			domicilio.forEach((k,v) -> System.out.println("Key: " + k + ": Value: " + v));
			
			// cogiendo números de teléfonos
			JSONArray ja = (JSONArray) jo.get("numerosTelefonos");
			// iterando números de teléfonos
			ja.stream().forEach(e->System.out.println(e));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void leerLuke(String cadenaCompleta) {
		Object obj;
		try {	
			// Obtener datos
			obj = new JSONParser().parse(new FileReader(cadenaCompleta));
			JSONObject jo = (JSONObject) obj;
			String nombre = (String) jo.get("name");
			String altura = (String) jo.get("height");
			JSONArray peliculas = (JSONArray) jo.get("films");
			
			// mostrar datos
			System.out.println(nombre + " tiene una altura de " + altura);
			System.out.println("Ha participado en las siguientes películas:");
			peliculas.forEach(e->System.out.println(e));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static List<Tareas> devolverTareasInternet(String url) {
		
		Object obj;
		List<Tareas> resultado = new ArrayList<Tareas>();
		try {	
			// cogiendo el array como elemento principal
			JSONArray ja = (JSONArray) new JSONParser().parse(InternetUtils.readUrl(url));
				
			ja.forEach(e->{
				JSONObject elementoObjeto = (JSONObject) e;
				resultado.add(new Tareas(
						(long) elementoObjeto.get("userId"),
						(long) elementoObjeto.get("id"),
						(String) elementoObjeto.get("title"),
						(boolean) elementoObjeto.get("completed")						
						));
			});
			
			return resultado;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}
	
	
	public static <T> List<T> devolverListaInternet(String url) {
		
		Object obj;
		List<T> resultado = new ArrayList<T>();
		try {	
			// cogiendo el array como elemento principal
			JSONArray ja = (JSONArray) new JSONParser().parse(InternetUtils.readUrl(url));
				
			ja.forEach(e->{
				JSONObject elementoObjeto = (JSONObject) e;
				insertarElemento(resultado, elementoObjeto);
			});
			
			return resultado;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}

	public static <T> void insertarElemento(List<T> resultado, JSONObject elementoObjeto) {
		resultado.add((T)new Tareas((long) elementoObjeto.get("userId"),
				(long) elementoObjeto.get("id"),
				(String) elementoObjeto.get("title"),
				(boolean) elementoObjeto.get("completed")			
				));
	}
	
	public static long devolverCuentaRegistros(String url, String count) {
		
		try {	
			JSONObject ja = (JSONObject) new JSONParser().parse(InternetUtils.readUrl(url));										
			return (long)ja.get(count);			
		} catch (Exception e) {
			e.printStackTrace();
		} 		
		return -1;
	}
	
	
	public static People leerStarWars(String url) {
		return new Gson().fromJson(InternetUtils.readUrl(url), People.class);		
	}
	
	public static <T> T leerGenerico(String url, Class<T> clase) {
		return new Gson().fromJson(InternetUtils.readUrl(url), clase);		
	}
	
	/**
	 * Creamos un String con un json a partir de un objeto
	 * @param <T> tipo del objeto
	 * @param object nombre de la variable
	 * @return String con el Json
	 */
	public static <T> String crearJson(T object) {
		return new Gson().toJson(object);
	}
	
	/**
	 * Creamos un String con un json a partir de un objeto en un formato amigable
	 * @param <T> tipo del objeto
	 * @param object nombre de la variable
	 * @return String con el Json
	 */
	public static <T> String crearJsonPretty(T object) {
		return new GsonBuilder()
				.setPrettyPrinting()
				.create()
				.toJson(object);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
