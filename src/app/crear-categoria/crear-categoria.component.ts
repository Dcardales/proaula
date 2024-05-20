import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RequestService } from '../requestService.service';

@Component({
  selector: 'app-crear-categoria',
  templateUrl: './crear-categoria.component.html',
  styleUrl: './crear-categoria.component.css'
})
export class CrearCategoriaComponent {

  categoria: any = {};

  constructor(private router: Router, private requestService: RequestService) { }

  crearCategoria() {
    this.requestService.crearCategoria(this.categoria).subscribe(
      (response) => {
        console.log('Categoria creada correctamente:', response);
        setTimeout(() => {
          this.router.navigate(['/panel-ente/categorias']);
        }, 100);
      },
      (error) => {
        console.error('Error al crear la categoria:', error);       
      }
    );
  }

}
