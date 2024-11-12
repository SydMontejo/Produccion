package com.api.apirest.service;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.apirest.model.Administrador;

@Service
public class DatabaseInitializer {
    @Autowired
    private AdministradorService administradorService;

    @PostConstruct
    public void init() throws Exception {
        String adminUsername = "admin";
        
        // Verificar si el administrador ya existe
        Administrador existingAdmin = administradorService.obtenerPorUsername(adminUsername);
        
        if (existingAdmin == null) {
            // Si el administrador no existe, entonces lo registramos
            Administrador administrador = new Administrador();
            administrador.setUsername(adminUsername);
            administrador.setPassword("admin11$");
            administrador.setRol("ADMINISTRADOR");
            administradorService.registrar(administrador);
        } else {
            // Si el administrador ya existe, no hacemos nada y mostramos un mensaje informativo
            System.out.println("El usuario administrador ya está presente en la base de datos.");
        }
    }
}

