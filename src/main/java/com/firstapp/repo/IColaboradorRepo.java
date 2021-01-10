package com.firstapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.firstapp.models.Colaborador;

public interface IColaboradorRepo extends JpaRepository<Colaborador, Integer> {

	@Query(value="select * from colaborador where usuario_id= ?1", nativeQuery=true)
	Colaborador findByUsuarioId(int usuario_id);
}
