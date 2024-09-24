package com.fran.ficheros.utilidades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;


public class SerializacionUtils {
	
	public static <T> boolean serializarObjeto(String rutaCompleta, T objeto) {
		try {
			File fichero = new File(rutaCompleta);
			FileOutputStream ficheroSalida = new FileOutputStream(fichero);
			ObjectOutputStream ficheroObjetos = new ObjectOutputStream(ficheroSalida);
			ficheroObjetos.writeObject(objeto);  // Serializa
			ficheroObjetos.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static <T> T deserializarObjeto(String rutaCompleta) {
		try {
			File fichero = new File(rutaCompleta);
			FileInputStream ficheroSalida = new FileInputStream(fichero);
			ObjectInputStream ficheroObjetos = new ObjectInputStream(ficheroSalida);
			@SuppressWarnings("unchecked")
			T objeto = (T)ficheroObjetos.readObject();  // Serializa
			ficheroObjetos.close();
			return objeto;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> boolean serializarListaObjetos(String rutaCompleta, List<T> objetos) {
		
		try {
			ObjectOutputStream ficheroObjetos = new ObjectOutputStream(
					new FileOutputStream(new File(rutaCompleta)));
			ficheroObjetos.writeObject(objetos);  // Serializa
			ficheroObjetos.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
				
	}
	
	public static <T> List<T> deserializarListaObjetos(String rutaCompleta) {
		try {
			ObjectInputStream ficheroObjetos = new ObjectInputStream(
					new FileInputStream(new File(rutaCompleta)));
			@SuppressWarnings("unchecked")
			List<T> lista = (List<T>)ficheroObjetos.readObject();  // Serializa
			ficheroObjetos.close();
			return lista;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
