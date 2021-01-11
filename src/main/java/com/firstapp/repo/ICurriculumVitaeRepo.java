package com.firstapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstapp.models.CurriculumVitae;



public interface ICurriculumVitaeRepo extends JpaRepository<CurriculumVitae, Integer>  {

	
}
