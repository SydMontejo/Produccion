package com.api.apirest.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;//Agregado
import org.springframework.stereotype.Repository;
import com.api.apirest.dto.EstudiantePruebasDTO;//Agregado
import com.api.apirest.model.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long>{
	
	Estudiante findByNombreAndCorreo(String nombre, String correo);
	List<Estudiante> findByNov(Integer nov);
	Estudiante findByNombre(String nombre);

	//Consulta agregada
	@Query("SELECT new com.api.apirest.dto.EstudiantePruebasDTO(e.nov, e.nombre, e.car, " +
    "MAX(CASE WHEN p.nombrePrueba = 'Computación' THEN p.resultado END), " +
    "MAX(CASE WHEN p.nombrePrueba = 'Matematica' THEN p.resultado END)) " +
    "FROM Estudiante e LEFT JOIN Prueba p ON e.id = p.estudiante.id " +
    "GROUP BY e.nov, e.nombre, e.car " +
    "ORDER BY e.nov, e.nombre, e.car")
	List<EstudiantePruebasDTO> obtenerEstudiantesConPruebas();
}
