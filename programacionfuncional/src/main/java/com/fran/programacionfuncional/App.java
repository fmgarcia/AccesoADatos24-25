package com.fran.programacionfuncional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
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
	
	public static void map() {
		// No es final
		// a partir de una lista de usuario crea una lista con sus nombres
		List<String> nombres = usuarios.stream()
					.map(e->e.getNombre())
					.collect(Collectors.toList());
		nombres.forEach(e->System.out.println(e));
		
		System.out.println("-----------------------");
		// a partir de una lista de usuario crea una lista con sus nombres de aquellos
		// que tengan más de 4 letras
		List<String> nombresLargos = usuarios.stream()
				.filter(e->e.getNombre().length()>4)
				.map(e->e.getNombre())
				.filter(e->e.startsWith("Ca"))
				.collect(Collectors.toList());
		nombresLargos.forEach(e->System.out.println(e));
		System.out.println("-----------------------");
		usuarios.stream()				
				.map(e->e.getNombre().length())
				.collect(Collectors.toList())
				.forEach(e->System.out.println(e));
		System.out.println("-----------------------");
		usuarios.stream()
			.filter(e->e.getSueldo()>30000)
			.map(e->e.getNombre())
			.collect(Collectors.toList())
			.forEach(e->System.out.println(e));		
	}
	
	public static void sumAvg() {
		// Cuanto nos gastamos en sueldos de los empleados?
		Double sumaSueldos = usuarios.stream()
			.mapToDouble(e->e.getSueldo())
			.sum();
		OptionalDouble mediaSueldos = usuarios.stream()
				.mapToDouble(e->e.getSueldo())
				.average();
		Double mediaSueldos2 = usuarios.stream()
				.mapToDouble(e->e.getSueldo())
				.average().orElse(-1);
		
		System.out.println("La suma de los salarios es: " + sumaSueldos);
		System.out.println("La media de los salarios es: " + mediaSueldos.getAsDouble());
		System.out.println("La media de los salarios es: " + mediaSueldos2);
		
	}
	
	public static void find() {
		Usuario usuarioGanaPoco = usuarios.stream()
			.filter(e->e.getSueldo()<50000)
			.findFirst().orElse(new Usuario(0,"Pobre",0));
		System.out.println(usuarioGanaPoco);
		Usuario usuarioGanaPocoAzar = usuarios.stream()
				.filter(e->e.getSueldo()<1)
				.findAny().orElse(new Usuario(0,"Pobre",0));
		System.out.println(usuarioGanaPocoAzar);
	}
	
	public static void flatMap() {
		// No final
		// A partir de una lista de listas las fusiona
		// FlatMap: Coge una lista de listas y las concatena en una lista única. Pasa de dos dimensiones a una  [][] -> []
		List<String> alumnosDam = new ArrayList<String>(Arrays.asList("Fran","Paco","Dani"));
		List<String> alumnosDaw = new ArrayList<String>(Arrays.asList("Adrián","Federico","Lorena"));
		List<List<String>> alumnos = new ArrayList<List<String>>(Arrays.asList(alumnosDam,alumnosDaw));
		
		/*List<String> todosAlumnos = new ArrayList<String>(alumnosDam);
		todosAlumnos.addAll(alumnosDaw);*/
		alumnos.stream()
		.filter(e->e.size()>2)
		.flatMap(e->e.stream())  // junta las listas
		.sorted()
		.forEach(e->System.out.println(e));	
	}
	
	public static void peek() {
		// No final. Igual que el forEach pero no final
		// Subir el sueldo a aquellos usuarios que ganen menos de 20000 y crear una nueva lista con esos datos
		List<Usuario> pobres = usuarios.stream()
		.filter(e->e.getSueldo()<20000)
		.peek(e->e.setSueldo(e.getSueldo()+1000))
		.collect(Collectors.toList());
		pobres.forEach(e->System.out.println(e));
		
	}
	
	public static void partitionigBy() {
		// Final
		// Parte la lista original en dos sublistas. Una que cumple la condición de partición y otra que no.
		Map<Boolean,List<Usuario>> sublistas = usuarios.stream()
		.collect(Collectors.partitioningBy(e->e.getSueldo()>10000));
		
		// Los que ganan +10000
		System.out.println("Usuarios que ganan más de 10000 Euros:");
		sublistas.get(true).forEach(e->System.out.println(e));
		// Los que ganan -10000
		System.out.println("Usuarios que ganan más de 10000 Euros:");
		sublistas.get(false).forEach(e->System.out.println(e));	
	}
	
	public static void main( String[] args )
    {
    	tearUp();
    	//forEach();
    	//filter();
    	//map();
    	//sumAvg();
    	//find();
    	//flatMap();
    	//peek();
    	partitionigBy();
    }
}
