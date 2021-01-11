package com.firstapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.firstapp.models.Empleador;
import com.firstapp.models.Usuario;
import com.firstapp.repo.IEmpleadorRepo;
import com.firstapp.repo.IUsuarioRepo;

@Controller
@RequestMapping("/empleador")
public class EmpleadorController {

	@Autowired
	private IEmpleadorRepo empleadorRepo;
	
	@Autowired 
	private IUsuarioRepo usuarioRepo;
	
	@GetMapping("/")
	public String informacion(Model model) {
		Empleador empleador = datosEmpleador();
		if(empleador!=null) {
			model.addAttribute("bottomName", "Editar");
			model.addAttribute("empleador", empleador);
			model.addAttribute("mostrar", true);
			
			if(!empleador.getOfertasLaborales().isEmpty()) {
				model.addAttribute("mostrarTabla", true);
				model.addAttribute("ofertaLaborales", empleador.getOfertasLaborales());
			}else {
				model.addAttribute("mostrarTabla", false);
			}
			
		}else {
			model.addAttribute("bottomName", "Agregar");
			model.addAttribute("mostrar", false);
			model.addAttribute("mostrarTabla", false);
		}
		
		return "Empleador/informacion";
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
		Empleador empleador = datosEmpleador();
		if(empleador!=null) {
			model.addAttribute("titulo", "Editar Empleador");
			model.addAttribute("empleador", empleador);
		}else {
			empleador = new Empleador();
			model.addAttribute("empleador", empleador);
			model.addAttribute("titulo", "Registrar Empleador");
		}
		
		return "Empleador/formulario";
	}
	@PostMapping("/")
	public String guardar(Model model, Empleador empleador) {
		Usuario usuario = usuarioLogeado();
		empleador.setUsuario(usuario);
		empleadorRepo.save(empleador);
		return "redirect:/empleador/";
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
	
	private Empleador datosEmpleador() {
		Usuario usuario = usuarioLogeado();
		int usuario_id= usuario.getId();
		Empleador empleador = empleadorRepo.findByUsuarioId(usuario_id);
		return empleador;
	}
}
