package com.firstapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.firstapp.models.JornadaLaboral;
import com.firstapp.repo.IJornadaLaboralRepo;

@Controller
@RequestMapping("/jornadaLaboral")
public class JornadaLaboralController {
	
	@Autowired
	private IJornadaLaboralRepo jornadaLaboralRepo;
	
	@GetMapping("/")
	public String listar(Model model) {
		model.addAttribute("jornadaLaborales", jornadaLaboralRepo.findAll());
		return "jornadaLaboral/listar";
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
		model.addAttribute("jornadaLaboral", new JornadaLaboral());
		return "jornadaLaboral/crear";
	}
	
	@PostMapping("/")
	public String guardar(Model model, JornadaLaboral jornadaLaboral) {
		jornadaLaboralRepo.save(jornadaLaboral);
		return "redirect:/jornadaLaboral/";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar (Model model, @PathVariable int id) {
		jornadaLaboralRepo.deleteById(id);
		return "redirect:/jornadaLaboral/";
	}
	
	@GetMapping("/editar/{id}")
	public String editar (Model model, @PathVariable int id) {
		model.addAttribute("jornadaLaboral", jornadaLaboralRepo.findById(id));
		return "jornadaLaboral/crear";
	}

}
