package com.firstapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.firstapp.models.Profesion;

public interface IProfesionRepo extends JpaRepository<Profesion, Integer> {

	@Query(value="select * from profesion where nombre like %?1%", nativeQuery=true)
	List <Profesion> findByNombreWith(String nombre);
}
