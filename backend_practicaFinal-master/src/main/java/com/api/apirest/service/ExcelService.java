// package com.api.apirest.service;

// import com.api.apirest.model.Administrador;
// import com.api.apirest.model.Estudiante;
// import com.api.apirest.model.Prueba;
// import com.api.apirest.repository.AdministradorRepository;
// import com.api.apirest.repository.EstudianteRepository;
// import com.api.apirest.repository.PruebaRepository;
// import org.apache.poi.ss.usermodel.*;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;
// import java.io.IOException;
// import java.math.BigDecimal;
// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.util.Iterator;

// @Service
// public class ExcelService {
// 	@Autowired
// 	private EstudianteRepository estudianteRepository;

// 	@Autowired
// 	private PruebaRepository pruebaRepository;
	
// 	@Autowired
// 	private AdministradorRepository administradorRepository;

// 	public void importarExcel(MultipartFile file) throws IOException {
// 		Workbook workbook = new XSSFWorkbook(file.getInputStream());
// 		Sheet sheet = workbook.getSheetAt(0);
// 		Iterator<Row> rowIterator = sheet.iterator();

// 		// Saltar la primera fila (encabezados)
// 		if (rowIterator.hasNext()) {
// 			rowIterator.next();
// 		}

// 		// Ahora, rowIterator comienza desde la segunda fila
// 		while (rowIterator.hasNext()) {
// 			Row row = rowIterator.next();

// 			// Verifica si la primera celda (nombre) está vacía
// 			Cell firstCell = row.getCell(0);
// 			if (firstCell == null || firstCell.getCellType() == CellType.BLANK) {
// 				// Si la primera celda está vacía, ignora el resto de la fila
// 				continue;
// 			}

// 			Estudiante estudiante = new Estudiante();
// 			Cell cell = row.getCell(0);
// 			if (cell != null) {
// 				estudiante.setNombre(cell.getStringCellValue());
// 			} else {
// 				System.out.println("No hay nada");
// 			}

// 			cell = row.getCell(8);
// 			if (cell != null) {
// 				String correo = cell.getStringCellValue();
// 				if (correo != null && !correo.isEmpty()) {
// 					estudiante.setCorreo(correo);
// 				} else {
// 					System.err.println("Advertencia: Celda de correo vacía o nula en la fila " + row.getRowNum());
// 				}
// 			} else {
// 				System.out.println("No hay nada");
// 			}

// 			cell = row.getCell(1);
// 			if (cell != null) {
// 				estudiante.setNov((int) cell.getNumericCellValue());
// 			} else {
// 				System.out.println("No hay nada");
// 			}

// 			cell = row.getCell(7);
// 			if (cell != null) {
// 			    if (cell.getCellType() == CellType.NUMERIC) {
// 			        // Convierte el valor numérico a una cadena
// 			        BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
// 			        String cuiValue =  bd.toPlainString();
// 			        estudiante.setCui(cuiValue);
// 			    } else if (cell.getCellType() == CellType.STRING) {
// 			        // Si la celda ya es de tipo STRING, simplemente obtén su valor
// 			        estudiante.setCui(cell.getStringCellValue());
// 			    } else {
// 			        // Maneja otros tipos de celdas si es necesario
// 			    }
// 			} else {
// 			    System.out.println("No hay nada");
// 			}


// 			cell = row.getCell(2);
// 			if (cell != null) {
// 				estudiante.setUa((int) cell.getNumericCellValue());
// 			} else {
// 				System.out.println("No hay nada");
// 			}

// 			cell = row.getCell(3);
// 			if (cell != null) {
// 				estudiante.setExt((int) cell.getNumericCellValue());
// 			} else {
// 				System.out.println("No hay nada");
// 			}

// 			cell = row.getCell(4);
// 			if (cell != null) {
// 				estudiante.setCar((int) cell.getNumericCellValue());
// 			} else {
// 				System.out.println("No hay nada");
// 			}

// 			// Verificar si el estudiante ya existe en la base de datos
// 			Estudiante estudianteExistente = estudianteRepository.findByNombreAndCorreo(estudiante.getNombre(), estudiante.getCorreo());
// 			if (estudianteExistente == null) {
// 				// Si el estudiante no existe, entonces lo guardamos
// 				estudianteRepository.save(estudiante);
// 			} else {
// 				// Si el estudiante ya existe, entonces usamos ese registro
// 				estudiante = estudianteExistente;
// 			}
			
