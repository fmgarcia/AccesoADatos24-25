package com.fran.programacionfuncional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fran.programacionfuncional.entidades.Usuario;

public class App 
{
	
	public static List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public static void tearUp() {
		usuarios.add(new Usuario(1,"Fran",100000));
		usuarios.add(new Usuario(2,"Paco",10000));
		usuarios.add(new Usuario(3,"Dani",50000));
		usuarios.add(new Usuario(4,"Fran",200000));
		usuarios.add(new Usuario(5,"Consu",40000));
		usuarios.add(new Usuario(6,"Carmen",300000));
	}
	
	public static void tearDown() {
		usuarios.clear();
	}
	
	public static void forEach() {
		// Final
		System.out.println("Sin stream:");
		usuarios.forEach(e->System.out.println(e));
		System.out.println("Con stream:");
		usuarios.stream().forEach(e->System.out.println(e));
	}
	
	public static void filter() {
		// No final
		// personas que ganan más de 50000 por pantalla
		/*usuarios.stream()
			.filter(e->e.getSueldo()>50000)
			.forEach(e->System.out.println(e));*/
		// Crear lista de las personas que ganan más de 50000
		List<Usuario> usuariosRicos = usuarios.stream()
				.filter(e->e.getSueldo()>50000)
				.collect(Collectors.toList());
		
		List<Usuario> usuariosRicos2 = new ArrayList<Usuario>();
		usuarios.stream()
				.filter(e->e.getSueldo()>50000)
				.forEach(e->usuariosRicos2.add(e));
		
		List<Usuario> usuariosRicos3 = new ArrayList<Usuario>();
		usuarios.stream()
				.filter(e->e.getSueldo()>50000 && e.getNombre().length()>4)
				.forEach(e->usuariosRicos3.add(e));
		
		List<Usuario> usuariosRicos4 = new ArrayList<Usuario>();
		usuarios.stream()
				.filter(e->e.getSueldo()>50000)
				.filter(e->e.getNombre().length()>4)
				.forEach(e->usuariosRicos4.add(e));
		
	}
	
    public static void main( String[] args )
    {
    	tearUp();
    	//forEach();
    	filter();
    }
}
