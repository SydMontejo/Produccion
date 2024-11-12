package com.api.apirest.model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

// public class ExportarExcel {
//     private XSSFWorkbook workbook;
//     private XSSFSheet sheet;
//     private List<Estudiante> listaEstudiantes;
//     private List<Prueba> listaPruebas;

//     public ExportarExcel(List<Estudiante> listaEstudiantes2, List<Prueba> listaPruebas2) {
//         this.listaEstudiantes = listaEstudiantes2;
//         this.listaPruebas = listaPruebas2;
//         workbook = new XSSFWorkbook(); 
//     }

//     private void writeHeaderLine() {
//         sheet = workbook.createSheet("FormatoPE");
//         Row row = sheet.createRow(0);
//         CellStyle style = workbook.createCellStyle();
//         XSSFFont font = workbook.createFont();
//         font.setBold(true);
//         font.setFontHeight(12);
//         style.setFont(font);
//         style.setAlignment(HorizontalAlignment.CENTER);

//         createCell(row, 0, "NOMBRE", style);
//         createCell(row, 1, "NOV", style);
//         createCell(row, 2, "UA", style);
//         createCell(row, 3, "EXT", style);
//         createCell(row, 4, "CAR", style);
//         createCell(row, 5, "RESULTADO", style);
//         createCell(row, 6, "FECHA APROBACION", style);
//         createCell(row, 7, "CUI", style);
//         createCell(row, 8, "CORREO", style);
//     }

//     private void createCell(Row row, int columnCount, Object value, CellStyle style) {
//         sheet.autoSizeColumn(columnCount);
//         Cell cell = row.createCell(columnCount);
//         if (value instanceof Integer) {
//             cell.setCellValue((Integer) value);
//         } else if (value instanceof Boolean) {
//             cell.setCellValue((Boolean) value);
//         } else if (value instanceof LocalDate) {
//             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//             String fecha = ((LocalDate) value).format(formatter);
//             cell.setCellValue(fecha);
//         } else {
//             cell.setCellValue((String) value);
//         }
//         cell.setCellStyle(style);
//     }

//     private void writeDataLines() {
//         int rowCount = 1;

//         CellStyle style = workbook.createCellStyle();
//         XSSFFont font = workbook.createFont();
//         font.setFontHeight(12);
//         style.setFont(font);
//         style.setAlignment(HorizontalAlignment.CENTER);

//         for (Estudiante est : listaEstudiantes) {
//             Row row = sheet.createRow(rowCount++);
//             int columnCount = 0;

//             createCell(row, columnCount++, est.getNombre(), style);
//             createCell(row, columnCount++, est.getNov(), style);
//             createCell(row, columnCount++, est.getUa(), style);
//             createCell(row, columnCount++, est.getExt(), style);
//             createCell(row, columnCount++, est.getCar(), style);

//             // Calcula el resultado general
//             boolean computacionSatisfactoria = false;
//             boolean matematicaSatisfactoria = false;

//             for (Prueba prueba : est.getPruebas()) {
//                 if (prueba.getNombrePrueba().equalsIgnoreCase("Computacion") && "Satisfactorio".equals(prueba.getResultado())) {
//                     computacionSatisfactoria = true;
//                 } else if (prueba.getNombrePrueba().equalsIgnoreCase("Matematica") && "Satisfactorio".equals(prueba.getResultado())) {
//                     matematicaSatisfactoria = true;
//                 }
//             }

//             String resultadoGeneral = (computacionSatisfactoria && matematicaSatisfactoria) ? "SATISFACTORIO" : "INSATISFACTORIO";
//             createCell(row, columnCount++, resultadoGeneral, style);

//             // Calcula la fecha más reciente de cualquier prueba
//             LocalDate fechaMasReciente = null;
//             for (Prueba prueba : est.getPruebas()) {
//                 if (fechaMasReciente == null || prueba.getFecha().isAfter(fechaMasReciente)) {
//                     fechaMasReciente = prueba.getFecha();
//                 }
//             }

//             if (fechaMasReciente != null) {
//                 createCell(row, columnCount++, fechaMasReciente, style);
//             } else {
//                 createCell(row, columnCount++, "", style);
//             }

//             createCell(row, columnCount++, est.getCui(), style);
//             createCell(row, columnCount++, est.getCorreo(), style);
//         }
//     }

//     public void export(HttpServletResponse response) throws IOException {
//         writeHeaderLine();
//         writeDataLines();

//         ServletOutputStream outputStream = response.getOutputStream();
//         workbook.write(outputStream);
//         workbook.close();

