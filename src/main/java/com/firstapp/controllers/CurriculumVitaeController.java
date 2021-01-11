package com.firstapp.controllers;

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
import com.firstapp.models.CurriculumVitae;
import com.firstapp.models.Profesion;
import com.firstapp.models.Usuario;
import com.firstapp.repo.IColaboradorRepo;
import com.firstapp.repo.ICurriculumVitaeRepo;
import com.firstapp.repo.IUsuarioRepo;

@Controller
@RequestMapping("/curriculumvitae")
public class CurriculumVitaeController {
	
	@Autowired
	private IColaboradorRepo colaboradorrepo;

	@Autowired 
	private IUsuarioRepo usuarioRepo;
	
	@Autowired
	private ICurriculumVitaeRepo curriculumRepo;
	
	@GetMapping("/crear")
	public String crear(Model model) {
		CurriculumVitae curriculum = new CurriculumVitae();
		model.addAttribute("curriculum", curriculum);
		model.addAttribute("titulo", "Crear Curriculum");
		
		return "CurriculumVitae/formulario";
	}
	@PostMapping("/")
	public String guardar(Model model, CurriculumVitae curriculum) {
		Colaborador colaborador = datosColaborador();
		curriculum.setColaborador(colaborador);
		curriculumRepo.save(curriculum);
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
}
