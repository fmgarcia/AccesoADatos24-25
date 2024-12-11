package com.fran.hibernateanotaciones3;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.fran.hibernateanotaciones3.entidades.Autores;
import com.fran.hibernateanotaciones3.entidades.Escribir;
import com.fran.hibernateanotaciones3.entidades.EscribirId;
import com.fran.hibernateanotaciones3.entidades.Libros;
import com.fran.hibernateanotaciones3.utilidades.HibernateUtils;

public class App 
{
	static Scanner sc;
	
	public static void probarEscribir() {
		
		List<Escribir> resultados = HibernateUtils.getAll(Escribir.class);
		for (Escribir resultado : resultados) {
			 System.out.println("(codautor,codlibro): ("
			+ resultado.getId().getCodautor()
			+ ","
			 + resultado.getId().getCodlibro()
			+ ") "
			+ resultado.getAutores().getNombre()
			+ " "
			 + resultado.getLibros().getTitulo()
			+ " "
			+ resultado.getAnyo());
		}
	}
	
	
	public static void insertarAutoryLibro() {
		 
		Autores autor = new Autores("FRAN2", "Francisco García", new HashSet<Escribir>());
		Set<Autores> autores = new HashSet<Autores>();
		autores.add(autor);
		
		Libros libro1 = new Libros(8, "Libro Fran1", new HashSet<Escribir>());
		Libros libro2 = new Libros(9, "Libro Fran2", new HashSet<Escribir>());
		
		Escribir escribir1 = new Escribir(new EscribirId("FRAN2", 8), autor, libro1, 1990);
		Escribir escribir2 = new Escribir(new EscribirId("FRAN2", 9), autor, libro2, 1991);

		autor.getEscribirs().add(escribir1);
		autor.getEscribirs().add(escribir2);

		libro1.getEscribirs().add(escribir1);
		libro2.getEscribirs().add(escribir2);

		if (HibernateUtils.persistAll(autor, libro1, libro2,escribir1,escribir2)) {
			System.out.println("Guardado correctamente");
		} else {
			System.out.println("Error al guardar");
		}
	}
	
	
    public static void main( String[] args )
    {
    	sc = new Scanner(System.in);
		HibernateUtils.quitarLog();
		if (!HibernateUtils.abrirConexion()) {
			System.out.println("Error al abrir la conexión");
			return;
		}

        
		//probarEscribir();
		insertarAutoryLibro();
		
		sc.close();
		HibernateUtils.cerrarConexion();
    }
    
}
