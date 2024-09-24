package com.fran.ficheros.entidades;

import java.io.Serializable;
import java.util.Objects;

public class Alumno implements Serializable {
	
	private static final long serialVersionUID = 2L;
	private String curso;
	private String nombre;
	private double nota;
		
	public Alumno() {
		super();
	}

	public Alumno(String curso, String nombre, double nota) {
		super();
		this.curso = curso;
		this.nombre = nombre;
		this.nota = nota;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Alumno [curso=" + curso + ", nombre=" + nombre + ", nota=" + nota + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(curso, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(curso, other.curso) && Objects.equals(nombre, other.nombre);
	}


}
