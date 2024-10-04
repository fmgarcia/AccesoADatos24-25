package com.fran.programacionfuncional;

import java.util.ArrayList;
import java.util.List;

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
		// personas que ganan más de 50000
		usuarios.stream()
			.filter(e->e.getSueldo()>50000)
			.forEach(e->System.out.println(e));
	}
	
    public static void main( String[] args )
    {
    	tearUp();
    	//forEach();
    	filter();
    }
}
