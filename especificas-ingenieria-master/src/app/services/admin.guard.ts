import { CanActivateFn, ActivatedRouteSnapshot, Router } from '@angular/router';
import { LoginService } from './login.service';
import { inject } from '@angular/core';

export const AdminGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state) => {
  const loginService = inject(LoginService);
  const router = inject(Router);

  if (loginService.isLoggedIn() === 'ESTUDIANTE') {
    const novInRoute = route.paramMap.get('nov');
    const currentNov = loginService.getCurrentNov();
    if (novInRoute !== currentNov) {
      if (currentNov) {
        router.navigateByUrl('/estudiante/' + currentNov).then(() => {
        });
      } else {
        loginService.logout();
        console.log('currentNov es null');
        router.navigate(['/']);
      }
      console.log("No puedes ingresar a esta dirección porque no corresponde a tu NOV");
      return false;
    }
    return true;
  } else if (loginService.isLoggedIn() === 'ADMINISTRADOR') {
    return true;
  } else {
    // Redirige a la página de inicio de sesión
    router.navigate(['/']);
    console.log("No puedes ingresar a esta dirección porque no has iniciado sesión");
    return false;
  }

//fin  
}



