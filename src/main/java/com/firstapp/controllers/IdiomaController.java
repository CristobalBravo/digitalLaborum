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

import com.firstapp.models.Idioma;
import com.firstapp.repo.IIdiomaRepo;


	@Controller
	@RequestMapping("/idioma")
	public class IdiomaController {

		@Autowired
		private IIdiomaRepo idiomaRepo;
		
		@GetMapping("/")
		public String listar(Model model) {
			model.addAttribute("idiomas", idiomaRepo.findAll());
			return "idioma/listar";
		}
		
		@GetMapping("/crear")
		public String crear(Model model) {
			model.addAttribute("titulo", "Crear Idioma");
			model.addAttribute("nombreButton", "Crear");
			model.addAttribute("idioma", new Idioma());
			return "idioma/crear";
		}
		
		@PostMapping("/")
		public String guardar(Model model, Idioma idioma) {
			idiomaRepo.save(idioma);
			return "redirect:/idioma/";
		}
		
		@GetMapping("/eliminar/{id}")
		public String eliminar (Model model, @PathVariable int id) {
			idiomaRepo.deleteById(id);
			return "redirect:/idioma/";
		}
		
		@GetMapping("/editar/{id}")
		public String editar (Model model, @PathVariable int id) {
			model.addAttribute("titulo", "Editar Idioma");
			model.addAttribute("nombreButton", "Editar");
			model.addAttribute("idioma", idiomaRepo.findById(id));
			return "idioma/crear";
		}
		@PostMapping("/buscar")
		public String editar (Model model, @RequestParam String nombre) {
			List<Idioma> idiomas = idiomaRepo.findByNombreWith(nombre);
			model.addAttribute("idiomas", idiomas);
			return "idioma/listar";
		}

		
	}