//         outputStream.close();
//     }
// }




// public class ExportarExcel {
// 	private XSSFWorkbook workbook;
// 	private XSSFSheet sheet;
// 	private List<Estudiante> listaEstudiantes;
// 	private List<Prueba> listaPruebas;
	
// 	public ExportarExcel(List<Estudiante> listaEstudiantes2, List<Prueba> listaPruebas2) {
// 		this.listaEstudiantes = listaEstudiantes2;
// 		this.listaPruebas = listaPruebas2;
// 		workbook = new XSSFWorkbook(); 
// 	}
	
// 	private void writeHeaderLine() {
// 		sheet = workbook.createSheet("FormatoPE");
// 		Row row = sheet.createRow(0);
// 		CellStyle style = workbook.createCellStyle();
// 		XSSFFont font = workbook.createFont();
// 		font.setBold(true);
// 		font.setFontHeight(12);
// 		style.setFont(font);
// 		style.setAlignment(HorizontalAlignment.CENTER);
// 		createCell(row, 0, "NOMBRE", style);
// 		createCell(row, 1, "NOV", style);
// 		createCell(row, 2, "UA", style);
// 		createCell(row, 3, "EXT", style);
// 		createCell(row, 4, "CAR", style);
// 		createCell(row, 5, "RESULTADO", style);
// 		createCell(row, 6, "FECHA APROBACION", style);
// 		createCell(row, 7, "CUI", style);
// 		createCell(row, 8, "CORREO", style);
// 	}

// 	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
// 	    sheet.autoSizeColumn(columnCount);
// 	    Cell cell = row.createCell(columnCount);
// 	    if (value instanceof Integer) {
// 	        cell.setCellValue((Integer) value);
// 	    } else if (value instanceof Boolean) {
// 	        cell.setCellValue((Boolean) value);
// 	    } else if (value instanceof LocalDate) {
// 	        // Convertir LocalDate a String
// 	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
// 	        String fecha = ((LocalDate) value).format(formatter);
// 	        cell.setCellValue(fecha);
// 	    } else {
// 	        cell.setCellValue((String) value);
// 	    }
// 	    cell.setCellStyle(style);
// 	}

	
// 	private void writeDataLines() {
// 	    int rowCount = 1;

// 	    CellStyle style = workbook.createCellStyle();
// 	    XSSFFont font = workbook.createFont();
// 	    font.setFontHeight(12);
// 	    style.setFont(font);
// 	    style.setAlignment(HorizontalAlignment.CENTER);

// 	    for(Estudiante est : listaEstudiantes) {
// 	        Row row = sheet.createRow(rowCount++);
// 	        int columnCount = 0;

// 	        createCell(row, columnCount++, est.getNombre(), style); // Nombre en la columna 0
// 	        createCell(row, columnCount++, est.getNov(), style); // Nov en la columna 1
// 	        createCell(row, columnCount++, est.getUa(), style); // Ua en la columna 2
// 	        createCell(row, columnCount++, est.getExt(), style); // Ext en la columna 3
// 	        createCell(row, columnCount++, est.getCar(), style); // Car en la columna 4

// 	     // Calcula el resultado general para las pruebas del estudiante
// 	        Map<String, String> ultimosResultados = new HashMap<>();
// 	        for (Prueba prueba : est.getPruebas()) {
// 	            // Solo actualiza el resultado si es "SATISFACTORIO" o si no hay un resultado previo
// 	            if (!ultimosResultados.containsKey(prueba.getNombrePrueba()) || "SATISFACTORIO".equals(prueba.getResultado())) {
// 	                ultimosResultados.put(prueba.getNombrePrueba(), prueba.getResultado());
// 	            }
// 	        }

// 	        boolean todasSatisfactorias = true;
// 	        for (String resultado : ultimosResultados.values()) {
// 	            if ("INSATISFACTORIO".equals(resultado)) {
// 	                todasSatisfactorias = false;
// 	                break;
// 	            }
// 	        }

// 	        String resultadoGeneral = todasSatisfactorias ? "SATISFACTORIO" : "INSATISFACTORIO";
// 	        createCell(row, columnCount++, resultadoGeneral, style); // Resultado en la columna 5


