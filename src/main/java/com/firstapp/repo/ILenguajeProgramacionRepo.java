package com.firstapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.firstapp.models.LenguajeProgramacion;


public interface ILenguajeProgramacionRepo extends JpaRepository<LenguajeProgramacion, Integer>{

	@Query(value="select * from lenguaje_programacion where nombre like %?1%", nativeQuery=true)
	List <LenguajeProgramacion>  findByNombreWith(String nombre);

}
