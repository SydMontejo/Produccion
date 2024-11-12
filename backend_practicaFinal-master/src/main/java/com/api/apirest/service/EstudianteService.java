package com.api.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apirest.dto.EstudiantePruebasDTO;
import com.api.apirest.iservice.IEstudianteService;
import com.api.apirest.model.Administrador;
import com.api.apirest.model.Estudiante;
import com.api.apirest.repository.AdministradorRepository;
import com.api.apirest.repository.EstudianteRepository;

@Service
public class EstudianteService implements IEstudianteService {

    @Autowired
    private EstudianteRepository rep;

    @Autowired
    private AdministradorRepository adminRepository;

    @Override
    public List<Estudiante> listar() {
        return rep.findAll();
    }

    @Override
    public Estudiante registrar(Estudiante estudiante) {
        return rep.save(estudiante);
    }

    @Override
    public Estudiante actualizar(Estudiante estudiante) {
        // Primero, actualizamos el estudiante
        Estudiante estudianteActualizado = rep.save(estudiante);

        // Luego, obtenemos el administrador asociado al estudiante
        Administrador administrador = estudianteActualizado.getAdministrador();

        // Actualizamos los datos del administrador
        administrador.setUsername(estudianteActualizado.getNov().toString());
        administrador.setPassword(estudianteActualizado.getCar().toString());

        // Guardamos los cambios en el administrador
        adminRepository.save(administrador);

        // Finalmente, retornamos el estudiante actualizado
        return estudianteActualizado;
    }

    @Override
    public void eliminar(Long id) {
        rep.deleteById(id);
    }

    @Override
    public Estudiante listarPorId(Long id) {
        return rep.findById(id).orElse(null);
    }

    @Override
    public void guardarListaDeEstudiantes(List<Estudiante> estudiantes) {
        for (Estudiante estudiante : estudiantes) {
            rep.save(estudiante);
        }
    }

    @Override
    public List<Estudiante> listarPorNov(Integer nov) {
        return rep.findByNov(nov);
    }

    @Override
    public Estudiante obtenerPorNombre(String nombre) {
        return rep.findByNombre(nombre);
    }

    public List<EstudiantePruebasDTO> obtenerEstudiantesConPruebas() {
        return rep.obtenerEstudiantesConPruebas(); // Este método debería estar en el repositorio
    }
}
