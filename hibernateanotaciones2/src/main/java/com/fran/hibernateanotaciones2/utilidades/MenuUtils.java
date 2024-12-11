package com.fran.hibernateanotaciones2.utilidades;

public class MenuUtils {
	
	public static String imprimirMenu(String... opciones) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < opciones.length; i++) {
			sb.append((i + 1) + ".- " + opciones[i] + "\n");
		}
		sb.append("0.- Salir\n");
		sb.append("Seleccione una opción: ");
		return sb.toString();
	}

}
