package com.firstapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.firstapp.models.NivelEducacional;
import com.firstapp.repo.INivelEducacionRepo;
@Controller
@RequestMapping("/nivelEducacional")
public class NivelEducacionalController {

	@Autowired
	private INivelEducacionRepo nivelEduacionalRepo;
	
	@GetMapping("/")
	public String listar(Model model) {
		model.addAttribute("nivelesEducacionales", nivelEduacionalRepo.findAll());
		return "nivelEducacional/listar";
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
		model.addAttribute("nivelEducacional", new NivelEducacional());
		return "nivelEducacional/crear";
	}
	
	@PostMapping("/")
	public String guardar(Model model,NivelEducacional nivelEduacional) {
		nivelEduacionalRepo.save(nivelEduacional);
		return "redirect:/nivelEducacional/";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar (Model model, @PathVariable int id) {
		nivelEduacionalRepo.deleteById(id);
		return "redirect:/nivelEducacional/";
	}
	
	@GetMapping("/editar/{id}")
	public String editar (Model model, @PathVariable int id) {
		model.addAttribute("nivelEducacional", nivelEduacionalRepo.findById(id));
		return "nivelEducacional/crear";
	}
	
}
