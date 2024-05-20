import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RequestService } from '../requestService.service';

@Component({
  selector: 'app-crear-producto',
  templateUrl: './crear-producto.component.html',
  styleUrl: './crear-producto.component.css'
})
export class CrearProductoComponent {

  producto: any = {};
  categoria: any[] = [];
  zona: any[] = [];

  constructor(private router: Router, private requestService: RequestService) { }

  ngOnInit(){
    this.cargarCategorias();
    this.cargarZonas();
  }

  crearProducto() {
 
    this.requestService.crearProducto(this.producto).subscribe(
      (response) => {
        console.log('Producto creado correctamente:', response);
        setTimeout(() => {
          this.router.navigate(['/panel-ente/productos']);
        }, 100);
      },
      (error) => {
        console.error('Error al crear el producto:', error);       
      }
    );
  }

  cargarCategorias() {
    this.requestService.verCategoria().subscribe(
      (data: any) => {
        this.categoria = data.object;
      },
      (error) => {
        console.error('Error al cargar Categoria por ID:', error);
      }
    );
  }

  cargarZonas() {
    this.requestService.verZona().subscribe(
      (data: any) => {
        this.zona = data.object;
      },
      (error) => {
        console.error('Error al cargar Zona por ID:', error);
      }
    );
  }
  

}
