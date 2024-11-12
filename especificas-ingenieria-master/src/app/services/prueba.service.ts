import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Prueba } from '../model/prueba';

@Injectable({
  providedIn: 'root'
})
export class PruebaService {

  //produccion 
  public URL:string="https://api-especificas.ingsistemascunori.org/api/pruebas";
  //public URL:string='http://localhost:8080/api/pruebas';

  constructor(public http: HttpClient) { }

  //obtener pruebas
  listar(){
    return this.http.get<Prueba[]>(this.URL);
  }
  
  //crear prueba
  create(prueba:Prueba):Observable<Prueba>{
    return this.http.post<Prueba>(this.URL, prueba)
  }

  //obtener una prueba
  get(id:number):Observable<Prueba>{
    return this.http.get<Prueba>(this.URL+'/pruebaPorId/'+id)
  }

  //actualizar prueba
  update(prueba: Prueba):Observable<Prueba>{
    return this.http.put<Prueba>(this.URL, prueba)
  }

  //eliminar una prueba
  eliminar(id:number):Observable<Prueba>{
    return this.http.delete<Prueba>(this.URL+'/'+id)
  } 

  //Nuevo servicio agregado
  getPruebasPorEstudiante(estudianteId: number): Observable<Prueba[]> {
    return this.http.get<Prueba[]>(`${this.URL}/pruebasPorEstudiante/${estudianteId}`);
  }

//fin
}
