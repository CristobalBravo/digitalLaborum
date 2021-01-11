package com.firstapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.firstapp.models.Empleador;
import com.firstapp.models.OfertaLaboral;
import com.firstapp.models.Usuario;
import com.firstapp.repo.ICargoRepo;
import com.firstapp.repo.ICategoriaRepo;
import com.firstapp.repo.IEmpleadorRepo;
import com.firstapp.repo.IOfertaLaboralRepo;
import com.firstapp.repo.IUsuarioRepo;

@Controller
@RequestMapping("/ofertaLaboral")
public class OfertaLaboralController {

	@Autowired
	private IEmpleadorRepo empleadorRepo;
	
	@Autowired 
	private IUsuarioRepo usuarioRepo;
	
	@Autowired
	private IOfertaLaboralRepo ofertaLaboralRepo;
	
	@Autowired
	private ICategoriaRepo categoriaRepo;
	
	@Autowired
	private ICargoRepo cargoRepo;
	
	
	@GetMapping("/listar")
	public String listado(Model model) {
		model.addAttribute("ofertasLaborales", ofertaLaboralRepo.findAllDisponible());
		return "ofertaLaboral/listado";
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
		model.addAttribute("ofertaLaboral", new OfertaLaboral());
		model.addAttribute("cargos", cargoRepo.findAll());
		model.addAttribute("categorias", categoriaRepo.findAll());
		model.addAttribute("titulo", "Crear Oferta Laboral");
		model.addAttribute("nombreButton", "Crear");
		return "ofertaLaboral/formulario";
	}
	
	@GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable int id) {
		OfertaLaboral ofertaLaboral= ofertaLaboralRepo.findById(id).get();
		model.addAttribute("cargo", ofertaLaboral.getCargo().getNombre() );
		model.addAttribute("categoria", ofertaLaboral.getCategoria().getNombre());
		model.addAttribute("ofertaLaboral", ofertaLaboral );
		model.addAttribute("titulo", "Detalle Oferta Laboral");
		model.addAttribute("nombreButton", "Crear");
		return "ofertaLaboral/detalle";
	}
	
	@GetMapping("/postular/{id}")
	public String postular(Model model, @PathVariable int id) {
		OfertaLaboral ofertaLaboral= ofertaLaboralRepo.findById(id).get();
		model.addAttribute("cargo", ofertaLaboral.getCargo().getNombre() );
		model.addAttribute("categoria", ofertaLaboral.getCategoria().getNombre());
		model.addAttribute("ofertaLaboral", ofertaLaboral );
		model.addAttribute("empleador", ofertaLaboral.getEmpleador() );
		model.addAttribute("titulo", "Postulacion Oferta Laboral");
		model.addAttribute("nombreButton", "Crear");
		return "ofertaLaboral/postular";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(Model model, @PathVariable int id) {
		OfertaLaboral ofertaLaboral= ofertaLaboralRepo.findById(id).get();
		model.addAttribute("ofertaLaboral", ofertaLaboral);
		model.addAttribute("cargos", cargoRepo.findAll());
		model.addAttribute("categorias", categoriaRepo.findAll());
		model.addAttribute("titulo", "Editar Oferta Laboral");
		model.addAttribute("nombreButton", "editar");
		return "ofertaLaboral/formulario";
	}
	
	@PostMapping("/")
	public String guardar(Model model, OfertaLaboral ofertaLaboral) {
		Empleador empleador = datosEmpleador();
		ofertaLaboral.setEmpleador(empleador);
		ofertaLaboralRepo.save(ofertaLaboral);
		return "redirect:/empleador/";
	}
	
	@PostMapping("/buscar")
	public String buscar (Model model, @RequestParam String nombre) {
		Empleador empleador = datosEmpleador();
		model.addAttribute("empleador", empleador);
		List<OfertaLaboral> ofertasLaborales = ofertaLaboralRepo.findByNombreWith(nombre);
		model.addAttribute("ofertaLaborales", ofertasLaborales);
		model.addAttribute("mostrarTabla", true);
		return "Empleador/informacion";
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


