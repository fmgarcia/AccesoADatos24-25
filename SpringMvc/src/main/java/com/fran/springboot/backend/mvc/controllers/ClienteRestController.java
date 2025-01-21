package com.fran.springboot.backend.mvc.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fran.springboot.backend.mvc.models.dto.ClienteDto;
import com.fran.springboot.backend.mvc.models.entity.Cliente;
import com.fran.springboot.backend.mvc.models.services.IClienteService;

import jakarta.validation.Valid;

@CrossOrigin(origins = {"*"}) // Para permitir peticiones desde cualquier origen
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	// Inyectamos el servicio
	@Autowired
	private IClienteService clienteService;

	@GetMapping({"/clientes/", "/clientes"})
	public List<Cliente> listar() {
		return clienteService.findAll();
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (cliente == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	@PostMapping({"/clientes/", "/clientes"})
	public ResponseEntity<?> crear(@Valid @RequestBody Cliente cliente, BindingResult result) {
        Cliente clienteNuevo = null;
        Map<String, Object> response = new HashMap<>();
        
        if(result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        
        try {
        	cliente.setCreateAt(LocalDate.now());
            clienteNuevo = clienteService.save(cliente);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la inserción en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido creado con éxito!");
        response.put("cliente", clienteNuevo);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/clientes/{id}")	
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Cliente clienteBorrado = clienteService.findById(id);
		Map<String, Object> response = new HashMap<>();
		if (clienteBorrado == null) {
			response.put("mensaje", "Error: no se pudo borrar, el cliente ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			clienteService.deleteById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
		Cliente clienteActual = clienteService.findById(id);
		Cliente clienteActualizado = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
		
		if (clienteActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el cliente ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmail(cliente.getEmail());
			clienteActualizado = clienteService.save(clienteActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido actualizado con éxito!");
		response.put("cliente", clienteActualizado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/clientesdto")
	public List<ClienteDto> indexdto() {
	    List<Cliente> clientes = clienteService.findAll();
	    List<ClienteDto> clientesDTO = new ArrayList<>();
	    //ModelMapper modelMapper = new ModelMapper();
	    clientes.forEach(cliente -> {
	        //ClienteDTO clientedto = modelMapper.map(cliente, ClienteDto.class);
	        ClienteDto clientedto = new ClienteDto(cliente.getId(), cliente.getNombre(), cliente.getEmail());
	        clientesDTO.add(clientedto);
	    });
	    return clientesDTO;
	}
	
	@GetMapping("/clientesdto/{id}")
	public ResponseEntity<?> dto(@PathVariable Long id) {
	    Cliente cliente = null;
	    Map<String, Object> respuesta = new HashMap<String, Object>();
	    ClienteDto clienteDto = new ClienteDto();

	    try {
	        cliente = clienteService.findById(id);
	    } catch (Exception e) { // Base de datos inaccesible
	        respuesta.put("mensaje", "Error al realizar la consulta sobre la base de datos");
	        respuesta.put("error", e.getMessage().concat(" : ").concat(e.getStackTrace().toString()));
	        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    if (cliente == null) { // Hemos buscado un id que no existe
	        respuesta.put("mensaje", "El Identificador buscado no existe");
	        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
	    }

	    clienteDto.setId(cliente.getId());
	    clienteDto.setNombre(cliente.getNombre());
	    clienteDto.setEmail(cliente.getEmail());
	    return new ResponseEntity<ClienteDto>(clienteDto, HttpStatus.OK);
	}
	
	
}
