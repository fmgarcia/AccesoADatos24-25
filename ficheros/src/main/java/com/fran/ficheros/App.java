package com.fran.ficheros;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fran.ficheros.entidades.Alumno;
import com.fran.ficheros.utilidades.FicherosUtils;
import com.fran.ficheros.utilidades.SerializacionUtils;

/**
 * Hello world!
 */
public class App {
	
	public static void pruebasFicheros() {
        //FicherosUtils.leerFichero("./data/alumnosLineas.txt");
    	//FicherosUtils.devolverLineasFichero("C:/ficheros/dam2425.txt").forEach(e->System.out.println(e));

    	// Imprime las líneas de más de 4 letras
    	/*FicherosUtils.devolverLineasFichero("C:/ficheros","dam2425.txt").stream()
    		.filter(e->e.length()>4)
    		.forEach(e->System.out.println(e));*/
		
    	
    	// Obtén aquellas líneas que contengan 'a' con más de 4 letras
    	/*FicherosUtils.devolverLineasFichero("C:/ficheros","dam2425.txt").stream()
			.filter(e->e.contains("a") && e.length()>4)
			.forEach(e->System.out.println(e));*/
    	
    	/*List<String> alumnosDam  = FicherosUtils.devolverLineasFichero("C:/ficheros","alumnos.txt").stream()
    		.filter(e->e.contains("2º DAM"))
    		.collect(Collectors.toList());
    	
    	
    	List<Alumno> alumnos = new ArrayList<Alumno>();
    	    	
    	alumnosDam.stream()
    		.forEach(e->
    		alumnos.add(
    				new Alumno(e.split(";")[0],
    						e.split(";")[1],
    						Double.parseDouble(e.split(";")[2].replace(',', '.')))));*/
    	
    	/*
    	List<Alumno> alumnos = new ArrayList<Alumno>();
    	
    	FicherosUtils.devolverLineasFichero("C:/ficheros","alumnos.txt").stream()
    		.filter(e->e.contains("2º DAM"))
    		.forEach(e-> alumnos.add(
    				new Alumno(e.split(";")[0],
    						e.split(";")[1],
    						Double.parseDouble(e.split(";")[2].replace(',', '.')))));

    	
    	alumnos.forEach(alumno->System.out.println(alumno));*/
    	
    	
    	//System.out.println(FicherosUtils.devolverFichero("C:/ficheros","dam2425.txt"));
        /*
    	List<String> lineas = new ArrayList<String>();
    	lineas.add("c");
    	lineas.add("d");
    	lineas.add("e"); */  
    	/*if(FicherosUtils.escribirFichero("C:/ficheros/prueba2425.txt", lineas))
    		System.out.println("Fichero escrito correctamente");*/
    	/*if(FicherosUtils.anyadirFichero("C:/ficheros/prueba2425b.txt", lineas))
    		System.out.println("Fichero escrito correctamente");*/
    	
	}
	
	public static void pruebasSerializacion() {
		/*if(SerializacionUtils.serializarObjeto("C:/ficheros/alumnos24.dat", 
				new Alumno("2º DAM","Fran",10.0)))
			System.out.println("Serialización correcta");
		Alumno alumno = SerializacionUtils.deserializarObjeto("C:/ficheros/alumnos24.dat");
		System.out.println(alumno);*/
		
		List<Alumno> alumnos = new ArrayList<Alumno>();
		alumnos.add(new Alumno("2º DAM","Fran",10.0));
		alumnos.add(new Alumno("2º DAM","Paco",8.0));
		alumnos.add(new Alumno("2º DAM","Pepe",7.0));
		alumnos.add(new Alumno("2º DAM","David",2.0));
		if(SerializacionUtils.serializarObjeto("C:/ficheros/lista24.dat", alumnos))
			System.out.println("Serialización correcta");
		alumnos.clear();
		alumnos = SerializacionUtils.deserializarObjeto("C:/ficheros/lista24.dat");
		alumnos.forEach(e->System.out.println(e));
	}
	
    public static void main(String[] args) {

    	//pruebasFicheros();
    	pruebasSerializacion();    	
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
