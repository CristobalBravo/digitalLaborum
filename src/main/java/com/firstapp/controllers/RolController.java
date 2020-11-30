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

import com.firstapp.models.Rol;
import com.firstapp.repo.IRolRepo;

@Controller
@RequestMapping("/configuracion/rol")
public class RolController {

	@Autowired
	private IRolRepo rolrepo;
	
	@GetMapping("/")
	public String listar(Model model) {
		model.addAttribute("roles", rolrepo.findAll());
		return "rol/listar";
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
		model.addAttribute("rol", new Rol());
		model.addAttribute("titulo", "Crear Rol");
		model.addAttribute("nombreButton", "Crear");
		return "rol/crear";
	}
	
	@PostMapping("/")
	public String guardar(Model model, Rol rol) {
		rolrepo.save(rol);
		return "redirect:/rol/";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar (Model model, @PathVariable int id) {
		rolrepo.deleteById(id);
		return "redirect:/rol/";
	}
	
	@GetMapping("/editar/{id}")
	public String editar (Model model, @PathVariable int id) {
		model.addAttribute("titulo", "Editar Rol");
		model.addAttribute("nombreButton", "Editar");
		model.addAttribute("rol", rolrepo.findById(id));
		return "rol/crear";
	}
	
	@PostMapping("/buscar")
	public String editar (Model model, @RequestParam String nombre) {
		List<Rol> roles = rolrepo.findByNombreWith(nombre);
		model.addAttribute("roles", roles);
		return "rol/listar";
	}
}
