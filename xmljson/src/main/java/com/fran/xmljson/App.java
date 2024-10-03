package com.fran.xmljson;

import java.util.ArrayList;
import java.util.List;

import com.fran.xmljson.entidades.Films;
import com.fran.xmljson.utilidades.JsonUtils;
import com.fran.xmljson.utilidades.XmlUtils;

public class App {
	
	public static void pruebasXml() {
		//XmlUtils.procesarXmlSax();  // Solamente usar para archivos extremadamente grandes
    	//XmlUtils.procesarAsignatura();
    	/*XmlUtils.procesarMarca("C:/ficheros/portada.xml").stream()
    	.filter(e->e.getTitulo().contains("Madrid"))
    	.forEach(e->System.out.println(e));*/
		//String xml = InternetUtils.readUrl("https://e00-marca.uecdn.es/rss/portada.xml");
		
    	XmlUtils.procesarMarcaOnline("https://e00-marca.uecdn.es/rss/portada.xml").stream()
    	.forEach(e->System.out.println(e));
		
	}
	
	
	public static void pruebasInternetUtils() {
		//System.out.println(InternetUtils.readUrl("https://swapi.dev/api/people/4/?format=json"));
		/*System.out.println(
				InternetUtils.readUrlList("https://api.football-data.org/v4/teams/86/matches?status=SCHEDULED"
				,"XXX"));*/
	}
	
    
	public static void pruebasJson() {
		//JsonUtils.leerJsonDesdeFichero("C:/ficheros/profesor.json");
		//JsonUtils.leerLuke("C:/ficheros/luke.json");
		// filtro sobre la marcha
		/*
		JsonUtils.devolverTareasInternet("https://jsonplaceholder.typicode.com/todos").stream()
			.filter(e->e.isCompleted()==true)
			.forEach(e->System.out.println(e));
		List<Tareas> tareas = JsonUtils.devolverTareasInternet("https://jsonplaceholder.typicode.com/todos");
		*/
		/*
		System.out.println(
		JsonUtils.leerStarWars("https://swapi.dev/api/people/1/?format=json")
				);
		*/
		//System.out.println(JsonUtils.leerGenerico("https://swapi.dev/api/people/1/?format=json", People.class));
		//System.out.println(JsonUtils.leerGenerico("https://swapi.dev/api/films/1/?format=json", Films.class));
		
		// Coger todas las películas
		List<Films> peliculas = new ArrayList<Films>();
		for (int i = 1; i<=6; i++) {
			peliculas.add(
			JsonUtils.leerGenerico("https://swapi.dev/api/films/" + i + "/?format=json", Films.class)
			);
		}
		
		peliculas.forEach(e->System.out.println(e));
		System.out.println(peliculas.size());
		
	}
	
	public static void main(String[] args) {
        pruebasXml();
    	//pruebasInternetUtils();
		//pruebasJson();
    }
    
}
