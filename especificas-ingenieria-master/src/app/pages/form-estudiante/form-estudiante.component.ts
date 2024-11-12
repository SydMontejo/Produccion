import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Estudiante } from 'src/app/model/estudiante';
import { EstudiantesService } from 'src/app/services/estudiante.service';
import { Prueba } from 'src/app/model/prueba';
import { PruebaService } from 'src/app/services/prueba.service';
import { Administrador } from 'src/app/model/administrador';
import { AdminService } from 'src/app/services/admin.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form-estudiante',
  templateUrl: './form-estudiante.component.html',
  styleUrls: ['./form-estudiante.component.scss']
})
export class FormEstudianteComponent implements OnInit {
  estudiante: Estudiante = new Estudiante();
  prueba: Prueba = new Prueba();
  admin: Administrador = new Administrador();

  titulo: string = "Registro del Estudiante";

  minDate?: Date;
  maxDate?: Date;

  constructor(public estudianteService: EstudiantesService,
    public pruebaService: PruebaService,
    public adminService: AdminService,
    public router: Router,
    public activadeRouter: ActivatedRoute) {

    this.minDate = new Date(2000, 0, 1);
    this.maxDate = new Date(2100, 0, 1);
  }

  ngOnInit(): void {
    this.activadeRouter.params.subscribe(
      p => {
        let id = p['id']
        let nombre = p['nombre']
        if (id) {
          this.pruebaService.get(id).subscribe(
            p => { this.prueba = p }
          )
        }
        if (nombre){
          this.estudianteService.getNombre(nombre).subscribe(
            e => { this.estudiante = e }
          )
        }
      }
    )
  }
  
  update(): void {
    this.prueba.estudiante = this.estudiante;
    this.pruebaService.update(this.prueba).subscribe(
      () => {
        this.estudianteService.update(this.estudiante).subscribe(
          () => {
            this.router.navigate(['/estudiantes']);
            Swal.fire({
              position: 'top-end',
              icon: 'success',
              title: 'Datos Actualizados',
              showConfirmButton: false,
              timer: 1500
            });
          }
        );
      }
    );
  }
  
//fin
}
// import { Component, OnInit } from '@angular/core';
// import { ActivatedRoute, Router } from '@angular/router';
// import { Estudiante } from 'src/app/model/estudiante';
// import { EstudiantesService } from 'src/app/services/estudiante.service';
// import { Prueba } from 'src/app/model/prueba';
// import { PruebaService } from 'src/app/services/prueba.service';
// import Swal from 'sweetalert2';

// @Component({
//   selector: 'app-form-estudiante',
//   templateUrl: './form-estudiante.component.html',
//   styleUrls: ['./form-estudiante.component.scss']
// })
// export class FormEstudianteComponent implements OnInit {
//   estudiante: Estudiante = new Estudiante();
//   prueba: Prueba = new Prueba();

//   titulo: string = "Registro del Estudiante";

//   minDate?: Date;
//   maxDate?: Date;

//   constructor(
//     public estudianteService: EstudiantesService,
//     public pruebaService: PruebaService,
//     public router: Router,
//     public activadeRouter: ActivatedRoute) {

//     this.minDate = new Date(2000, 0, 1);
//     this.maxDate = new Date(2100, 0, 1);
//   }

//   ngOnInit(): void {
//     this.activadeRouter.params.subscribe(
//       p => {
//         let nombre = p['nombre'];

//         if (nombre) {
//           this.estudianteService.getNombre(nombre).subscribe(
//             e => {
//               this.estudiante = e;

//               // Buscar prueba por estudiante_id y nombre_prueba
//               this.pruebaService.getPruebaPorEstudianteYNombre(e.id, 'Computacion') // Puedes ajustar el nombre de la prueba aquí
//                 .subscribe(
//                   prueba => {
//                     if (prueba) {
//                       this.prueba = prueba;
//                     } else {
//                       console.log('No se encontró la prueba.');
//                     }
//                   }
//                 );
//             }
//           );
//         }
//       }
//     );
//   }

//   update(): void {
//     if (!this.estudiante.id) {
//       Swal.fire({
//         position: 'top-end',
//         icon: 'error',
//         title: 'No se puede actualizar',
//         text: 'El Estudiante no tiene ID asignado.',
//         showConfirmButton: true
//       });
//       return;
//     }

//     if (!this.prueba.nombrePrueba) {
//       Swal.fire({
//         position: 'top-end',
//         icon: 'error',
//         title: 'No se puede actualizar',
//         text: 'El nombre de la prueba no está asignado.',
//         showConfirmButton: true
//       });
//       return;
//     }

//     if (!this.prueba.id) {
//       Swal.fire({
//         position: 'top-end',
//         icon: 'error',
//         title: 'No se puede actualizar',
//         text: 'La Prueba no tiene ID asignado.',
//         showConfirmButton: true
//       });
//       return;
//     }

//     // Asignar el estudiante a la prueba antes de la actualización
//     this.prueba.estudiante = this.estudiante;

//     // Actualizar la prueba
//     this.pruebaService.update(this.prueba).subscribe(
//       () => {
//         Swal.fire({
//           position: 'top-end',
//           icon: 'success',
//           title: 'Datos Actualizados',
//           showConfirmButton: false,
//           timer: 1500
//         });
//         this.router.navigate(['/estudiantes']);
//       }
//     );
//   }
// }
