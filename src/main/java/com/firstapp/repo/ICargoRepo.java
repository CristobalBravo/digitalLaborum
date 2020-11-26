package com.firstapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.firstapp.models.Cargo;

public interface ICargoRepo extends JpaRepository<Cargo, Integer> {
	
	@Query(value="select * from cargo where nombre like %?1%", nativeQuery=true)
	List <Cargo> findByNombreWith(String nombre);
}
