package com.fran.hibernateanotaciones2;

import java.util.Scanner;

import com.fran.hibernateanotaciones2.utilidades.HibernateUtils;

public class App {
	
	static Scanner sc;
	
    public static void main(String[] args) {
    	sc = new Scanner(System.in);
		HibernateUtils.quitarLog();
		if (!HibernateUtils.abrirConexion()) {
			System.out.println("Error al abrir la conexión");
			return;
		}
		//tratarMenu();
		System.out.println("Conexión correcta");
		
		sc.close();
		HibernateUtils.cerrarConexion();
    }
}
