package com.api.apirest.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apirest.dto.EstudiantePruebasDTO;
import com.api.apirest.iservice.IEstudianteService;
import com.api.apirest.model.Estudiante;
import com.api.apirest.service.EstudianteService;

//produccion 
@CrossOrigin(origins = "https://especificas.ingsistemascunori.org", allowCredentials = "true")
//@CrossOrigin
@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {
@Autowired
    //---------------------Agregue esta seccion=------------------------------------------
	private EstudianteService estudianteService;

    @GetMapping("/con-pruebas")
    public List<EstudiantePruebasDTO> obtenerEstudiantesConPruebas() {
        return estudianteService.obtenerEstudiantesConPruebas();
    }
	//----------------------------------------------------------------------------------
	@Autowired
	private IEstudianteService service;
	
	@GetMapping("/estudiante")
	public List<Estudiante> obtenerEstudiante(){
		List<Estudiante> listaEstudiantes = new ArrayList<>();
		//1
		
		Estudiante e = new Estudiante();
		e.setId(1L);
		e.setNov(201744372);
		e.setNombre("Hermas Rene Ramirez Rodriguez");
		e.setCui("3321145895214");
		e.setCorreo("hermas@gmail.com");
		e.setUa(19);
		e.setExt(0);
		e.setCar(22);
	
		//2
		Estudiante e1 = new Estudiante();
		e1.setId(2L);
		e1.setNov(201844358);
		e1.setNombre("Josue Mario Pineda Martinez");
		e1.setCui("3321178495214");
		e1.setCorreo("mario@gmail.com");
		e1.setUa(19);
		e1.setExt(0);
		e1.setCar(33);

		listaEstudiantes.add(e);
		listaEstudiantes.add(e1);
		
		return listaEstudiantes;
	}
	
	@GetMapping
	public ResponseEntity<List<Estudiante>> listar(){
		List<Estudiante> obj = service.listar(); 
		return new ResponseEntity<List<Estudiante>>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Estudiante> registrar(@RequestBody Estudiante estudiante) {
		Estudiante obj = service.registrar(estudiante);
		return new ResponseEntity<Estudiante>(obj, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Estudiante> actualizar(@RequestBody Estudiante estudiante) {
		Estudiante obj = service.actualizar(estudiante);
		return new ResponseEntity<Estudiante>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) throws Exception{
		Estudiante obj = service.listarPorId(id);
		if(obj==null) {
			throw new Exception("No se encontro el ID");
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/estudiantePorId/{id}")
	public ResponseEntity<Estudiante> listarPorId(@PathVariable Long id) throws Exception{
		Estudiante obj = service.listarPorId(id);
		if(obj==null) {
			throw new Exception("No se encontro el ID");
		}
		return new ResponseEntity<Estudiante>(obj, HttpStatus.OK);
	}
	
	@PostMapping("/importados")
    public ResponseEntity<String> guardarListaEstudiantes(@RequestBody List<Estudiante> estudiantes) {
        try {
            service.guardarListaDeEstudiantes(estudiantes);
            return ResponseEntity.ok("Lista de estudiantes importada y guardada en la base de datos.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al importar y guardar la lista de estudiantes: " + e.getMessage());
        }
    }
	
	@GetMapping("/estudiantePorNov/{nov}")
	public ResponseEntity<List<Estudiante>> listarPorNov(@PathVariable Integer nov) {
	    List<Estudiante> estudiantes = service.listarPorNov(nov);
	    if (estudiantes.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }else {
	    	return ResponseEntity.ok(estudiantes);
	    } 
	}
	/*
	@GetMapping("/estudiante/{nombre}")
    public ResponseEntity<List<Estudiante>> obtenerPorNombre(@PathVariable("nombre") String nombre) {
		List<Estudiante> estudiantes = service.obtenerPorNombre(nombre);
		if (estudiantes.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }else {
	    	return ResponseEntity.ok(estudiantes);
	    } 
    }*/
	
	@GetMapping("/estudiante/{nombre}")
    public ResponseEntity<Estudiante> obtenerPorUsername(@PathVariable String nombre) {
		Estudiante est = service.obtenerPorNombre(nombre);
        if (est != null) {
            return ResponseEntity.ok(est);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
