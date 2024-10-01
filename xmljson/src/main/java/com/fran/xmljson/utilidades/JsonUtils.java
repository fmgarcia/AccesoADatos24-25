package com.fran.xmljson.utilidades;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
	

}
