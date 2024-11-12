import { Component, OnInit } from '@angular/core';
import { EstudiantesService } from 'src/app/services/estudiante.service';
import { EstudiantePruebasDTO } from 'src/app/model/estudiante-pruebas-dto'; // Importa tu nuevo DTO
import { Estudiante } from 'src/app/model/estudiante';
import { Prueba } from 'src/app/model/prueba';
import { PruebaService } from 'src/app/services/prueba.service';
import { LoginService } from './../../services/login.service';
import Swal from 'sweetalert2';
import { mergeMap } from 'rxjs/operators';

@Component({
  selector: 'app-estudiantes',
  templateUrl: './estudiantes.component.html',
  styleUrls: ['./estudiantes.component.scss']
})
export class EstudiantesComponent implements OnInit {
  titulo: string = "Lista de Estudiantes";
  terminoBusqueda: string = '';
  estudiantes: EstudiantePruebasDTO[] = [];
  pruebas: Prueba[] = [];
  estudiante: Estudiante = new Estudiante();
  prueba: Prueba = new Prueba();

  constructor(
    private estudianteService: EstudiantesService,
    private pruebaService: PruebaService,
    private loginService: LoginService
  ) { }

  ngOnInit(): void {
    this.obtenerEstudiantesConPruebas();
    this.pruebaService.listar().subscribe(
      p => {
        this.pruebas = p;
      }
    );
  }

  obtenerEstudiantesConPruebas(): void {
    this.estudianteService.obtenerEstudiantesConPruebas().subscribe(
      (data: EstudiantePruebasDTO[]) => {
        this.estudiantes = data;
      },
      (error) => {
        console.error('Error al obtener estudiantes con pruebas', error);
      }
    );
  }

  eliminar(prueba: Prueba, estudiante: Estudiante): void {
    Swal.fire({
      title: '¿Está segur@?',
      text: "¡No podrás revertir esto!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      cancelButtonText: 'Cancelar',
      confirmButtonText: '¡Sí, bórralo!'
    }).then((result) => {
      if (result.isConfirmed) {
        if (prueba.id !== undefined && estudiante.id !== undefined) {
          this.pruebaService.eliminar(prueba.id).pipe(
            mergeMap(() => this.estudianteService.eliminar(estudiante.id))
          ).subscribe(
            () => {
              Swal.fire(
                '¡Eliminado!',
                'Se ha borrado la prueba y el estudiante',
                'success'
              ).then(() => {
                // Actualizar la lista de pruebas y estudiantes
                this.obtenerEstudiantesConPruebas();
                this.pruebaService.listar().subscribe(
                  response => this.pruebas = response
                );
              });
            },
            error => {
              Swal.fire({
                title: 'Error!',
                html: `Ocurrió un error al eliminar. <br> Tienes que eliminar todas las pruebas del estudiante asociado para poder eliminar al estudiante<br>`,
                icon: 'error'
              });
            }
          );
        } else {
          //console.error('Error: prueba.id o estudiante.id es undefined');
        }
      }
    });
  }

  buscar(): void {
    if (this.terminoBusqueda.trim() !== '') {
      this.estudianteService.listar().subscribe(estudiantes => {
        this.estudiantes = estudiantes.filter(estudiante => {
          // Filtrar los estudiantes
          const termino = this.terminoBusqueda.trim().toLowerCase().normalize("NFD").replace(/[\u0300-\u036f]/g, "");
          return (
            estudiante.nombre.toLowerCase().normalize("NFD").replace(/[\u0300-\u036f]/g, "").includes(termino) ||
            estudiante.nov.toString().includes(termino) ||
            estudiante.car.toString().includes(termino)
          );
        });
      });
      this.pruebaService.listar().subscribe(pruebas => {
        this.pruebas = pruebas.filter(prueba => {
          // Filtrar las pruebas
          const termino = this.terminoBusqueda.trim().toLowerCase().normalize("NFD").replace(/[\u0300-\u036f]/g, "");
          return prueba.resultado.toLowerCase().normalize("NFD").replace(/[\u0300-\u036f]/g, "").includes(termino) ||
            prueba.nombrePrueba.toLowerCase().normalize("NFD").replace(/[\u0300-\u036f]/g, "").includes(termino);
        });
      });
    } else {
      // Mostrar todos los estudiantes y pruebas nuevamente
      this.obtenerEstudiantesConPruebas();
      this.pruebaService.listar().subscribe(pruebas => {
        this.pruebas = pruebas;
      });
    }
  }

  // buscar(): void {
  //   if (this.terminoBusqueda.trim() !== '') {
  //     this.estudianteService.listar().subscribe(estudiantes => {
  //       this.estudiantes = estudiantes.filter(estudiante => {
  //         // Filtrar los estudiantes
  //         const termino = this.terminoBusqueda.trim().toLowerCase().normalize("NFD").replace(/[\u0300-\u036f]/g, "");
  //         return (
  //           estudiante.nombre.toLowerCase().normalize("NFD").replace(/[\u0300-\u036f]/g, "").includes(termino) ||
  //           estudiante.nov.toString().includes(termino) ||
  //           estudiante.car.toString().includes(termino)
  //         );
  //       });
  //     });
  //     this.pruebaService.listar().subscribe(pruebas => {
  //       this.pruebas = pruebas.filter(prueba => {
  //         // Filtrar las pruebas
  //         const termino = this.terminoBusqueda.trim().toLowerCase().normalize("NFD").replace(/[\u0300-\u036f]/g, "");
  //         return prueba.resultado.toLowerCase().normalize("NFD").replace(/[\u0300-\u036f]/g, "").includes(termino) ||
  //           prueba.nombrePrueba.toLowerCase().normalize("NFD").replace(/[\u0300-\u036f]/g, "").includes(termino);
  //       });
  //     });
  //   } else {
  //     // Mostrar todos los estudiantes y pruebas nuevamente
  //     this.obtenerEstudiantesConPruebas();
  //     this.pruebaService.listar().subscribe(pruebas => {
  //       this.pruebas = pruebas;
  //     });
  //   }
  // }

  borrarBusqueda(): void {
    this.terminoBusqueda = '';
    this.obtenerEstudiantesConPruebas();
    this.pruebaService.listar().subscribe(pruebas => {
      this.pruebas = pruebas;
    });
  }

  public logout(): void {
    this.loginService.logout();
    window.location.reload();
    console.log("Ha cerrado sesión el administrador");
  }
}

