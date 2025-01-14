package com.fran.springboot.backend.mvc.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fran.springboot.backend.mvc.models.entity.Cliente;
import com.fran.springboot.backend.mvc.models.services.IClienteService;

@CrossOrigin(origins = {"*"}) // Para permitir peticiones desde cualquier origen
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {
	
	// Inyectamos el servicio
	@Autowired
	private IClienteService clienteService;

	@GetMapping({"/", ""})
	public List<Cliente> listar() {
		return clienteService.findAll();
	}
	
	@GetMapping("/{id}")
	public Cliente ver(@PathVariable Long id) {
		return clienteService.findById(id);
	}
	
	@PostMapping({"/", ""})
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente crear(@RequestBody Cliente cliente) {
		cliente.setCreateAt(LocalDate.now());
		return clienteService.save(cliente);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		clienteService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente editar(@RequestBody Cliente cliente, @PathVariable Long id) {
		Cliente clienteActual = clienteService.findById(id);
		clienteActual.setNombre(cliente.getNombre());
		clienteActual.setApellido(cliente.getApellido());
		clienteActual.setEmail(cliente.getEmail());
		return clienteService.save(clienteActual);
	}
	
}
