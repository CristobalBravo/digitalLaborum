package com.firstapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.firstapp.models.NivelEducacional;

public interface INivelEducacionRepo extends JpaRepository<NivelEducacional, Integer> {
	
	@Query(value="select * from nivel_educacional where nombre like %?1%", nativeQuery=true)
	List <NivelEducacional> findByNombreWith(String nombre);
}
