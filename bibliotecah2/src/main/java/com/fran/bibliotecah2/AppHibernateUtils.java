package com.fran.bibliotecah2;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Transaction;

import com.fran.bibliotecah2.entidades.Autores;
import com.fran.bibliotecah2.entidades.Libros;
import com.fran.bibliotecah2.utilidades.HibernateUtils;
import com.fran.bibliotecah2.utilidades.MenuUtils;

public class AppHibernateUtils {
	
	static Scanner sc;
	
	public static Libros insertarLibro() {
		System.out.print("Introduzca el código del libro: ");
		int id = Integer.parseInt(sc.nextLine());
		System.out.print("Introduzca el título: ");
		String titulo = sc.nextLine();
		System.out.print("Introduzca el autor: ");
		String autor = sc.nextLine();
		Libros libro = new Libros(id, new Autores(autor), titulo);
		if(HibernateUtils.persist(libro))
			return libro;
	    else
	    	return null;
	}
	
	public static Libros actualizarLibro() {
		System.out.print("Introduzca el código del libro: ");
		int id = Integer.parseInt(sc.nextLine());
		Libros libroAModificar = HibernateUtils.getId(Libros.class, id);
		if (libroAModificar == null) {
			System.out.println("No se ha encontrado el libro con ese código");
			return null;
		}
		System.out.print("Introduzca el nuevo título: ");
		libroAModificar.setTitulo(sc.nextLine());
		System.out.print("Introduzca el nuevo autor: ");
		libroAModificar.setAutores(new Autores(sc.nextLine()));
		HibernateUtils.merge(libroAModificar);
		System.out.println("Libro actualizado correctamente");
		return libroAModificar;
	}
	
	
	private static Object borrarLibro() {
		System.out.print("Introduzca el código del libro: ");
		int id = Integer.parseInt(sc.nextLine());
		Libros libroABorrar = HibernateUtils.getId(Libros.class, id);
		if (libroABorrar == null) {
			System.out.println("No se ha encontrado el libro con ese código");
			return null;
		}
		HibernateUtils.remove(libroABorrar);
		System.out.println("Libro borrado correctamente");
		return null;
	}
	
	public static void tratarMenu() {
		while (true) {
			System.out.println(MenuUtils.imprimirMenu("Mostrar libros", "Insertar libro", "Actualizar libro", "Borrar libro")); 
			String opcion = sc.nextLine();
			if (opcion.equals("0"))
				break;
			switch (opcion) {
			//case "1" -> mostrarLibros();
			case "2" -> insertarLibro();
			case "3" -> actualizarLibro();
			case "4" -> borrarLibro();
			default -> System.out.println("Opción no válida");
			}
		}
	}
	
	public static void probar_persistAll() {
		List<Object> objetos = List.of(
				new Libros(30, new Autores("FROJA"), "Libro1"), 
				new Libros(31, new Autores("FROJA"), "Libro1"), 
				new Libros(32, new Autores("FROJA"), "Libro1"), 
				new Libros(33, new Autores("FROJA"), "Libro1"), 
				new Libros(34, new Autores("FROJA"), "Libro1"), 
				new Libros(34, new Autores("FROJA"), "Libro2"));
		if (HibernateUtils.persistAll(objetos))
			System.out.println("Guardado correctamente");
		else
			System.out.println("Error al guardar");
	}

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		HibernateUtils.quitarLog();
		if (!HibernateUtils.abrirConexion()) {
			System.out.println("Error al abrir la conexión");
			return;
		}
		tratarMenu();
		
		sc.close();
	}

}
