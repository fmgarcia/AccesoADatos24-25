package com.fran.hibernateanotaciones3.entidades;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Libros generated by hbm2java
 */
@Entity
@Table(name = "libros")
@NamedQueries({
	 @NamedQuery(name = "Libros.findAll",
	 query = "SELECT l FROM Libros l"),
	 @NamedQuery(name = "Libros.findById",
	 query = "SELECT l FROM Libros l WHERE l.id = :id"),
	 @NamedQuery(name = "Libros.findByTitulo",
	 query = "SELECT l FROM Libros l WHERE l.titulo = :titulo")})
public class Libros implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String titulo;
	private Set<Escribir> escribirs = new HashSet<Escribir>(0);

	public Libros() {
	}

	public Libros(int id) {
		this.id = id;
	}

	public Libros(int id, String titulo, Set<Escribir> escribirs) {
		this.id = id;
		this.titulo = titulo;
		this.escribirs = escribirs;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "titulo", length = 60)
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "libros")
	public Set<Escribir> getEscribirs() {
		return this.escribirs;
	}

	public void setEscribirs(Set<Escribir> escribirs) {
		this.escribirs = escribirs;
	}

	@Override
	public String toString() {
		return "Libros [id=" + id + ", titulo=" + titulo + "]";
	}
	

}