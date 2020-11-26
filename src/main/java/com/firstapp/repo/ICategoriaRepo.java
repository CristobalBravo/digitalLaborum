package com.firstapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.firstapp.models.Categoria;

public interface ICategoriaRepo extends JpaRepository<Categoria, Integer> {

	@Query(value="select * from categoria where nombre like %?1%", nativeQuery=true)
	List <Categoria> findByNombreWith(String nombre);
}
