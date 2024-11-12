package com.api.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.apirest.model.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    Administrador findByUsername(String username);
}

