package com.firstapp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.firstapp.models.Colaborador;
import com.firstapp.models.Empleador;
import com.firstapp.models.Profesion;
import com.firstapp.models.Usuario;
import com.firstapp.repo.IColaboradorRepo;
import com.firstapp.repo.IProfesionRepo;
import com.firstapp.repo.IUsuarioRepo;

@Controller
@RequestMapping("/colaborador")
public class ColaboradorController {
	
	@Autowired
	private IColaboradorRepo colaboradorrepo;

	@Autowired 
	private IUsuarioRepo usuarioRepo;
	
	@Autowired
	private IProfesionRepo profesionrepo;
	
	@GetMapping("/")
	public String informacion(Model model) {
		Colaborador colaborador = datosColaborador();
		if(colaborador!=null) {
			model.addAttribute("bottomName", "Editar");
			model.addAttribute("colaborador", colaborador);
			model.addAttribute("mostrar", true);
			model.addAttribute("mostrarCard", true);
			if(!colaborador.getProfesiones().isEmpty() ) {
				model.addAttribute("bottomName", "Editar");
				model.addAttribute("profesiones", colaborador.getProfesiones());
				model.addAttribute("mostrarProfesiones", true);
			}else {
				model.addAttribute("bottomName", "Agregar");
				model.addAttribute("mostrarProfesiones", false);

				System.out.println("NO Hay Profesiones --------------------------------");
			}
		}else {
			model.addAttribute("bottomName", "Agregar");
			model.addAttribute("mostrar", false);
			model.addAttribute("mostrarCard", false);
		}
		
		
		
		return "Colaborador/informacion";
	}
	
	@GetMapping("/crear/profesion")
	public String agregarProfesion(Model model) {
		vistaAgregar(model);
		return "Colaborador/agregarprofesion";
	}
	
	@PostMapping("/guardar/profesion")
	public String guardarProfesion(Model model,Profesion profesionEscogida) {
		Colaborador colaborador = new Colaborador();
		colaborador = datosColaborador();
		List<Profesion> profesionesList = colaborador.getProfesiones();
		profesionesList.add(profesionEscogida);
		for(int i=0; i<profesionesList.size();i++) {		
			if(profesionesList.get(i).getId() == profesionEscogida.getId()) {
				vistaAgregar(model);
				model.addAttribute("mostrarError", true);
				return "Colaborador/agregarprofesion";
			}
		}
		colaborador.setProfesiones(profesionesList);
		colaboradorrepo.save(colaborador);
		return "redirect:/colaborador/";
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
		Colaborador colaborador = datosColaborador();
		if(colaborador!=null) {
			model.addAttribute("titulo", "Editar Colaborador");
			model.addAttribute("colaborador", colaborador);
		}else {
			colaborador = new Colaborador();
			model.addAttribute("colaborador", colaborador);
			model.addAttribute("titulo", "Registrar Colaborador");
		}
		
		return "Colaborador/formulario";
	}
	@PostMapping("/")
	public String guardar(Model model, Colaborador colaborador) {
		Usuario usuario = usuarioLogeado();
		colaborador.setUsuario(usuario);
		colaboradorrepo.save(colaborador);
		return "redirect:/colaborador/";
	}
	
	
	private Usuario usuarioLogeado() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		String userName = userDetails.getUsername();
		Usuario usuario= usuarioRepo.findByuser_name(userName);
		return usuario;
	}
	
	private Colaborador datosColaborador() {
		Usuario usuario = usuarioLogeado();
		int usuario_id= usuario.getId();
		Colaborador colaborador = colaboradorrepo.findByUsuarioId(usuario_id);
		return colaborador;
	}
	
	private void vistaAgregar(Model model) {
		List<Profesion> profesiones = profesionrepo.findAll();
		Profesion profesion = new Profesion();
		model.addAttribute("profesiones", profesiones);
		model.addAttribute("profesion", profesion);
		model.addAttribute("titulo","Seleccione profesion");
	}
	
}
