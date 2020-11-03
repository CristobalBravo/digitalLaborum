package com.firstapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstapp.models.Categoria;

public interface ICategoriaRepo extends JpaRepository<Categoria, Integer> {

}
