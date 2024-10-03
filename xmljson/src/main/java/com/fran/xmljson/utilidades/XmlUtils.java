package com.fran.xmljson.utilidades;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.fran.xmljson.entidades.Noticia;

public class XmlUtils {

	public static void procesarXmlSax() {
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
			DefaultHandler manejadorEventos = new DefaultHandler() {
				String etiquetaActual = "";
				String contenido = "";

				// Método que se llama al encontrar inicio de etiqueta: '<'
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					// Si el nombre es "asignatura",
					// empieza una nueva y mostramos su id
					// Si no, memorizamos el nombre para mostrar después
					etiquetaActual = qName;
					if (etiquetaActual == "asignatura")
						System.out.println("Asignatura: " + attributes.getValue("id"));
				}

				// Obtiene los datos entre '<' y '>'
				public void characters(char ch[], int start, int length) throws SAXException {
					contenido = new String(ch, start, length);
				}

				// Llamado al encontrar un fin de etiqueta: '>'
				public void endElement(String uri, String localName, String qName) throws SAXException {

					if (etiquetaActual != "") {
						System.out.println(" " + etiquetaActual + ": " + contenido);
						etiquetaActual = "";
					}
				}
			};
			// Cuerpo de la función: trata de analizar el fichero deseado
			// Llamará a startElement(), endElement() y character()
			saxParser.parse("C:/ficheros/asignaturas.xml", manejadorEventos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void procesarAsignatura() {

		try {
			// Este bloque consigue que en "doc" tengamos un xml correcto
			File inputFile = new File("C:/ficheros/asignaturas.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			///
			System.out.println("Elemento base : " + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("asignatura");
			System.out.println();
			System.out.println("Recorriendo asignaturas...");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Codigo: " + eElement.getAttribute("id"));
					System.out.println("Nombre: " + eElement.getElementsByTagName("nombre").item(0).getTextContent());
					System.out.println(
							"Ciclo: " + eElement.getElementsByTagName("cicloFormativo").item(0).getTextContent());
					System.out.println("Curso: " + eElement.getElementsByTagName("curso").item(0).getTextContent());
					System.out
							.println("Profesor: " + eElement.getElementsByTagName("profesor").item(0).getTextContent());
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public static List<Noticia> procesarMarcaOnline(String url) {

		List<Noticia> noticias = new ArrayList<Noticia>();
		
		try {
			// Este bloque consigue que en "doc" tengamos un xml correcto
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(url);
			doc.getDocumentElement().normalize();
			///
			System.out.println("Elemento base : " + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("item");
			System.out.println();
			System.out.println("Recorriendo noticias...");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
										
					String foto = eElement.getElementsByTagName("media:content")
							.item(0)!=null?
							eElement.getElementsByTagName("media:content")
							.item(0).getAttributes().getNamedItem("url").getTextContent()
							:"";
					
					noticias.add(new Noticia(
							eElement.getElementsByTagName("title").item(0).getTextContent(),
							eElement.getElementsByTagName("guid").item(0).getTextContent(),
							foto
							));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return noticias;

	}
	
	

	
}
