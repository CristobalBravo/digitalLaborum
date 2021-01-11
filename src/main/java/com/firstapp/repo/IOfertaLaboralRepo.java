package com.firstapp.repo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.firstapp.models.OfertaLaboral;

public interface IOfertaLaboralRepo extends JpaRepository<OfertaLaboral, Integer> {
	
	@Query(value="select * from oferta_laboral where nombre like %?1%", nativeQuery=true)
	List <OfertaLaboral> findByNombreWith(String nombre);
	
	@Query(value="select * from oferta_laboral where estado = 1", nativeQuery=true)
	Collection<OfertaLaboral> findAllDisponible();

}
