package com.fran.ejemplo1dist.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fran.ejemplo1dist.entities.Usuario;

@Controller
@RequestMapping("/app")
public class IndexController {
	
	@GetMapping({"/","","/index","/home"})
	public String index(Model model) {
		model.addAttribute("titulo", "Hola Spring Framework con Thymeleaf");
		model.addAttribute("mensaje", "Bienvenido al curso de Spring Framework 5");
		return "index";
	}
	
	@GetMapping("/perfil")
	public String perfil(Model model) {
		Usuario fran = new Usuario("Francisco", "García", "fran@iessanvicente.com");
		//Usuario fran = new Usuario("Francisco", "García");
		model.addAttribute("titulo", "Perfil del usuario");
		model.addAttribute("usuario", fran);
		return "perfil";
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {		
		model.addAttribute("titulo", "Listado de usuarios");
		return "listar";
	}
	
	@ModelAttribute("usuarios")
	public List<Usuario> getUsuarios(){
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(new Usuario("Francisco", "García"));
		usuarios.add(new Usuario("Daniel", "García", "dani@ua.es"));
		usuarios.add(new Usuario("Paco", "García", "paco@google.es"));
		usuarios.add(new Usuario("Consuelo", "López"));
		return usuarios;
	}
	

}
