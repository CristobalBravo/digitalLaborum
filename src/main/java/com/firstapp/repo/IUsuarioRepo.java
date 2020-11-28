package com.firstapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.firstapp.models.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario, Integer> {
	
	@Query(value="select * from usuario where user_name= ?1", nativeQuery=true)
	Usuario findByuser_name(String user_name);
}
