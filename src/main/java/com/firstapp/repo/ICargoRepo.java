package com.firstapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstapp.models.Cargo;

public interface ICargoRepo extends JpaRepository<Cargo, Integer> {

}
