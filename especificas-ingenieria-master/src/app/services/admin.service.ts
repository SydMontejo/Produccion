import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Administrador } from '../model/administrador';

@Injectable({
  providedIn: 'root'
})

export class AdminService {
  //produccion 
  public URL:string="https://api-especificas.ingsistemascunori.org/api/administrador"
  //public URL:string="http://localhost:8080/api/administrador" 

  constructor(public http:HttpClient) { }

  //obtener todos los administradores
  listar(): Observable<Administrador[]>{
    return this.http.get<Administrador[]>(this.URL);
  }
  //crear administrador
  create(admin:Administrador):Observable<Administrador>{
    return this.http.post<Administrador>(this.URL, admin)
  }
  //actualizar administrador
  update(admin: Administrador):Observable<Administrador>{
    return this.http.put<Administrador>(this.URL, admin)
  }
  //eliminar un administrador
  eliminar(id:number):Observable<Administrador>{
    return this.http.delete<Administrador>(this.URL+'/'+id)
  }
  //obtener un administrador por su nombre
  getUsername(username:String):Observable<Administrador>{
    return this.http.get<Administrador>(this.URL+'/'+username)
  }

//fin  
}
