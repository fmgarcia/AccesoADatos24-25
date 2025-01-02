package com.fran.ejemplo1dist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/params")
public class RequestParamController {
	
	@GetMapping({"/","","/index","/home"})
	public String index(Model model) {
		model.addAttribute("titulo", "Prueba de RequestParam");
		model.addAttribute("mensaje", "Mensaje dentro de params");
		return "params/index";
	}
	
	@GetMapping("/string")
	public String ver(@RequestParam(required=false, defaultValue="texto de ejemplo") String texto,Model model) {
		model.addAttribute("titulo", "Recibir parámetros de la ruta @PathVariable");
		model.addAttribute("mensaje", texto);
		return "params/ver";
	}
	

}
