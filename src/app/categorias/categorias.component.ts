import { Component } from '@angular/core';
import { RequestService } from '../requestService.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-categorias',
  templateUrl: './categorias.component.html',
  styleUrl: './categorias.component.css'
})
export class CategoriasComponent {

  categoria: any[] = [];
 

  constructor(private requestService: RequestService, private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.obtenerCategorias();
  }
  
  //MOSTRAR LAS CATEGORIAS
  obtenerCategorias() {
    this.requestService.verCategoria().subscribe(
      (data: any) => {
        console.log(data);
        this.categoria = data.object;
      },
      (error) => {
        console.error('Error al obtener las categorias:', error);
      }
    );
  }

  crearCategoria(){
    this.router.navigate(['/panel-ente/crear-categoria'])
  }

  borrarCategoria(categoria: any){

    if (categoria.idCategoia){
      this.requestService.eliminarCategoria(categoria.idCategoia, categoria).subscribe(
        (data: any) => {
          console.log('Categoria Eliminada:', data);
          alert('Categoría eliminada correctamente');
          setTimeout(() => {
            window.location.reload();
          }, 200);
        },
        (error) => {
          console.error('Error al eliminar la categoria:', error);
        }
      );
    } else {
      console.error('El ID de la categoria es inválido.')
    }

  }

  editarCategoria(categoria: any) {
    categoria.editando = true;
  }

  //GUARDAR EDICION
  guardarCategoria(categoria: any) {
    console.log('ID de la categoria a actualizar:', categoria.idCategoia);
    this.requestService.updateCategoria(categoria.idCategoia, categoria).subscribe(
      (data: any) => {
        console.log('Categoría actualizada:', data);
        categoria.editando = false; // Desactivar modo edición después de guardar cambios
        alert('Categoría actualizada correctamente');
      },
      (error) => {
        console.error('Error al actualizar la categoría:', error);
      }
    );
  
  }
  
  //CANCELAR LA EDICION
  cancelarEdicion(categoria: any) {
    categoria.editando = false; // Cancelar edición, desactivar modo edición
  }

}
