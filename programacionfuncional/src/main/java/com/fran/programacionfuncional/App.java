package com.fran.programacionfuncional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.fran.programacionfuncional.entidades.Usuario;

public class App 
{
	
	public static List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public static void tearUp() {
		usuarios.add(new Usuario(1,"Fran",-100000));
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
	
	public static void groupingBy() {
		// Agrupar por el elemento que pongamos. Tendremos tantas listas como elementos distintos
		Map<Character,List<Usuario>> letras = usuarios.stream()
				.collect(Collectors.groupingBy(e->new Character(e.getNombre().charAt(0))));
		// acceder a los elementos
		letras.get('P').forEach(e->System.out.println(e));
		System.out.println(letras.containsKey('A')?
				"Hay personas que empiezan por A":"No hay personas que empiezan por A");
		
		// Elementos desde posición incial a final en una lista
		usuarios.stream()
		.filter(e->e.getNombre().charAt(0)>='A' && e.getNombre().charAt(0)<='F')
		.forEach(e->System.out.println(e));
		
		// Elementos desde posición incial a final en un mapa
		for(int i = (int)'A';i<=(int)'F';i++) {
			if(letras.containsKey((char)i))
				letras.get((char)i).forEach(e->System.out.println(e));
		}		
	}
	
	public static void count() {
		long sueldoNegativo = usuarios.stream()
				.filter(e->e.getSueldo()<0)
				.count();
		System.out.println("Número de empleados con sueldo negativo: " + sueldoNegativo);
	}
	
	public static void skipYLimit() {
		// ambas son no finales
		// skip salta un número de resultados.
		// limit limita el número de elementos devueltos.
		
		// Los usuarios 3,4 y 5 que más ganan en la empresa
		List<Usuario> usuarios345 = usuarios.stream()
				.sorted(Comparator.comparingDouble(Usuario::getSueldo).reversed())
				.skip(2)
				.limit(3)
				.collect(Collectors.toList());
		usuarios345.forEach(e->System.out.println(e));
		
		// Solo mostrar
		usuarios.stream()
		.sorted(Comparator.comparingDouble(Usuario::getSueldo).reversed())
		.skip(2)
		.limit(3)
		.forEach(e->System.out.println(e));
	}
	
	public static void maxYmin() {
		// Max devuelve el máximo valor de un campo
		Optional<Usuario> masgana = usuarios.stream()
				.max(Comparator.comparingDouble(Usuario::getSueldo));
		if(masgana.isPresent())
			System.out.println(masgana.get().toString());
		
		Usuario menosgana = usuarios.stream()
				.min(Comparator.comparingDouble(Usuario::getSueldo))
				.orElse(new Usuario(1,"Fran",0));
		System.out.println(menosgana.toString());
		
	}
	
	public static void distinct() {
		// No final. Saca elementos diferentes.
		// Saca los id's diferentes porque coge el equals de la clase
		long idsdistintos = usuarios.stream()
				.distinct()
				.count();
		System.out.println("Número de usuarios distintos: "+ idsdistintos);
		
		long nombresdistintos = usuarios.stream()
				.map(e->e.getNombre())
				.distinct()
				.count();
		System.out.println("Número de usuarios con nombres distintos: "+ nombresdistintos);
		System.out.println("Los nombres distintos son: ");
		usuarios.stream()
		.map(e->e.getNombre())
		.distinct()
		.forEach(e->System.out.println(e));
	}
	
	public static void match() {
		// Finales. Devuelven booleanos
		// anyMatch. True si existe alguno que cumpla la condición
		// allMatch. True si todos cumplen la condición
		// noneMatch. True si ninguno cumple la condición
		
		// ¿Alguien gana más de 100000?
		boolean ganaMas100000 = usuarios.stream()
				.anyMatch(e->e.getSueldo()>100000);
		
		// ¿Todos ganan más de cero?
		boolean ganaMas0 = usuarios.stream()
				.allMatch(e->e.getSueldo()>0);
		
		// ¿Nadie gana menos de cero?
		boolean ganaMas0b = usuarios.stream()
				.noneMatch(e->e.getSueldo()<0);
		
		System.out.println(ganaMas100000 + " " + ganaMas0 + " " + ganaMas0b);		
	}
	
	public static void offtopic1() {
		// Crea una lista de números con los valores inicial y final
		IntStream.rangeClosed(1, 10).forEach(e->System.out.println(e + "*7=" + (e*7)));		
	}
	
	public static void summarizingDouble() {
		// Final.
		// Recolecciona todas las estadísticas de un campo numérico
		DoubleSummaryStatistics estadisticas = usuarios.stream()
		.collect(Collectors.summarizingDouble(Usuario::getSueldo));
		System.out.println("Media: " + estadisticas.getAverage());
		System.out.println("Máximo: " + estadisticas.getMax());
		System.out.println("Míximo: " + estadisticas.getMin());
		System.out.println("Suma: " + estadisticas.getSum());
		System.out.println("Cuenta: " + estadisticas.getCount());
	}
	
	public static void reduce() {
		// Reduce : Reduce los datos que tengamos a un ÚNICO valor 
		double sumaSueldos = usuarios.stream()
				.mapToDouble(e->e.getSueldo())
				.reduce(0, (a,b)->a+b);  // multiplica todos los sueldos tomando como valor inicial el del primer parámetro
				//.reduce(0,Double::sum);
		System.out.println(sumaSueldos);
		
		long multiplicacionLetrasNombres = usuarios.stream()
				.mapToInt(e->e.getNombre().length())
				.reduce(1, (a,b)->a*b);
		System.out.println("La multiplicación de las letras de las palabras :" + multiplicacionLetrasNombres);
	
		// cadena concatenada con los nombres de los usuarios
		System.out.println(usuarios.stream()
			.map(e->e.getNombre())
			.reduce((a,b)-> a.toString() + ";" + b.toString()).get());
	}
	
	public static void joining() {
		String nombresSeparados = usuarios.stream()
			.map(e->e.getNombre().toLowerCase())
			.distinct()
			.sorted()
			.collect(Collectors.joining(", ")).toString();
		System.out.println(nombresSeparados);
	}
	
	private static String convertirMayusculas(String nombre) {
		try {
			Thread.sleep(1000); // Un segundo
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(nombre.toUpperCase());
		return nombre.toUpperCase();
	}
	
	
	public static void parallelStream() {
		long tiempo1 = System.currentTimeMillis();
		usuarios.forEach(e->convertirMayusculas(e.getNombre()));
		long tiempo2 = System.currentTimeMillis();
		System.out.println("El tiempo es: " + (tiempo2-tiempo1));
		tiempo1 = System.currentTimeMillis();
		usuarios.parallelStream().forEach(e->convertirMayusculas(e.getNombre()));
		tiempo2 = System.currentTimeMillis();
		System.out.println("Tiempo en paralelo: " + (tiempo2-tiempo1));
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
    	//partitionigBy();
    	//groupingBy();
    	//count();
    	//skipYLimit();
    	//maxYmin();
    	//distinct();
    	//match();
    	//offtopic1();
    	//reduce();
    	//joining();
    	//parallelStream();
    }
}
