package com.fran.springboot.backend.mvc.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fran.springboot.backend.mvc.models.dao.IclienteDAO;
import com.fran.springboot.backend.mvc.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IclienteDAO clienteDAO;
	
	@Override
	@Transactional(readOnly = true)  // Solo lectura lo que haremos en el método
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDAO.save(cliente);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		clienteDAO.deleteById(id);		
	}
	
	@Override
	@Transactional
	public void delete(Cliente cliente) {
		clienteDAO.delete(cliente);
	}

}