// 			int userNov = estudiante.getNov();
// 		    String userStrNov = String.valueOf(userNov);
		    
// 		    int passwordInt = estudiante.getCar();
// 		    String passwordStrCar = String.valueOf(passwordInt);
// 			// Buscar si ya existe un usuario para este estudiante
// 			Administrador usuarioExistente = administradorRepository.findByUsername(userStrNov);

// 			if (usuarioExistente == null) {
// 			    // Si no existe, crear un nuevo usuario
// 			    Administrador usuario = new Administrador();
			   
// 			    usuario.setUsername(userStrNov);
// 			    usuario.setPassword(passwordStrCar);
// 			    usuario.setRol("ESTUDIANTE");

// 			    estudiante.setAdministrador(usuario);

// 			    administradorRepository.save(usuario);
// 			} else {
// 			    // Si ya existe, usar el usuario existente
// 			    estudiante.setAdministrador(usuarioExistente);
// 			}


// 			Prueba prueba = new Prueba();

// 			cell = row.getCell(9);
// 			if (cell != null) {
// 				prueba.setNombrePrueba(cell.getStringCellValue());
// 			} else {
// 				System.out.println("No hay nada");
// 			}


// 			cell = row.getCell(5);
// 			if (cell != null) {
// 				prueba.setResultado(cell.getStringCellValue());
// 			} else {
// 				System.out.println("No hay nada");
// 			}

// 			cell = row.getCell(6);
// 			if (cell != null) {
// 				if (cell.getCellType() == CellType.NUMERIC) {
// 					// Obtén la fecha a partir del valor numérico
// 					LocalDate fecha = cell.getLocalDateTimeCellValue().toLocalDate();
// 					prueba.setFecha(fecha);
// 				} else if (cell.getCellType() == CellType.STRING) {
// 					String fechaString = cell.getStringCellValue();
// 					LocalDate fecha = LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
// 					prueba.setFecha(fecha);
// 			} else {
// 				System.out.println("No hay nada");
// 			}

// 			prueba.setEstudiante(estudiante);

// 			pruebaRepository.save(prueba);
// 		}
// 		workbook.close();
// 	}
// 	}
// }
package com.api.apirest.service;

import com.api.apirest.model.Administrador;
import com.api.apirest.model.Estudiante;
import com.api.apirest.model.Prueba;
import com.api.apirest.repository.AdministradorRepository;
import com.api.apirest.repository.EstudianteRepository;
import com.api.apirest.repository.PruebaRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;


