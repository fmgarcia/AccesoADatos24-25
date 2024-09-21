package com.fran.ficheros.utilidades;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FicherosUtils {
	
	/**
	 * Dado un fichero lo imprime por consola
	 * @param ruta Ruta absoluta del fichero
	 */
	public static void leerFichero(String ruta)  {
		try {
			Files.readAllLines(Paths.get(ruta)).forEach(e->System.out.println(e));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Devuelve el contenido de un fichero en líneas
	 * @param directorio Ruta donde se encuentra el archivo
	 * @param nombreFichero Nombre del archivo a leer
	 * @return Lista de líneas del fichero
	 */
	public static List<String> devolverLineasFichero(String directorio, String nombreFichero)  {
		return devolverLineasFichero(directorio + File.separator + nombreFichero);
	}
	
	/**
	 * Devuelve el contenido de un fichero en líneas
	 * @param rutaCompleta Toda la ruta del fichero incluido el mismo
	 * @return Lista de líneas del fichero
	 */
	public static List<String> devolverLineasFichero(String rutaCompleta)  {
		try {
			return Files.readAllLines(Paths.get(rutaCompleta),StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * A partir de un fichero devuelve todo el contenido en un único String
	 * @param directorio Ruta del fichero
	 * @param nombreFichero Nombre del fichero
	 * @param charset Conjunto de letras utilizado
	 * @return String con todo el contenido del fichero
	 */
	public static String devolverFichero(String directorio, String nombreFichero, Charset charset) {
		try {
			return Files.readString(Paths.get(directorio + File.separator + nombreFichero), charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * A partir de un fichero devuelve todo el contenido en un único String
	 * @param directorio Ruta del fichero
	 * @param nombreFichero Nombre del fichero
	 * @return String con todo el contenido del fichero
	 */
	public static String devolverFichero(String directorio, String nombreFichero) {
		return devolverFichero(directorio, nombreFichero,Charset.defaultCharset());
	}
	
	/**
	 * A partir de un fichero devuelve todo el contenido en un único String
	 * @param rutaCompleta Ruta del fichero incluyendo el nombre
	 * @return String con todo el contenido del fichero
	 */
	public static String devolverFichero(String rutaCompleta) {
		File fichero = new File(rutaCompleta);	
		return devolverFichero(fichero.getParent(),fichero.getName());
	}
	
	/**
	 * Dada una ruta y una lista de líneas crea un fichero en esa ruta.
	 * Si el fichero ya existe lo sobreescribe
	 * @param rutaCompleta Ruta del fichero
	 * @param lineas Lineas del fichero
	 * @return True si puede escribir el fichero. False en caso contrario.
	 */
	public static boolean escribirFichero(String rutaCompleta, List<String> lineas) {
		try {
			Files.write(Paths.get(rutaCompleta), lineas);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Añado datos a un fichero. Si el fichero existe añade datos por el final, si no, lo crea y
	 * añade datos
	 * @param rutaCompleta Ruta del fichero
	 * @param lineas Lineas del fichero
	 * @return True si puede añadir/crear fichero. False caso contrario
	 */
	public static boolean anyadirFichero(String rutaCompleta, List<String> lineas) {
		try {
			if(Files.exists(Paths.get(rutaCompleta)))
				Files.write(Paths.get(rutaCompleta), lineas, StandardOpenOption.APPEND);
			else
				Files.write(Paths.get(rutaCompleta), lineas, StandardOpenOption.CREATE);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
	
	
	

}
