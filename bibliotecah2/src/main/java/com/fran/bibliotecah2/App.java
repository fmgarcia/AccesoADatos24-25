package com.fran.bibliotecah2;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.fran.bibliotecah2.entidades.Autores;
import com.fran.bibliotecah2.entidades.Libros;
import com.fran.bibliotecah2.utilidades.MenuUtils;

public class App {
	static SessionFactory sessionFactory;
	static Session session;
	static Scanner sc;

	public static boolean abrirConexion() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		return (session != null);
	}

	public static void cerrarConexion() {
		session.close();
		sessionFactory.close();
	}

	public static void quitarLog() {
		Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);
	}

	public static void mostrarLibros() {
		Query<Libros> query = session.createQuery("from Libros", Libros.class); // En lenguaje HQL
		for (Libros libro : query.list()) {
			System.out.println(libro.getTitulo() + " - "
					+ session.get(Autores.class,libro.getAutores().getCod()).getNombre());
		}
	}

	public static Libros insertarLibro() {
		System.out.print("Introduzca el código del libro: ");
		int id = Integer.parseInt(sc.nextLine());
		System.out.print("Introduzca el título: ");
		String titulo = sc.nextLine();
		System.out.print("Introduzca el autor: ");
		String autor = sc.nextLine();
		Transaction trans = session.beginTransaction();
		Libros libro = new Libros(id, new Autores(autor), titulo);
		session.persist(libro);
		trans.commit();
		System.out.println("Libro insertado correctamente");
		return libro;
	}
	
	public static Libros actualizarLibro() {
		System.out.print("Introduzca el código del libro: ");
		int id = Integer.parseInt(sc.nextLine());
		List<Libros> resultados = session.createQuery("FROM Libros WHERE id=" + id, Libros.class).list();
		if (resultados.isEmpty()) {
			System.out.println("No se ha encontrado el libro con ese código");
			return null;
		}
		Transaction trans = session.beginTransaction();
		Libros libroAModificar = (Libros) resultados.get(0);
		System.out.print("Introduzca el nuevo título: ");
		libroAModificar.setTitulo(sc.nextLine());
		System.out.print("Introduzca el nuevo autor: ");
		libroAModificar.setAutores(new Autores(sc.nextLine()));
		session.merge(libroAModificar);  // Actualiza el registro
		trans.commit();
		System.out.println("Libro actualizado correctamente");
		return libroAModificar;
	}
	
	private static Object borrarLibro() {
		System.out.print("Introduzca el código del libro: ");
		int id = Integer.parseInt(sc.nextLine());
		List<Libros> resultados = session.createQuery("FROM Libros WHERE id=" + id, Libros.class).list();
		if (resultados.isEmpty()) {
			System.out.println("No se ha encontrado el libro con ese código");
			return null;
		}
		Transaction trans = session.beginTransaction();
		session.remove(resultados.get(0));
		trans.commit();
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
			case "1" -> mostrarLibros();
			case "2" -> insertarLibro();
			case "3" -> actualizarLibro();
			case "4" -> borrarLibro();
			default -> System.out.println("Opción no válida");
			}
		}
	}

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		quitarLog(); // Quitamos los mensajes de log de Hibernate
		if (!abrirConexion()) {
			System.out.println("Error al abrir la conexión");
			return;
		}
		tratarMenu();
		cerrarConexion();
		sc.close();
	}
}
