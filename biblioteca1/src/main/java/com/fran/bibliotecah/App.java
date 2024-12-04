package com.fran.bibliotecah;



import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App 
{
	
	public static void quitarLog() {
		Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.OFF);
	}
	
    public static void main( String[] args )
    {
    	//quitarLog(); 
    	
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
		if (session != null) {
			System.out.println("Conexión establecida");
		} else {
			System.out.println("Error en la conexión");
		}
    }
}
