package com.firstapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstapp.models.Profesion;

public interface IProfesionRepo extends JpaRepository<Profesion, Integer> {

}
