package com.firstapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.firstapp.models.Usuario;
import com.firstapp.repo.IUsuarioRepo;

@Service
public class UsuarioService implements UserDetailsService{
	
	@Autowired
	private IUsuarioRepo usuarioRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario =usuarioRepo.findByuser_name(username);
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));
		UserDetails userDetails= new User(usuario.getUser_name(),usuario.getPassword(),roles);
		return userDetails;
	}

}