@Service
public class ExcelService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private PruebaRepository pruebaRepository;
    
    @Autowired
    private AdministradorRepository administradorRepository;

    public void importarExcel(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        // Saltar la primera fila (encabezados)
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        // Ahora, rowIterator comienza desde la segunda fila
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // Verifica si la primera celda (nombre) está vacía
            Cell firstCell = row.getCell(0);
            if (firstCell == null || firstCell.getCellType() == CellType.BLANK) {
                // Si la primera celda está vacía, ignora el resto de la fila
                continue;
            }

            Estudiante estudiante = new Estudiante();
            Cell cell = row.getCell(0);
            if (cell != null) {
                estudiante.setNombre(cell.getStringCellValue());
            } else {
                System.out.println("No hay nada");
            }

            cell = row.getCell(7);//era 8
            if (cell != null) {
                String correo = cell.getStringCellValue();
                if (correo != null && !correo.isEmpty()) {
                    estudiante.setCorreo(correo);
                } else {
                    System.err.println("Advertencia: Celda de correo vacía o nula en la fila " + row.getRowNum());
                }
            } else {
                System.out.println("No hay nada");
            }

            cell = row.getCell(1);
            if (cell != null) {
                estudiante.setNov((int) cell.getNumericCellValue());
            } else {
                System.out.println("No hay nada");
            }

            cell = row.getCell(6);//era 7
            if (cell != null) {
                if (cell.getCellType() == CellType.NUMERIC) {
                    // Convierte el valor numérico a una cadena
                    BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
                    String cuiValue =  bd.toPlainString();
                    estudiante.setCui(cuiValue);
                } else if (cell.getCellType() == CellType.STRING) {
                    // Si la celda ya es de tipo STRING, simplemente obtén su valor
                    estudiante.setCui(cell.getStringCellValue());
                } else {
                    // Maneja otros tipos de celdas si es necesario
                }
            } else {
                System.out.println("No hay nada");
            }


            cell = row.getCell(2);
            if (cell != null) {
                estudiante.setUa((int) cell.getNumericCellValue());
            } else {
                System.out.println("No hay nada");
            }

            cell = row.getCell(3);
            if (cell != null) {
                estudiante.setExt((int) cell.getNumericCellValue());
            } else {
                System.out.println("No hay nada");
            }

            cell = row.getCell(4);
            if (cell != null) {
                estudiante.setCar((int) cell.getNumericCellValue());
            } else {
                System.out.println("No hay nada");
            }

            // Verificar si el estudiante ya existe en la base de datos
            Estudiante estudianteExistente = estudianteRepository.findByNombreAndCorreo(estudiante.getNombre(), estudiante.getCorreo());
            if (estudianteExistente == null) {
                // Si el estudiante no existe, entonces lo guardamos
                estudianteRepository.save(estudiante);
            } else {
                // Si el estudiante ya existe, entonces usamos ese registro
                estudiante = estudianteExistente;
            }
            
            int userNov = estudiante.getNov();
            String userStrNov = String.valueOf(userNov);
            
            int passwordInt = estudiante.getCar();
            String passwordStrCar = String.valueOf(passwordInt);
            // Buscar si ya existe un usuario para este estudiante
            Administrador usuarioExistente = administradorRepository.findByUsername(userStrNov);

            if (usuarioExistente == null) {
                // Si no existe, crear un nuevo usuario
                Administrador usuario = new Administrador();
               
                usuario.setUsername(userStrNov);
                usuario.setPassword(passwordStrCar);
                usuario.setRol("ESTUDIANTE");

                estudiante.setAdministrador(usuario);

                administradorRepository.save(usuario);
            } else {
                // Si ya existe, usar el usuario existente
                estudiante.setAdministrador(usuarioExistente);
            }

            // Procesar las pruebas (a partir de la columna 9 en adelante)
            String[] pruebas = {"Matematica", "Computacion"};
            int pruebaColumnIndex = 8;//era 9
            for (String pruebaNombre : pruebas) {
                Prueba prueba = new Prueba();
                prueba.setNombrePrueba(pruebaNombre);

                // Obtener el resultado de la prueba
                cell = row.getCell(pruebaColumnIndex);
                if (cell != null) {
                    prueba.setResultado(cell.getStringCellValue());
                } else {
                    System.out.println("No hay resultado para " + pruebaNombre);
                    //prueba.setResultado("No agarra");
                }

                // Obtener la fecha de la prueba (columna 6)
                cell = row.getCell(5);//era 6
                if (cell != null) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        // Obtén la fecha a partir del valor numérico
                        LocalDate fecha = cell.getLocalDateTimeCellValue().toLocalDate();
                        prueba.setFecha(fecha);
                    } else if (cell.getCellType() == CellType.STRING) {
                        String fechaString = cell.getStringCellValue();
                        LocalDate fecha = LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        prueba.setFecha(fecha);
                    } else {
                        System.out.println("No hay fecha válida para la prueba");
                    }
                }

                prueba.setEstudiante(estudiante);
                pruebaRepository.save(prueba);

                pruebaColumnIndex++;
            }
        }
        workbook.close();
    }
}
//----------------------------------------------------------------------------------------------------
// @Service
// public class ExcelService {

//     @Autowired
//     private EstudianteRepository estudianteRepository;

//     @Autowired
//     private PruebaRepository pruebaRepository;

//     @Autowired
//     private AdministradorRepository administradorRepository;

//     public void importarExcel(MultipartFile file) throws IOException {
//         Workbook workbook = new XSSFWorkbook(file.getInputStream());
//         Sheet sheet = workbook.getSheetAt(0);
//         Iterator<Row> rowIterator = sheet.iterator();

//         // Saltar la primera fila (encabezados)
//         if (rowIterator.hasNext()) {
//             rowIterator.next();
//         }

//         // Ahora, rowIterator comienza desde la segunda fila
//         while (rowIterator.hasNext()) {
//             Row row = rowIterator.next();

//             // Verifica si la primera celda (nombre) está vacía
//             Cell firstCell = row.getCell(0);
//             if (firstCell == null || firstCell.getCellType() == CellType.BLANK) {
//                 // Si la primera celda está vacía, ignora el resto de la fila
//                 continue;
//             }

