package com.api.apirest.service;

import com.api.apirest.iservice.IPruebaService;
import com.api.apirest.model.Estudiante;
import com.api.apirest.model.Prueba;
import com.api.apirest.repository.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PruebaService implements IPruebaService {

    @Autowired
    PruebaRepository rep;
    
    @Autowired
    private EstudianteService estudianteService;
    
    @Override
    public List<Prueba> listar() {
        return rep.findAll();
    }

    @Override
    public Prueba registrar(Prueba prueba) {
        return rep.save(prueba);
    }

    @Override
    public Prueba actualizar(Prueba prueba) {
        return rep.save(prueba);
    }

    @Override
    public void eliminar(Integer id) {
        // Buscar la prueba
        Prueba prueba = rep.findById(Long.valueOf(id)).orElse(null);

        // Verificar si la prueba existe
        if (prueba != null) {
            // Guardar el estudiante asociado a la prueba
            Estudiante estudiante = prueba.getEstudiante();

            // Eliminar la prueba
            rep.deleteById(Long.valueOf(id));

            // Verificar si el estudiante ya no tiene más pruebas
            if (estudiante.getPruebas().isEmpty()) {
                // Si el estudiante no tiene más pruebas, eliminar al estudiante
                estudianteService.eliminar(estudiante.getId());
            }
        } else {
            // Si la prueba no existe, lanzar una excepción o manejar el error de alguna otra forma
            throw new RuntimeException("La prueba con id " + id + " no existe");
        }
    }



    @Override
    public Prueba listarPorId(Integer id) {
        return rep.findById(Long.valueOf(id)).orElse(null);
    }
}
