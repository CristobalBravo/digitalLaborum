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
import com.firstapp.models.Categoria;
import com.firstapp.repo.ICategoriaRepo;

@Controller
@RequestMapping("/configuracion/categoria")
public class CategoriaController {
	
	@Autowired
	private ICategoriaRepo categoriaRepo;
	
	@GetMapping("/")
	public String listar(Model model) {
		model.addAttribute("categorias", categoriaRepo.findAll());
		return "categoria/listar";
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
		model.addAttribute("categoria", new Categoria());
		model.addAttribute("titulo", "Crear Categoria");
		model.addAttribute("nombreButton", "Crear");
		return "categoria/crear";
	}
	
	@PostMapping("/")
	public String guardar(Model model, Categoria categoria) {
		categoriaRepo.save(categoria);
		return "redirect:/categoria/";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar (Model model, @PathVariable int id) {
		categoriaRepo.deleteById(id);
		return "redirect:/categoria/";
	}
	
	@GetMapping("/editar/{id}")
	public String editar (Model model, @PathVariable int id) {
		model.addAttribute("titulo", "Editar Categoria");
		model.addAttribute("nombreButton", "Editar");
		model.addAttribute("categoria", categoriaRepo.findById(id));
		return "categoria/crear";
	}
	
	@PostMapping("/buscar")
	public String editar (Model model, @RequestParam String nombre) {
		List<Categoria> categorias = categoriaRepo.findByNombreWith(nombre);
		model.addAttribute("categorias", categorias);
		return "categoria/listar";
	}
	
}
