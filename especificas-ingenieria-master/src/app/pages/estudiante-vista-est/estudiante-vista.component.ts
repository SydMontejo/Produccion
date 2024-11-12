import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'
import { EstudiantesService } from '../../services/estudiante.service';
import { Estudiante } from '../../model/estudiante';
import { Prueba } from '../../model/prueba';
import { LoginService } from './../../services/login.service';

@Component({
  selector: 'app-estudiante-vista',
  templateUrl: './estudiante-vista.component.html',
  styleUrls: ['./estudiante-vista.component.scss']
})
export class EstudianteVistaComponent implements OnInit {
  nombreEstudiante: string = "Resultado de:";
  nombreCarrera: string = "Carrera:";

  estudiante!: Estudiante;
  pruebas!: Prueba[];

  constructor(
    private router: Router,
    public estudianteService: EstudiantesService,
    public activatedRoute: ActivatedRoute,
    public changeDetector: ChangeDetectorRef,
    public login:LoginService) {
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      let nov = +params['nov'];
      if (nov) {
        this.estudianteService.getNov(nov).subscribe(
          estudiantes => {
            if (estudiantes && estudiantes.length > 0) {
              this.estudiante = estudiantes[0];
              this.pruebas = this.estudiante.pruebas;
              //console.log("PRUEBAS:", this.pruebas);
              this.changeDetector.detectChanges();
            }
          }
        )
      }
    })
  }

  public logout(){
    this.login.logout();
    this.router.navigate(['/']);
    console.log("Ha cerrado sesión el estudiante");
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

//fin    
}
