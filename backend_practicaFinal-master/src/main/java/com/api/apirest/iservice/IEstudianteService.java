package com.api.apirest.iservice;

import java.util.List;
import com.api.apirest.model.Estudiante;

public interface IEstudianteService {
	
	List<Estudiante> listar();
	Estudiante registrar(Estudiante estudiante);
	Estudiante actualizar(Estudiante estudiante);
	void eliminar(Long id);
	Estudiante listarPorId(Long id);
	void guardarListaDeEstudiantes(List<Estudiante> estudiante);
	List<Estudiante> listarPorNov(Integer nov);
	//List<Estudiante> obtenerPorNombre(String nombre);
	Estudiante obtenerPorNombre(String username);

}
