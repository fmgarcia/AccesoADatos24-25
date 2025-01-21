package com.fran.springboot.backend.mvc.models.dto;

import java.io.Serializable;

public class ClienteDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String email;
	
	public ClienteDto() {
		super();
	}
	
	public ClienteDto(Long id, String nombre, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
