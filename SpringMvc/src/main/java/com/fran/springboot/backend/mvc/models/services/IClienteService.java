package com.fran.springboot.backend.mvc.models.services;

import java.util.List;

import com.fran.springboot.backend.mvc.models.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	public Cliente findById(Long id);
	public Cliente save(Cliente cliente);
	public void deleteById(Long id);
	public void delete(Cliente cliente);

}
