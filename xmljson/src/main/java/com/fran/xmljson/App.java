package com.fran.xmljson;

import java.util.ArrayList;
import java.util.List;

import com.fran.xmljson.entidades.Films;
import com.fran.xmljson.entidades.People;
import com.fran.xmljson.utilidades.InternetUtils;
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
		System.out.println(
				InternetUtils.readUrlList("https://api.football-data.org/v4/teams/86/matches?status=SCHEDULED"
				,"XXX"));
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
		
		// Coger todos los personajes
		List<People> personajes = new ArrayList<People>();
		for (int i = 1; i<=4; i++) {
			personajes.add(
			JsonUtils.leerGenerico("https://swapi.dev/api/people/" + i + "/?format=json", People.class)
			);
		}
		
		personajes.forEach(e->System.out.println(e));
		System.out.println(personajes.size());
		
	}
	
	public static void obtenerPeliculasPersonajes() {
		List<Films> peliculas = new ArrayList<Films>();
		for (long i = 1; i<=JsonUtils.devolverCuentaRegistros("https://swapi.dev/api/films/", "count"); i++) {
			peliculas.add(
			JsonUtils.leerGenerico("https://swapi.dev/api/films/" + i , Films.class)
			);
		}
		
		peliculas.stream()
			.forEach(e->{ System.out.println("\n\n" + e.getTitle());
			System.out.println("--------------------------------------");
				e.getCharacters()
					.forEach(p->{
						People people = JsonUtils.leerGenerico(p , People.class);
						System.out.println(people.getNombre());
								}
							);
						}
					);		
	}
	
	public static void ejemplosImprimir() {
		People people = JsonUtils.leerGenerico("https://swapi.dev/api/people/1/?format=json", People.class);
		//System.out.println(JsonUtils.crearJson(people));
		System.out.println(JsonUtils.crearJsonPretty(people));
	}
	
	public static void main(String[] args) {
        //pruebasXml();
    	//pruebasInternetUtils();
		//pruebasJson();
		//obtenerPeliculasPersonajes();
		ejemplosImprimir();
    }
    
}
