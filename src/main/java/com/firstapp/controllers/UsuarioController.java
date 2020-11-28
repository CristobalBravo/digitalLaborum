package com.firstapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.firstapp.models.Usuario;
import com.firstapp.repo.IUsuarioRepo;

@RequestMapping("/registro")
@Controller
public class UsuarioController {
	
	@Autowired
	private IUsuarioRepo usuarioRepo;
	@Autowired 
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("/")
	public String registro(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "Usuario/Registro";
	}
	@PostMapping("/")
	public String guardar(Model model, Usuario usuario ) {
		String pwd = usuario.getPassword();
		String pwdEncoder= encoder.encode(pwd);
		usuario.setPassword(pwdEncoder);
		usuarioRepo.save(usuario);
		return "redirect:/login/";
	}


}
