package com.api.apirest.controller;

import com.api.apirest.iservice.IPruebaService;
import com.api.apirest.model.Prueba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//produccion 
@CrossOrigin(origins = "https://especificas.ingsistemascunori.org", allowCredentials = "true")
//@CrossOrigin
@RestController
@RequestMapping("/api/pruebas")
public class PruebaController {

    @Autowired
    private IPruebaService service;

    @GetMapping
    public ResponseEntity<List<Prueba>> listar(){
        List<Prueba> obj = service.listar();
        return new ResponseEntity<List<Prueba>>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Prueba> registrar(@RequestBody Prueba prueba) {
        Prueba obj = service.registrar(prueba);
        return new ResponseEntity<Prueba>(obj, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Prueba> actualizar(@RequestBody Prueba prueba) {
        Prueba obj = service.actualizar(prueba);
        return new ResponseEntity<Prueba>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) throws Exception{
        Prueba obj = service.listarPorId(id);
        if(obj==null) {
            throw new Exception("No se encontro el ID");
        }
        service.eliminar(id);
    }

    @GetMapping("/pruebaPorId/{id}")
    public ResponseEntity<Prueba> listarPorId(@PathVariable Integer id) throws Exception{
        Prueba obj = service.listarPorId(id);
        if(obj==null) {
            throw new Exception("No se encontro el ID");
        }
        return new ResponseEntity<Prueba>(obj, HttpStatus.OK);
    }

}
