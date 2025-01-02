package com.fran.ejemplo1dist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/variables")
public class PathVariableController {
	
	@GetMapping({ "/", "", "/index", "/home" })
	public String index(Model model) {
		model.addAttribute("titulo", "Prueba de PathVariable");
		model.addAttribute("mensaje", "Mensaje dentro de variables");
		return "variables/index";
	}

	@GetMapping("/string/{texto}")
	public String ver(@PathVariable String texto, Model model) {
		model.addAttribute("titulo", "Recibir parámetros de la ruta @PathVariable");
		model.addAttribute("mensaje", texto);
		return "variables/ver";
	}

}
