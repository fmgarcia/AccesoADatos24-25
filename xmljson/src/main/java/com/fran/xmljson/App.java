package com.fran.xmljson;

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
		/*System.out.println(
				InternetUtils.readUrlList("https://api.football-data.org/v4/teams/86/matches?status=SCHEDULED"
				,"XXX"));*/
	}
	
    
	public static void pruebasJson() {
		//JsonUtils.leerJsonDesdeFichero("C:/ficheros/profesor.json");
		JsonUtils.leerLuke("C:/ficheros/luke.json");
	}
	
	public static void main(String[] args) {
        //pruebasXml();
    	//pruebasInternetUtils();
		pruebasJson();
    }
    
}
