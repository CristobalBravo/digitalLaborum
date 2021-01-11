package com.firstapp.controllers;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.firstapp.models.Colaborador;
import com.firstapp.models.OfertaLaboral;
import com.firstapp.models.Postulacion;
import com.firstapp.models.Usuario;
import com.firstapp.repo.IColaboradorRepo;
import com.firstapp.repo.IOfertaLaboralRepo;
import com.firstapp.repo.IPostulacionRepo;
import com.firstapp.repo.IUsuarioRepo;

@Controller
@RequestMapping("/postulacion")
public class PostulacionController {

	@Autowired
	private IColaboradorRepo colaboradorrepo;
	
	@Autowired 
	private IUsuarioRepo usuarioRepo;
	
	@Autowired
	private IOfertaLaboralRepo ofertaLaboralRepo;
	
	@Autowired
	private IPostulacionRepo postulacionrepo;
	
	@GetMapping("/agregarpostulacion/{id}")
	public String agregarPostulacion(Model model, @PathVariable int id) {
		LocalDate localDate = LocalDate.now();
		Date date = Date.valueOf(localDate);
		OfertaLaboral ofertaLaboral= ofertaLaboralRepo.findById(id).get();
		Colaborador colaborador = datosColaborador();
		Postulacion postulacion = new Postulacion();
		postulacion.setColaborador(colaborador);
		postulacion.setDate(date);
		postulacion.setOfertaLaboral(ofertaLaboral);
		postulacionrepo.save(postulacion);
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
