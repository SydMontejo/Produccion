package com.api.apirest.controller;

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
import com.api.apirest.iservice.IAdministradorService;
import com.api.apirest.model.Administrador;

@CrossOrigin
@RestController
@RequestMapping("/api/administrador")
public class AdministradorController {

    @Autowired
    private IAdministradorService service;
    
    /*@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @PostMapping
	public Administrador registrar(@RequestBody Administrador administrador) throws Exception {
    	administrador.setPassword(this.bCryptPasswordEncoder.encode(administrador.getPassword()));
		return service.registrar(administrador);
	}
*/
    @PostMapping
	public ResponseEntity<Administrador> registrar(@RequestBody Administrador administrador) throws Exception {
		Administrador obj = service.registrar(administrador);
		return new ResponseEntity<Administrador>(obj, HttpStatus.OK);
	}

    @GetMapping
	public ResponseEntity<List<Administrador>> listar(){
		List<Administrador> obj = service.listar(); 
		return new ResponseEntity<List<Administrador>>(obj, HttpStatus.OK);
	}
    
    @GetMapping("/{username}")
    public ResponseEntity<Administrador> obtenerPorUsername(@PathVariable String username) {
        Administrador administrador = service.obtenerPorUsername(username);
        if (administrador != null) {
            return ResponseEntity.ok(administrador);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) throws Exception{
		Administrador obj = service.listarPorId(id);
		if(obj==null) {
			throw new Exception("No se encontro el ID");
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
    
    @PutMapping
    public ResponseEntity<Administrador> actualizar(@RequestBody Administrador admin) {
        Administrador obj = service.actualizar(admin);
        return new ResponseEntity<Administrador>(obj, HttpStatus.OK);
    }
}
