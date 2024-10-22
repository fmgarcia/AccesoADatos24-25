package com.fran.jdbc.entidades;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Evento {
	
	private int id;
	private String nombre;
	private String descripcion;
	private double precio;
	private LocalDate fecha;

}
