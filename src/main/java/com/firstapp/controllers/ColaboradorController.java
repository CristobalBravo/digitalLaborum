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
import com.firstapp.models.CurriculumVitae;
import com.firstapp.models.Empleador;
import com.firstapp.models.Idioma;
import com.firstapp.models.JornadaLaboral;
import com.firstapp.models.LenguajeProgramacion;
import com.firstapp.models.NivelEducacional;
import com.firstapp.models.Profesion;
import com.firstapp.models.Usuario;
import com.firstapp.repo.IColaboradorRepo;
import com.firstapp.repo.ICurriculumVitaeRepo;
import com.firstapp.repo.IIdiomaRepo;
import com.firstapp.repo.IJornadaLaboralRepo;
import com.firstapp.repo.ILenguajeProgramacionRepo;
import com.firstapp.repo.INivelEducacionRepo;
import com.firstapp.repo.IProfesionRepo;
import com.firstapp.repo.IUsuarioRepo;

@Controller
@RequestMapping("/colaborador")
public class ColaboradorController {
	
	@Autowired
	private IColaboradorRepo colaboradorrepo;
	
	@Autowired
	private ICurriculumVitaeRepo curriculumrepo;

	@Autowired 
	private IUsuarioRepo usuarioRepo;
	
	@Autowired
	private IProfesionRepo profesionrepo;
	
	@Autowired
	private IJornadaLaboralRepo jornadarepo;
	
	@Autowired
	private INivelEducacionRepo niveleducacionrepo;
	
	@Autowired
	private ILenguajeProgramacionRepo lenguajerepo;
	
	@Autowired
	private IIdiomaRepo idiomarepo;
	
	@GetMapping("/")
	public String informacion(Model model) {
		Colaborador colaborador = datosColaborador();
		if(colaborador!=null) {
			model.addAttribute("bottomName", "Editar");
			model.addAttribute("colaborador", colaborador);
			model.addAttribute("mostrar", true);
			model.addAttribute("mostrarCard", true);
			model.addAttribute("mostrarCardCurriculum", true);
			if(!colaborador.getProfesiones().isEmpty() ) {
				model.addAttribute("bottomName", "Editar");
				model.addAttribute("profesiones", colaborador.getProfesiones());
				model.addAttribute("mostrarProfesiones", true);
			}else {
				model.addAttribute("bottomName", "Agregar");
				model.addAttribute("mostrarProfesiones", false);
			}
			if(colaborador.getCurriculum() != null) {
				model.addAttribute("bottomCurriculumName", "Editar");
				model.addAttribute("curriculum", colaborador.getCurriculum());
				model.addAttribute("mostrarCurriculum", true);
			}else {
				model.addAttribute("bottomCurriculumName", "Agregar Curriculum");
				model.addAttribute("mostrarCurriculum", false);
			}
			if(!colaborador.getPostulaciones().isEmpty()) {
				model.addAttribute("postulaciones", colaborador.getPostulaciones());
				model.addAttribute("mostrarPostulaciones", true);
				model.addAttribute("mostrarCardPostulaciones", true);
			}else {
				model.addAttribute("mostrarPostulaciones", false);
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
	@GetMapping("/crear/curriculumvitae")
	public String agregarCV(Model model) {
		vistaCVAgregar(model);
		return "Colaborador/agregarcurriculum";
	}
	
	@PostMapping("/guardar/profesion")
	public String guardarProfesion(Model model,Profesion profesionEscogida) {
		Colaborador colaborador = new Colaborador();
		colaborador = datosColaborador();
		List<Profesion> profesionesList = colaborador.getProfesiones();
		
		for(int i=0; i<profesionesList.size();i++) {
			System.out.println("Prof: " + profesionesList.get(i).getId());
			if(profesionesList.get(i).getId() == profesionEscogida.getId()) {
				vistaAgregar(model);
				model.addAttribute("mostrarError", true);
				return "Colaborador/agregarprofesion";
			}
		}
		profesionesList.add(profesionEscogida);
		colaborador.setProfesiones(profesionesList);
		colaboradorrepo.save(colaborador);
		return "redirect:/colaborador/";
	}
	
	@PostMapping("/guardar/curriculum")
	public String guardarCV(Model model,CurriculumVitae curriculum) {
		Colaborador colaborador = new Colaborador();
		CurriculumVitae curriculumVitae = new CurriculumVitae();
		colaborador = datosColaborador();
		/*
		curriculumVitae = colaborador.getCurriculum();
		
		List<Idioma> idiomas = curriculumVitae.getIdiomas();
		List<LenguajeProgramacion> lenguajes = curriculumVitae.getLenguajesProgramacion();
        */
		/*
		for(int i=0; i<idiomas.size();i++) {		
			if(idiomas.get(i).getId() == idiomaEscogido.getId()) {
				vistaCVAgregar(model);
				model.addAttribute("mostrarErrorIdioma", true);
				return "Colaborador/agregarcurriculum";
			}
		}*/
		/*
		for(int i=0; i<lenguajes.size();i++) {		
			if(lenguajes.get(i).getId() == lenguajeEscogido.getId()) {
				vistaCVAgregar(model);
				model.addAttribute("mostrarErrorLenguaje", true);
				return "Colaborador/agregarcurriculum";
			}
		}*/
		curriculum.setColaborador(colaborador);
		curriculumrepo.save(curriculum);
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
		model.addAttribute("titulo","Seleccione profesión");
	}
	
	private void vistaCVAgregar(Model model) {
		List<JornadaLaboral> jornadaLaboralList = jornadarepo.findAll();
		List<NivelEducacional> nivelEducacionalList = niveleducacionrepo.findAll();
		List<Idioma> idiomasList = idiomarepo.findAll();
		List<LenguajeProgramacion> lenguajesList = lenguajerepo.findAll();
		CurriculumVitae curriculum = new CurriculumVitae();
		model.addAttribute("jornadaLaboralList", jornadaLaboralList);
		model.addAttribute("nivelEducacionalList", nivelEducacionalList);
		model.addAttribute("idiomasList", idiomasList);
		model.addAttribute("lenguajesList", lenguajesList);
		model.addAttribute("curriculum", curriculum);
		model.addAttribute("titulo","Crear Curriculum");
	}
}
