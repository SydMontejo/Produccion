import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router'
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

//angular-material
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatMomentDateModule, MAT_MOMENT_DATE_ADAPTER_OPTIONS } from '@angular/material-moment-adapter';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MAT_DATE_LOCALE } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';

//componentes-clases
import { EstudiantesComponent } from './pages/estudiantes-vista-adm/estudiantes.component';
import { FormEstudianteComponent } from './pages/form-estudiante/form-estudiante.component';
import { LoginAdministradorComponent } from './pages/login-administrador/login-administrador.component';
import { LoginEstudiantesComponent } from './pages/login-estudiantes/login-estudiantes.component';
import { resumenesComponent } from './pages/resumenes/resumenes.component';
import { EstudianteVistaComponent } from './pages/estudiante-vista-est/estudiante-vista.component';
import { AgregarEstudiantesComponent } from './pages/agregar-estudiantes/agregar-estudiantes.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';

import { authInterceptorProviders } from './services/auth.interceptor';
import { AdminGuard } from './services/admin.guard';
import { FileUploadModule } from 'ng2-file-upload';
import { RECAPTCHA_SETTINGS, RecaptchaFormsModule, RecaptchaModule, RecaptchaSettings } from 'ng-recaptcha';

const routes:Routes=[
  { path:'', component:LoginEstudiantesComponent, pathMatch:'full'},
  { path:'loginadm',component:LoginAdministradorComponent},
  { path:'estudiantes', component:EstudiantesComponent, canActivate: [AdminGuard]},
  { path:'estudiantes/form', component:FormEstudianteComponent, canActivate: [AdminGuard]},
  { path:'estudiantes/form/:id/:nombre', component:FormEstudianteComponent, canActivate: [AdminGuard]},
  { path: 'estudiante/:nov', component: EstudianteVistaComponent, canActivate: [AdminGuard] },
  { path:'resumenes', component:resumenesComponent, canActivate: [AdminGuard]},
  { path:'agregar', component:AgregarEstudiantesComponent, canActivate: [AdminGuard]},
  { path:'**', component:NotFoundComponent },
]


@NgModule({
  declarations: [
    AppComponent,
    EstudiantesComponent,
    FormEstudianteComponent,
    LoginAdministradorComponent,
    LoginEstudiantesComponent,
    resumenesComponent,
    EstudianteVistaComponent,
    AgregarEstudiantesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    CommonModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    ReactiveFormsModule,
    MatIconModule,
    RouterModule.forRoot(routes),
    FormsModule,
    MatDatepickerModule,
    MatMomentDateModule,
    MatSnackBarModule,
    FileUploadModule,
    MatSelectModule,
    MatProgressBarModule,
    RecaptchaModule,
    RecaptchaFormsModule,
  ],
  providers: [ authInterceptorProviders,
    { provide: MAT_DATE_LOCALE, useValue: 'es-ES' }, // es-ES' configuración regional 
    { provide: MAT_MOMENT_DATE_ADAPTER_OPTIONS, useValue: { useUtc: true } }, // Opcional: UTC para evitar problemas de zona horaria
    {
      provide: RECAPTCHA_SETTINGS,
      useValue: {
        siteKey: "6LdzTNUoAAAAAD8sjH-lAHP2mSMfYCUKau36ug5K",//clave de reCAPTCHA
      } as RecaptchaSettings,
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
