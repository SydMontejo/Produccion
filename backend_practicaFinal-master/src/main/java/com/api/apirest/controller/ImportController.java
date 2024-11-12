package com.api.apirest.controller;

import com.api.apirest.iservice.IEstudianteService;
import com.api.apirest.iservice.IPruebaService;
import com.api.apirest.model.Estudiante;
import com.api.apirest.model.ExportarExcel;
import com.api.apirest.model.Prueba;
import com.api.apirest.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

//produccion 
@CrossOrigin(origins = "https://especificas.ingsistemascunori.org", allowCredentials = "true")
//@CrossOrigin
@RestController
public class ImportController {
    @Autowired
    private ExcelService excelService;
    
    @Autowired
	private IEstudianteService servEst;
    
    @Autowired
	private IPruebaService servPrueba;

    
    @PostMapping("/importar")
    public ResponseEntity<String> importar(@RequestParam MultipartFile file) throws IOException {
        try {
            excelService.importarExcel(file);
            return ResponseEntity.ok("Archivo importado con éxito");
        } catch (DataIntegrityViolationException e) {
            System.err.println("Error al guardar el estudiante: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al guardar el estudiante, " +
                    "ya existe un estudiante con esos DATOS: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al leer el archivo: " + e.getMessage());
        }
    }
    
    // Endpoint para exportar datos a un archivo Excel
    @GetMapping("/exportar")
    public ResponseEntity<String> exportarExcel(HttpServletResponse response) {
        try {
            response.setContentType("application/octet-stream");
            List<Estudiante> estudiantes = servEst.listar();
            List<Prueba> pruebas = servPrueba.listar();
            ExportarExcel excel = new ExportarExcel(estudiantes, pruebas);
            excel.export(response);
            return ResponseEntity.ok("Archivo exportado con éxito");
        } catch (IOException e) {
            System.err.println("Error al exportar el archivo: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al exportar el archivo: " + e.getMessage());
        }
    }



}


