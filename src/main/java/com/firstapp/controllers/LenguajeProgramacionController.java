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

import com.firstapp.models.JornadaLaboral;
import com.firstapp.models.LenguajeProgramacion;
import com.firstapp.repo.ILenguajeProgramacionRepo;

@Controller
@RequestMapping("/configuracion/lenguajeProgramacion")
public class LenguajeProgramacionController {

	@Autowired
	private ILenguajeProgramacionRepo lenguajeProgramacionRepo;
	
	@GetMapping("/")
	public String listar(Model model) {
		model.addAttribute("lenguajeProgramacion", lenguajeProgramacionRepo.findAll());
		return "lenguajeProgramacion/listar";
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
		model.addAttribute("titulo", "Crear Lenguaje de Programacion");
		model.addAttribute("nombreButton", "Crear");
		model.addAttribute("lenguajeProgramacion", new LenguajeProgramacion());
		return "lenguajeProgramacion/crear";
	}
	
	@PostMapping("/")
	public String guardar(Model model, LenguajeProgramacion lenguajeProgramacion) {
		lenguajeProgramacionRepo.save(lenguajeProgramacion);
		return "redirect:/lenguajeProgramacion/";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar (Model model, @PathVariable int id) {
		lenguajeProgramacionRepo.deleteById(id);
		return "redirect:/lenguajeProgramacion/";
	}
	
	@GetMapping("/editar/{id}")
	public String editar (Model model, @PathVariable int id) {
		model.addAttribute("titulo", "Editar Lenguaje Programacion");
		model.addAttribute("nombreButton", "Editar");
		model.addAttribute("lenguajeProgramacion", lenguajeProgramacionRepo.findById(id));
		return "lenguajeProgramacion/crear";
	}
	@PostMapping("/buscar")
	public String editar (Model model, @RequestParam String nombre) {
		List<LenguajeProgramacion> lenguajesProgramacion = lenguajeProgramacionRepo.findByNombreWith(nombre);
		model.addAttribute("lenguajeProgramacion", lenguajesProgramacion);
		return "lenguajeProgramacion/listar";
	}

	
}
