package com.fran.xmljson;

import com.fran.xmljson.utilidades.XmlUtils;

public class App {
	
    public static void main(String[] args) {
        //XmlUtils.procesarXmlSax();  // Solamente usar para archivos extremadamente grandes
    	//XmlUtils.procesarAsignatura();
    	XmlUtils.procesarMarca("C:/ficheros/portada.xml").stream()
    	.filter(e->e.getTitulo().contains("Madrid"))
    	.forEach(e->System.out.println(e));
    }
    
}
