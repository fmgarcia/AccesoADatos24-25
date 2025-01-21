package com.fran.springboot.backend.mvc.controllers;

import java.time.LocalDate;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fran.springboot.backend.mvc.models.entity.Cliente;
import com.fran.springboot.backend.mvc.models.services.ClienteServiceImpl;

@Controller
@RequestMapping("/clientes")
public class ClienteViewController {
	
	@Autowired
	ClienteServiceImpl clienteService = new ClienteServiceImpl();
	
	@GetMapping("")
	public String indexClientes(Model model)
	{
		model.addAttribute("titulo", "Clientes");
		return "clientes/index";
	}
	
	@GetMapping("/listar")
	public String listarClientes(Model model)
	{
		model.addAttribute("titulo", "Listar clientes");
		model.addAttribute("clientes", clienteService.findAll());
		return "clientes/listar";
	}
	
	@GetMapping("/anyadir")
	public String addCliente(Model model)
	{
		model.addAttribute("titulo", "Añadir cliente");
		model.addAttribute("nombre", "Nombre:");
        model.addAttribute("apellido", "Apellido:");
		model.addAttribute("email", "Correo:");
		//model.addAttribute("createAt", "Fecha de creación:");
		model.addAttribute("cliente", new Cliente());
		return "clientes/anyadir";
	}
	
	@RequestMapping("/create")
    public ModelAndView createCliente(@Valid Cliente cliente, BindingResult result, Model mod) {
        ModelAndView model = new ModelAndView();
        boolean exists = false;
        cliente.setCreateAt(LocalDate.now());
        model.addObject("cliente", cliente);
        
        if(!result.hasErrors())
        {
        	for(Cliente c: clienteService.findAll())
        	{
        		if(c.getId() == cliente.getId())
        		{
        			exists = true;
        			break;
        		}
        	}
        	
        	model.setViewName("ready");
        	
        	if(!exists)
        	{
        		mod.addAttribute("resultado", "Cliente creado");
        		clienteService.save(cliente);
        	}
        	else
        	{
        		mod.addAttribute("resultado", "El id ya existe");
        	}
        }
        else
        {
        	model.setViewName("clientes/anyadir");
        }
        mod.addAttribute("nombre", "Nombre:");
        mod.addAttribute("apellido", "Apellido:");
		mod.addAttribute("email", "Correo:");
		mod.addAttribute("createAt", "Fecha de creación:");
        return model;
    }

}
