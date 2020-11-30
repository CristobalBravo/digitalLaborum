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

import com.firstapp.models.Cargo;
import com.firstapp.repo.ICargoRepo;

@Controller
@RequestMapping("/configuracion/cargo")
public class CargoController {
	
	@Autowired
	private ICargoRepo cargoRepo;
	
	@GetMapping("/")
	public String listar(Model model) {
		model.addAttribute("cargos", cargoRepo.findAll());
		return "cargo/listar";
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
		model.addAttribute("titulo", "Crear Cargo");
		model.addAttribute("nombreButton", "Crear");
		model.addAttribute("cargo", new Cargo());
		return "cargo/crear";
	}
	
	@PostMapping("/")
	public String guardar(Model model, Cargo cargo) {
		cargoRepo.save(cargo);
		return "redirect:/cargo/";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar (Model model, @PathVariable int id) {
		cargoRepo.deleteById(id);
		return "redirect:/cargo/";
	}
	
	@GetMapping("/editar/{id}")
	public String editar (Model model, @PathVariable int id) {
		model.addAttribute("titulo", "Editar Cargo");
		model.addAttribute("nombreButton", "Editar");
		model.addAttribute("cargo", cargoRepo.findById(id));
		return "cargo/crear";
	}
	
	@PostMapping("/buscar")
	public String editar (Model model, @RequestParam String nombre) {
		List<Cargo> cargos = cargoRepo.findByNombreWith(nombre);
		model.addAttribute("cargos", cargos);
		return "cargo/listar";
	}

}
