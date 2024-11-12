import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { EstudiantesService } from 'src/app/services/estudiante.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login-estudiantes',
  templateUrl: './login-estudiantes.component.html',
  styleUrls: ['./login-estudiantes.component.scss'],
})
export class LoginEstudiantesComponent implements OnInit {
  loginData = {
    "username": '',
    "password": '',
  }

  carreras = [
    { value: '21', viewValue: 'Ingeniería Civil' },
    { value: '22', viewValue: 'Ingeniería en Ciencias y Sistemas' },
    { value: '33', viewValue: 'Ingeniería Industrial' }
  ];

  token: string | undefined;

  constructor(public snack: MatSnackBar,
    public loginService: LoginService,
    public estudianteService: EstudiantesService,
    public router: Router) {
    this.token = undefined;
  }

  ngOnInit() {
  }

  formSubmit() {
    if (this.loginData.username.trim() == '' || this.loginData.username.trim() == null) {
      this.snack.open('El NOV/Carnet del estudiante es requerido !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }
    if (this.loginData.password.trim() == '' || this.loginData.password.trim() == null) {
      this.snack.open('La carrera es requerida !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }
    // Verifica si el captcha ha sido completado
    if (!this.token) {
      this.snack.open('Por favor, completa el captcha antes de iniciar sesión.', 'Aceptar', {
        duration: 3000
      })
      return;
    }

    this.loginService.generateToken(this.loginData).subscribe(
      (data: any) => {
        //console.log(data);
        this.loginService.loginUser(data.token, this.loginData.username);
        this.loginService.getCurrentUser().subscribe((user: any) => {
          this.loginService.setUser(user);
          //console.log(user);
          //this.router.navigate(['estudiante/:nov']);
          this.router.navigate(['estudiante', this.loginData.username]);
          let usernameNumber = Number(this.loginData.username);
          if (!isNaN(usernameNumber)) {
            this.estudianteService.getNov(usernameNumber).subscribe((pruebas: any) => {
              //console.log(pruebas);
            });
          } else {
            console.log('El nombre de usuario no es un número');
          }
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Datos Correctos',
            showConfirmButton: false,
            timer: 1500
          })
        })
      }, (error) => {
        console.log(error);
        let carrera = this.carreras.find(c => c.value === this.loginData.password);
        Swal.fire({
          title: 'Error!',
          html: `El NOV/Carnet: ${this.loginData.username}, <br> No coincide con la carrera: ${carrera ? carrera.viewValue : 'Carrera no encontrada'} <br><br> Posibles Causas:<br>- Aún no se han subido sus resultados.<br>- Datos incorrectos.`,
          icon: 'error',
          confirmButtonText: 'Aceptar'
        })
      }
    )
  }

//fin  
}