// 	     // Calcula la fecha de la última prueba satisfactoria
// 	        Map<String, LocalDate> ultimasFechasSatisfactorias = new HashMap<>();
// 	        for (Prueba prueba : est.getPruebas()) {
// 	            // Solo actualiza la fecha si el resultado es "SATISFACTORIO" o si no hay una fecha previa
// 	            if ("SATISFACTORIO".equals(prueba.getResultado()) && (!ultimasFechasSatisfactorias.containsKey(prueba.getNombrePrueba()) || prueba.getFecha().isAfter(ultimasFechasSatisfactorias.get(prueba.getNombrePrueba())))) {
// 	                ultimasFechasSatisfactorias.put(prueba.getNombrePrueba(), prueba.getFecha());
// 	            }
// 	        }

// 	        LocalDate fechaMasReciente = null;
// 	        for (LocalDate fecha : ultimasFechasSatisfactorias.values()) {
// 	            if (fechaMasReciente == null || fecha.isAfter(fechaMasReciente)) {
// 	                fechaMasReciente = fecha;
// 	            }
// 	        }

// 	        // Usa la fecha de la última prueba satisfactoria (si existe)
// 	        if (fechaMasReciente != null) {
// 	            createCell(row, columnCount++, fechaMasReciente, style); // Fecha en la columna 6
// 	        }


// 	        createCell(row, columnCount++, est.getCui(), style); // Cui en la columna 7
// 	        createCell(row, columnCount++, est.getCorreo(), style); // Correo en la columna 8
// 	    }
// 	}


	
// 	public void export(HttpServletResponse response) throws IOException{
// 		writeHeaderLine();
// 		writeDataLines();
		
// 		ServletOutputStream outputStream = response.getOutputStream();
// 		workbook.write(outputStream);
// 		workbook.close();
		
// 		outputStream.close();
// 	}

// }

public class ExportarExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Estudiante> listaEstudiantes;
    private List<Prueba> listaPruebas;

    public ExportarExcel(List<Estudiante> listaEstudiantes2, List<Prueba> listaPruebas2) {
        this.listaEstudiantes = listaEstudiantes2;
        this.listaPruebas = listaPruebas2;
        workbook = new XSSFWorkbook(); 
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("FormatoPE");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        createCell(row, 0, "NOMBRE", style);
        createCell(row, 1, "NOV", style);
        createCell(row, 2, "UA", style);
        createCell(row, 3, "EXT", style);
        createCell(row, 4, "CAR", style);
        createCell(row, 5, "RESULTADO", style);
        createCell(row, 6, "FECHA APROBACION", style);
        createCell(row, 7, "CUI", style);
        createCell(row, 8, "CORREO", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof LocalDate) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fecha = ((LocalDate) value).format(formatter);
            cell.setCellValue(fecha);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        for (Estudiante est : listaEstudiantes) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, est.getNombre(), style);
            createCell(row, columnCount++, est.getNov(), style);
            createCell(row, columnCount++, est.getUa(), style);
            createCell(row, columnCount++, est.getExt(), style);
            createCell(row, columnCount++, est.getCar(), style);

            // Calcula el resultado general
            boolean computacionSatisfactoria = false;
            boolean matematicaSatisfactoria = false;

            for (Prueba prueba : est.getPruebas()) {
                if (prueba.getNombrePrueba().equalsIgnoreCase("Computacion") && "Satisfactorio".equals(prueba.getResultado())) {
                    computacionSatisfactoria = true;
                } else if (prueba.getNombrePrueba().equalsIgnoreCase("Matematica") && "Satisfactorio".equals(prueba.getResultado())) {
                    matematicaSatisfactoria = true;
                }
            }

            String resultadoGeneral = (computacionSatisfactoria && matematicaSatisfactoria) ? "SATISFACTORIO" : "INSATISFACTORIO";
            createCell(row, columnCount++, resultadoGeneral, style);

            // Modificación aquí: ordenar las pruebas por fecha antes de calcular la fecha más reciente
            est.getPruebas().sort(Comparator.comparing(Prueba::getFecha).reversed()); // Ordena las pruebas por fecha, de la más reciente a la más antigua

            // Calcula la fecha más reciente de cualquier prueba
            LocalDate fechaMasReciente = null;
            for (Prueba prueba : est.getPruebas()) {
                if (fechaMasReciente == null || prueba.getFecha().isAfter(fechaMasReciente)) {
                    fechaMasReciente = prueba.getFecha();
                }
            }

            if (fechaMasReciente != null) {
                createCell(row, columnCount++, fechaMasReciente, style);
            } else {
                createCell(row, columnCount++, "", style);
            }

            createCell(row, columnCount++, est.getCui(), style);
            createCell(row, columnCount++, est.getCorreo(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
