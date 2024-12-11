package com.fran.hibernateanotaciones3.entidades;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "autores")
public class Autores implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String cod;
	private String nombre;
	private Set<Escribir> escribirs = new HashSet<Escribir>(0);

	public Autores() {
	}

	public Autores(String cod) {
		this.cod = cod;
	}

	public Autores(String cod, String nombre, Set<Escribir> escribirs) {
		this.cod = cod;
		this.nombre = nombre;
		this.escribirs = escribirs;
	}

	@Id

	@Column(name = "cod", unique = true, nullable = false, length = 5)
	public String getCod() {
		return this.cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	@Column(name = "nombre", length = 60)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "autores")
	public Set<Escribir> getEscribirs() {
		return this.escribirs;
	}

	public void setEscribirs(Set<Escribir> escribirs) {
		this.escribirs = escribirs;
	}

}
