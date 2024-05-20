import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RequestService } from '../requestService.service';

@Component({
  selector: 'app-crear-zona',
  templateUrl: './crear-zona.component.html',
  styleUrl: './crear-zona.component.css'
})
export class CrearZonaComponent {

  zona: any = {};

  constructor(private router: Router, private requestService: RequestService) { }

  crearZona() {

    this.requestService.crearZona(this.zona).subscribe(
      (response) => {
        console.log('Zona creada correctamente:', response);
        setTimeout(() => {
          this.router.navigate(['/panel-ente/zonas']);
        }, 100);
      },
      (error) => {
        console.error('Error al crear la zona:', error);       
      }
    );
  }

}
