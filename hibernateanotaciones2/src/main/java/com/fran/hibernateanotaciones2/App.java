package com.fran.hibernateanotaciones2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.fran.hibernateanotaciones2.entidades.Autores;
import com.fran.hibernateanotaciones2.entidades.Libros;
import com.fran.hibernateanotaciones2.utilidades.HibernateUtils;

public class App {
	
	static Scanner sc;
	
	public static void insertarAutoryLibro() {
		 
		Autores autor = new Autores("FRAN", "Francisco García", null);
		Set<Autores> autores = new HashSet<Autores>();
		autores.add(autor);
		Libros libro1 = new Libros(6, "Libro Fran1", autores);
		Libros libro2 = new Libros(7, "Libro Fran2", autores);

		List<Object> listaSave = new ArrayList<Object>();
		listaSave.add(autor);
		listaSave.add(libro1);
		listaSave.add(libro2);
		if (HibernateUtils.persistAll(listaSave)) {
			System.out.println("Guardado correctamente");
		} else {
			System.out.println("Error al guardar");
		}
	}
	
	public static void insertarAutoryLibro2() {
		 
		Autores autor = new Autores("FRAN2", "Francisco García", null);
		Set<Autores> autores = new HashSet<Autores>();
		autores.add(autor);
		Libros libro1 = new Libros(8, "Libro Fran1", autores);
		Libros libro2 = new Libros(9, "Libro Fran2", autores);

		if (HibernateUtils.persistAll(autor, libro1, libro2)) {
			System.out.println("Guardado correctamente");
		} else {
			System.out.println("Error al guardar");
		}
	}
	
    public static void main(String[] args) {
    	sc = new Scanner(System.in);
		HibernateUtils.quitarLog();
		if (!HibernateUtils.abrirConexion()) {
			System.out.println("Error al abrir la conexión");
			return;
		}

		//insertarAutoryLibro();
		insertarAutoryLibro2();
		
		sc.close();
		HibernateUtils.cerrarConexion();
    }
}
