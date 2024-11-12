package com.api.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.apirest.model.Prueba;

@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Long> {

}

