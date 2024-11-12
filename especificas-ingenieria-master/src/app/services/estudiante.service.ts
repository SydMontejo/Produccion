import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Estudiante } from '../model/estudiante';
import { EstudiantePruebasDTO } from '../model/estudiante-pruebas-dto'; // Importa nuevo DTO

@Injectable({
  providedIn: 'root'
})
export class EstudiantesService {
  // URL base para la API
  public URL:string="https://api-especificas.ingsistemascunori.org/api/estudiantes"
  //public URL: string = "http://localhost:8080/api/estudiantes"; // Usa la URL de producción en lugar de localhost cuando estés listo

  constructor(public http: HttpClient) { }

  // Obtener estudiantes con pruebas
  obtenerEstudiantesConPruebas(): Observable<EstudiantePruebasDTO[]> {
    return this.http.get<EstudiantePruebasDTO[]>(this.URL + '/con-pruebas');
  }

  // Obtener todos los estudiantes
  listar(): Observable<Estudiante[]> {
    return this.http.get<Estudiante[]>(this.URL);
  }

  // Crear un estudiante
  create(estudiante: Estudiante): Observable<Estudiante> {
    return this.http.post<Estudiante>(this.URL, estudiante);
  }

  // Obtener un estudiante por su ID
  getId(id: number): Observable<Estudiante> {
    return this.http.get<Estudiante>(this.URL + '/estudiantePorId/' + id);
  }

  // Actualizar un estudiante
  update(estudiante: Estudiante): Observable<Estudiante> {
    return this.http.put<Estudiante>(this.URL, estudiante);
  }

  // Eliminar un estudiante
  eliminar(id: number): Observable<Estudiante> {
    return this.http.delete<Estudiante>(this.URL + '/' + id);
  }

  // Crear una lista de estudiantes
  createList(estudiantes: Estudiante[]): Observable<any> {
    return this.http.post<any>(`${this.URL}/importados`, estudiantes);
  }

  // Obtener estudiantes por número de NOV
  getNov(nov: number): Observable<Estudiante[]> {
    return this.http.get<Estudiante[]>(this.URL + '/estudiantePorNov/' + nov);
  }

  // Obtener un estudiante por nombre
  getNombre(nombre: String): Observable<Estudiante> {
    return this.http.get<Estudiante>(this.URL + '/estudiante/' + nombre);
  }
}