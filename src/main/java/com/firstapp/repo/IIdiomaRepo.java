package com.firstapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.firstapp.models.Idioma;

public interface IIdiomaRepo extends JpaRepository<Idioma, Integer>{
	@Query(value="select * from idioma where nombre like %?1%", nativeQuery=true)
	List <Idioma> findByNombreWith(String nombre);
}
