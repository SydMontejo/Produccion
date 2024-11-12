import { Component, OnInit } from '@angular/core';
import { EstudiantesService } from 'src/app/services/estudiante.service';
import { Estudiante } from 'src/app/model/estudiante';
import { Prueba } from 'src/app/model/prueba';
import { PruebaService } from 'src/app/services/prueba.service';
import { HttpClient } from '@angular/common/http';
import { saveAs } from 'file-saver';
import Swal from 'sweetalert2';

//produccion 
const URL = 'https://api-especificas.ingsistemascunori.org/exportar';
//const URL = 'http://localhost:8080/exportar';

@Component({
  selector: 'app-resumenes',
  templateUrl: './resumenes.component.html',
  styleUrls: ['./resumenes.component.scss']
})

export class resumenesComponent implements OnInit {
  titulo: string = "Resúmenes";

  estudiantes!: Estudiante[];
  pruebas!: Prueba[];

  years: number[] = [];
  selectedYear!: number;
  selectedCarrera!: string;
  totalEstudiantes!: number;
  totalEstudiantesEvaluados!: number;

  carreras = [
    { value: 'all', viewValue: 'Todas' },
    { value: '21', viewValue: 'Ingeniería Civil' },
    { value: '22', viewValue: 'Ingeniería en Ciencias y Sistemas' },
    { value: '33', viewValue: 'Ingeniería Industrial' }
  ];

  constructor(public estudianteService: EstudiantesService,
    public pruebaService: PruebaService,
    private http: HttpClient) {
    let currentYear = new Date().getFullYear();
    this.years.push(0); //Valor para representar a todos los años
    for (let i = currentYear; i >= 2010; i--) {
      this.years.push(i);
    }
  }

  ngOnInit() {
  }

  cargarPruebas(): void {
    this.estudianteService.listar().subscribe(
      e => {
        // Filtra los estudiantes que tengan pruebas en el año seleccionado y la carrera seleccionada
        this.estudiantes = e.filter(estudiante =>
          (this.selectedYear === 0 || estudiante.pruebas.some(prueba => new Date(prueba.fecha).getFullYear() === this.selectedYear)) &&
          (this.selectedCarrera === 'all' || estudiante.car.toString() === this.selectedCarrera)
        );
        // Actualiza el total de estudiantes satisfactorios
        this.totalEstudiantes = this.estudiantes.filter(estudiante =>
          this.calculaResultadoGeneral(estudiante) === 'SATISFACTORIO'
        ).length;
        
        // Actualiza el total de estudiantes evaluados
        this.totalEstudiantesEvaluados = this.estudiantes.length;
    
        // Verifica si hay datos disponibles
        if (this.estudiantes.length === 0) {
          Swal.fire({
            title: 'Error!',
            text: 'No hay ningún Dato',
            icon: 'error',
            confirmButtonText: 'Aceptar'
          })
        }
      }
    )  
  }

  exportarExcel() {
    Swal.fire({
      title: 'Cargando...',
      allowOutsideClick: false
    });
    Swal.showLoading(null);
    // Realiza una solicitud GET al nuevo endpoint de exportación
    this.http.get(URL, { responseType: 'blob' }).subscribe(response => {
      saveAs(response, 'FORMATO PARA RESULTADOS DE PRUEBAS ESPECÍFICAS, COORDINACIÓN ACADÉMICA.xlsx');
      Swal.close();
      Swal.fire({
        icon: 'success',
        title: 'Archivo generado y exportado con éxito',
        showConfirmButton: false,
        timer: 2500
      });
    });
  }

  getNombreCarrera(car: number): string {
    switch (car) {
      case 21:
        return 'Ingeniería Civil';
      case 22:
        return 'Ingeniería en Ciencias y Sistemas';
      case 33:
        return 'Ingeniería Industrial';
      default:
        return '';
    }
  }

  calculaResultadoGeneral(estudiante: any) {
    type PruebaNombre = 'computacion' | 'matematica';
    let resultados: Record<PruebaNombre, string> = {
      computacion: 'INSATISFACTORIO',
      matematica: 'INSATISFACTORIO'
    };
  
    for (let prueba of estudiante.pruebas) {
      const nombrePrueba = prueba.nombrePrueba.toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g, '') as PruebaNombre;
      console.log('Evaluando prueba:', prueba.nombrePrueba, 'Resultado:', prueba.resultado);
  
      if (nombrePrueba === 'computacion' || nombrePrueba === 'matematica') {
        resultados[nombrePrueba] = prueba.resultado;
      }
    }
  
    console.log('Resultados para estudiante', estudiante.nov, resultados);
    return resultados.computacion === 'Satisfactorio' && resultados.matematica === 'Satisfactorio'
      ? 'SATISFACTORIO'
      : 'INSATISFACTORIO';
  }
  
  // calculaResultadoGeneral(estudiante: any) {
  //   let ultimosResultados: { [key: string]: string } = {};
  //   for (let prueba of estudiante.pruebas) {
  //     if (!ultimosResultados.hasOwnProperty(prueba.nombrePrueba) || prueba.resultado === 'SATISFACTORIO') {
  //       ultimosResultados[prueba.nombrePrueba] = prueba.resultado;
  //     }
  //   }
  //   let todasSatisfactorias = true;
  //   for (let resultado in ultimosResultados) {
  //     if (ultimosResultados[resultado] === 'INSATISFACTORIO') {
  //       todasSatisfactorias = false;
  //       break;
  //     }
  //   }
  //   return todasSatisfactorias ? 'SATISFACTORIO' : 'INSATISFACTORIO';
  // }

  calculaUltimaFecha(estudiante: any): Date | null {
    let fechaMasReciente: Date | null = null;
  
    for (let prueba of estudiante.pruebas) {
      let fechaPrueba = new Date(prueba.fecha + 'T00:00:00');
      
      // Verifica si la fecha actual es más reciente que la última registrada
      if (fechaMasReciente === null || fechaPrueba > fechaMasReciente) {
        fechaMasReciente = fechaPrueba;
      }
    }
  
    return fechaMasReciente;
  }

  // calculaUltimaFecha(estudiante: any) {
  //   let ultimasFechasSatisfactorias: { [key: string]: Date } = {};
  //   for (let prueba of estudiante.pruebas) {
  //     let fechaPrueba = new Date(prueba.fecha + 'T00:00:00');
  //     if (prueba.resultado === 'SATISFACTORIO' && (!ultimasFechasSatisfactorias.hasOwnProperty(prueba.nombrePrueba) || fechaPrueba > new Date(ultimasFechasSatisfactorias[prueba.nombrePrueba]))) {
  //       ultimasFechasSatisfactorias[prueba.nombrePrueba] = fechaPrueba;
  //     }
  //   }
  //   let fechaMasReciente = null;
  //   for (let fecha in ultimasFechasSatisfactorias) {
  //     if (fechaMasReciente === null || new Date(ultimasFechasSatisfactorias[fecha]) > new Date(fechaMasReciente)) {
  //       fechaMasReciente = ultimasFechasSatisfactorias[fecha];
  //     }
  //   }
  //   return fechaMasReciente;
  // }

  getSelectedCarreraText(): string {
    if (this.selectedCarrera === 'all') {
      return 'Todas las Carreras';
    }
    const carrera = this.carreras.find(c => c.value === this.selectedCarrera);
    return carrera ? carrera.viewValue : '';
  }

  //fin  
}