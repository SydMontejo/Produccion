import { Router } from '@angular/router';
import { LoginService } from './../../services/login.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login-administrador',
  templateUrl: './login-administrador.component.html',
  styleUrls: ['./login-administrador.component.scss']
})
export class LoginAdministradorComponent implements OnInit {
  hide: boolean = true;
  loginData = {
    "username": '',
    "password": '',
  }

  constructor(public snack: MatSnackBar, 
    public loginService: LoginService, 
    public router: Router) {
  }

  ngOnInit() {
  }

  formSubmit() {
    if (this.loginData.username.trim() == '' || this.loginData.username.trim() == null) {
      this.snack.open('El nombre de usuario es requerido !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }
    if (this.loginData.password.trim() == '' || this.loginData.password.trim() == null) {
      this.snack.open('La contraseña es requerida !!', 'Aceptar', {
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
          this.router.navigate(['estudiantes']);
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            html: `Datos Correctos.<br>Ha iniciado sesión.`,
            showConfirmButton: false,
            timer: 1500
          })
        })
      }, (error) => {
        console.log(error);
        Swal.fire({
          title: 'Error!',
          html: `Datos Incorrectos.<br>Estas credenciales no coinciden con nuestros registros.`,
          icon: 'error',
          confirmButtonText: 'Aceptar'
        }) 
      }
    )
  }

//fin  
}
