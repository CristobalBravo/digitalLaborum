package com.firstapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.firstapp.models.Rol;

public interface IRolRepo extends JpaRepository<Rol, Integer>{
	
	@Query(value="select * from rol where nombre like %?1%", nativeQuery=true)
	List <Rol> findByNombreWith(String nombre);
}
