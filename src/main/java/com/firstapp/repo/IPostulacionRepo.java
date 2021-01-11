package com.firstapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.firstapp.models.Postulacion;
import com.firstapp.models.Profesion;

public interface IPostulacionRepo extends JpaRepository<Postulacion, Integer>{

	@Query(value="select * from postulacion where nombre like %?1%", nativeQuery=true)
	List <Postulacion> findByNombreWith(String nombre);
}
