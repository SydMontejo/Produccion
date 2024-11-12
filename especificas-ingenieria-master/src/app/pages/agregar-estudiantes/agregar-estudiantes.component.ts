import { Component } from '@angular/core';
import { FileUploader, FileItem, ParsedResponseHeaders } from 'ng2-file-upload';
import Swal from 'sweetalert2';

//produccion 
const URL = 'https://api-especificas.ingsistemascunori.org/importar';
//const URL = 'http://localhost:8080/importar';

@Component({
  selector: 'app-agregar-estudiantes',
  templateUrl: './agregar-estudiantes.component.html',
  styleUrls: ['./agregar-estudiantes.component.scss',]
})

export class AgregarEstudiantesComponent {
  titulo: string = "Importar Archivo de Excel";
  public uploader: FileUploader = new FileUploader({ url: URL, itemAlias: 'file' });
  
  constructor() {
    this.uploader.onCompleteItem = (item: FileItem, response: string, status: number, headers: ParsedResponseHeaders) => {
      Swal.close();
      if (status === 200) {
        Swal.fire({
          icon: 'success',
          title: 'Archivo agregado con éxito',
          showConfirmButton: false,
          timer: 2500
        }) 
      } else {
        Swal.fire({
          title: 'Error!',
          text: 'Error al agregar el archivo',
          icon: 'error',
          confirmButtonText: 'Aceptar'
        }) 
      }
    }
  }

  uploadAllWithLoading() {
    Swal.fire({
      title: 'Cargando...',
      allowOutsideClick: false
    });
    Swal.showLoading(null);
    this.uploader.uploadAll();
  }

//fin
}


