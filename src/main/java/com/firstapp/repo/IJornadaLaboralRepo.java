package com.firstapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.firstapp.models.JornadaLaboral;

public interface IJornadaLaboralRepo extends JpaRepository<JornadaLaboral, Integer> {
	
	@Query(value="select * from jornada_laboral where nombre like %?1%", nativeQuery=true)
	List <JornadaLaboral> findByNombreWith(String nombre);

}
