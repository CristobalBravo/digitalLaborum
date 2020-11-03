package com.firstapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstapp.models.NivelEducacional;

public interface INivelEducacionRepo extends JpaRepository<NivelEducacional, Integer> {

}
