package com.fran.ejemplo1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class IndexController {
	
	@GetMapping({"/", "/index","","/home"})
	public String index(Model model) {
		model.addAttribute("titulo", "Hola Mundo con Spring Boot");
		model.addAttribute("mensaje", "Bienvenido a mi página web");
		return "index";  // This is the name of the template file
	}

}
