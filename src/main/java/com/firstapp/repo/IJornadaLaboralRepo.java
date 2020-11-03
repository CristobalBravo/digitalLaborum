package com.firstapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstapp.models.JornadaLaboral;

public interface IJornadaLaboralRepo extends JpaRepository<JornadaLaboral, Integer> {

}
