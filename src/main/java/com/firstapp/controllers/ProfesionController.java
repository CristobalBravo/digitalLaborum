package com.firstapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.firstapp.models.Profesion;
import com.firstapp.repo.IProfesionRepo;
@Controller
@RequestMapping("/profesion")
public class ProfesionController {

	@Autowired
	private IProfesionRepo profesionRepo;
	
	@GetMapping("/")
	public String listar(Model model) {
		model.addAttribute("profesiones", profesionRepo.findAll());
		return "profesion/listar";
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
		model.addAttribute("titulo", "Crear Profesion");
		model.addAttribute("nombreButton", "Crear");
		model.addAttribute("profesion", new Profesion());
		return "profesion/crear";
	}
	
	@PostMapping("/")
	public String guardar(Model model, Profesion profesion) {
		profesionRepo.save(profesion);
		return "redirect:/profesion/";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar (Model model, @PathVariable int id) {
		profesionRepo.deleteById(id);
		return "redirect:/profesion/";
	}
	
	@GetMapping("/editar/{id}")
	public String editar (Model model, @PathVariable int id) {
		model.addAttribute("titulo", "Editar Profesion");
		model.addAttribute("nombreButton", "Editar");
		model.addAttribute("profesion", profesionRepo.findById(id));
		return "profesion/crear";
	}
	@PostMapping("/buscar")
	public String editar (Model model, @RequestParam String nombre) {
		List<Profesion> profesiones = profesionRepo.findByNombreWith(nombre);
		model.addAttribute("profesiones", profesiones);
		return "profesion/listar";
	}
	
	
}
