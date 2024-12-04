package com.fran.hibernateanotaciones1.entidades;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "libros")
public class Libros implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Autores autores;
	private String titulo;

	public Libros() {
	}

	public Libros(int id) {
		this.id = id;
	}

	public Libros(int id, Autores autores, String titulo) {
		this.id = id;
		this.autores = autores;
		this.titulo = titulo;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codautor")
	public Autores getAutores() {
		return this.autores;
	}

	public void setAutores(Autores autores) {
		this.autores = autores;
	}

	@Column(name = "titulo", length = 60)
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Override
	public String toString() {
		return "id=" + id + ", autor=" + autores.getNombre() + ", titulo=" + titulo;
	}

}