//             Estudiante estudiante = new Estudiante();

//             // Leer nombre
//             Cell cell = row.getCell(0);
//             estudiante.setNombre(cell != null ? cell.getStringCellValue() : "");

//             // Leer NOV
//             cell = row.getCell(1);
//             estudiante.setNov(cell != null ? (int) cell.getNumericCellValue() : 0);

//             // Leer UA
//             cell = row.getCell(2);
//             estudiante.setUa(cell != null ? (int) cell.getNumericCellValue() : 0);

//             // Leer EXT
//             cell = row.getCell(3);
//             estudiante.setExt(cell != null ? (int) cell.getNumericCellValue() : 0);

//             // Leer CAR
//             cell = row.getCell(4);
//             estudiante.setCar(cell != null ? (int) cell.getNumericCellValue() : 0);

//             // Leer y validar las notas de Matemática y Computación
//             double notaMatematica = 0;
//             double notaComputacion = 0;

//             // Leer nota Matemática (valor sobre 25)
//             cell = row.getCell(5);
//             if (cell != null) {
//                 notaMatematica = cell.getNumericCellValue();
//             }

//             // Leer nota Computación (valor sobre 20)
//             cell = row.getCell(6);
//             if (cell != null) {
//                 notaComputacion = cell.getNumericCellValue();
//             }

//             // Calcular si el resultado global es satisfactorio o insatisfactorio
//             double porcentajeMatematica = (notaMatematica / 25) * 100;
//             double porcentajeComputacion = (notaComputacion / 20) * 100;

//             String resultadoFinal = (porcentajeMatematica >= 61 && porcentajeComputacion >= 61) ? "SATISFACTORIO" : "INSATISFACTORIO";

//             // Leer fecha de aprobación (columna 8)
//             LocalDate fechaAprobacion = null;
//             cell = row.getCell(8);
//             if (cell != null) {
//                 if (cell.getCellType() == CellType.NUMERIC) {
//                     fechaAprobacion = cell.getLocalDateTimeCellValue().toLocalDate();
//                 } else if (cell.getCellType() == CellType.STRING) {
//                     fechaAprobacion = LocalDate.parse(cell.getStringCellValue(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
//                 }
//             }

//             // Verificar si el estudiante ya existe en la base de datos
//             Estudiante estudianteExistente = estudianteRepository.findByNombreAndCorreo(estudiante.getNombre(), estudiante.getCorreo());
//             if (estudianteExistente == null) {
//                 // Si el estudiante no existe, entonces lo guardamos
//                 estudianteRepository.save(estudiante);
//             } else {
//                 estudiante = estudianteExistente;
//             }

//             // Crear usuario Administrador si no existe
//             int userNov = estudiante.getNov();
//             String userStrNov = String.valueOf(userNov);

//             int passwordInt = estudiante.getCar();
//             String passwordStrCar = String.valueOf(passwordInt);

//             Administrador usuarioExistente = administradorRepository.findByUsername(userStrNov);

//             if (usuarioExistente == null) {
//                 Administrador usuario = new Administrador();
//                 usuario.setUsername(userStrNov);
//                 usuario.setPassword(passwordStrCar);
//                 usuario.setRol("ESTUDIANTE");
//                 estudiante.setAdministrador(usuario);
//                 administradorRepository.save(usuario);
//             } else {
//                 estudiante.setAdministrador(usuarioExistente);
//             }

//             // Procesar las pruebas
//             String[] pruebas = {"Matematica", "Computacion"};
//             double[] notas = {notaMatematica, notaComputacion};

//             for (int i = 0; i < pruebas.length; i++) {
//                 Prueba prueba = new Prueba();
//                 prueba.setNombrePrueba(pruebas[i]);

//                 // Asignar resultado individual de la prueba
//                 double nota = notas[i];
//                 double porcentaje = (i == 0) ? (nota / 25) * 100 : (nota / 20) * 100;
//                 prueba.setResultado(porcentaje >= 61 ? "SATISFACTORIO" : "INSATISFACTORIO");

//                 // Asignar fecha de aprobación y estudiante
//                 prueba.setFecha(fechaAprobacion);
//                 prueba.setEstudiante(estudiante);

//                 pruebaRepository.save(prueba);
//             }
//         }

//         workbook.close();
//     }
// }
