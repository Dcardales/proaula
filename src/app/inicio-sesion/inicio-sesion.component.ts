import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RequestService } from '../requestService.service';

@Component({
  selector: 'app-inicio-sesion',
  templateUrl: './inicio-sesion.component.html',
  styleUrls: ['./inicio-sesion.component.css'],
})
export class InicioSesionComponent {

  username: string = '';
  password: string = '';
  mensaje: string = '';
  loginError: boolean = false;

  constructor(private router: Router, private requestService: RequestService) { }

  login(): void {

    this.requestService.login(this.username, this.password).subscribe(
      response => {
        console.log("Respuesta del backend:", response);
        if (response && response.perfil) {
          
          if (response.perfil === "Admin") {
            this.router.navigate(['/panel-admin']);

          } else if (response.perfil === "Ente") {
            this.router.navigate(['/panel-ente']);

          } else {
            console.error("Perfil no reconocido:", response.perfil);
          }

        } else {
          console.error("Perfil no proporcionado en la respuesta.");
        }
      },
      
      error => {
        console.error("Error en la solicitud:", error);    

        //Recargar la página si el usuario es incorrecto
        alert("Usuario y/o contraseña incorrectos")
        setTimeout(() => {
          window.location.reload();
        }, 10);
      }
    );

  }

  register() {
    throw new Error('Method not implemented.');
    }

}