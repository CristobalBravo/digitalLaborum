package com.firstapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.firstapp.models.Empleador;

public interface IEmpleadorRepo extends JpaRepository<Empleador, Integer> {

	@Query(value="select * from empleador where usuario_id= ?1", nativeQuery=true)
	Empleador findByUsuarioId(int usuario_id);
}