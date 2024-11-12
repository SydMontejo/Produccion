import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public currentNov: string | null = null;
  //produccion 
  public URL:string="https://api-especificas.ingsistemascunori.org"
  //public URL: string = "http://localhost:8080"

  constructor(public http: HttpClient) { }

  //generamos el token
  public generateToken(loginData: any) {
    return this.http.post(`${this.URL}/generate-token`, loginData);
  }

  public getCurrentUser() {
    return this.http.get(`${this.URL}/actual-usuario`);
  }

  //iniciamos sesión y establecemos el token en el localStorage
  public loginUser(token: string, username: string) {
    localStorage.setItem("token", token);
    // Verifica si el nombre de usuario es numérico
    if (/^\d+$/.test(username)) {
      localStorage.setItem("userType", "ESTUDIANTE");
    } else {
      localStorage.setItem("userType", "ADMINISTRADOR");
    }
    this.currentNov = username;
    return true;
  }

  public isLoggedIn() {
    let tokenStr = localStorage.getItem('token');
    if (tokenStr == undefined || tokenStr == '' || tokenStr == null) {
      return false;
    } else {
      return localStorage.getItem('userType');
    }
  }

  //cerramos sesion y eliminamos los datos del localStorage
  public logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('userType');
    localStorage.removeItem('_grecaptcha');
    return true;
  }

  //obtenemos el token
  public getToken() {
    return localStorage.getItem('token');
  }

  public setUser(user: any) {
  }

  public getCurrentNov() {
    //console.log('getCurrentNov devolviendo:', this.currentNov);
    return this.currentNov;
  }

  public getUser() {
    let userStr = localStorage.getItem('userType');
    if (userStr != null) {
      return JSON.parse(userStr);
    } else {
      this.logout();
      return null;
    }
  }

//fin  
}
