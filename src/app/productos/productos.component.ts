import { Component } from '@angular/core';
import { RequestService } from '../requestService.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrl: './productos.component.css'
})
export class ProductosComponent {

  producto: any[] = [];
  zona: any[] = [];
  categoria: any[] = [];
  productoFiltrado: any[] = [];

  sortColumn: string = '';
  sortDirection: 'asc' | 'desc' = 'asc';

  constructor(private requestService: RequestService, private http: HttpClient, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.obtenerProducto();
    this.cargarZona();
    this.cargarCategoria();
  }

  cargarZona() {
    this.requestService.verZona().subscribe(
      (data: any) => {
        console.log(data);
        this.zona = data.object;
      },
      (error) => {
        console.error('Error al obtener las zonas:', error);
      }
    );
  }

  cargarCategoria() {
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
  
  //MOSTRAR LOS PRODUCTOS
  obtenerProducto() {
    this.requestService.verProducto().subscribe(
      (data: any) => {
        console.log(data);
        this.producto = data.object;
        this.productoFiltrado = [...this.producto];
      },
      (error) => {
        console.error('Error al obtener los productos:', error);
      }
    );
  }

  crearProducto(){
    this.router.navigate(['/panel-ente/crear-producto'])
  }

  borrarProducto(producto: any){

    if (producto.idProducto){
      this.requestService.eliminarProducto(producto.idProducto, producto).subscribe(
        (data: any) => {
          console.log('Producto Eliminado:', data);
          alert('Producto eliminado correctamente');
          setTimeout(() => {
            window.location.reload();
          }, 200);
        },
        (error) => {
          console.error('Error al eliminar el producto:', error);
        }
      );
    } else {
      console.error('El ID del producto es inválido.')
    }

  }

  editarProducto(producto: any) {
    producto.editando = true;
  }

  //GUARDAR EDICION
  guardarProducto(producto: any) {
    console.log('ID del producto a actualizar:', producto.idProducto);
    this.requestService.updateProducto(producto.idProducto, producto).subscribe(
      (data: any) => {
        console.log('Producto actualizado:', data);
        producto.editando = false; 
        alert('Producto actualizado correctamente');
      },
      (error) => {
        console.error('Error al actualizar el producto:', error);
      }
    );
  
  }
  
  //CANCELAR LA EDICION
  cancelarEdicion(producto: any) {
    producto.editando = false; 
  }

  //PARTE DEL TURISTA
  

  esRutaTuristaProductos(): boolean {
    return this.router.url === '/turista/productos';
  }

  //PARA LOS FILTROS
  sortData(column: string) {
    if (this.sortColumn === column) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortColumn = column;
      this.sortDirection = 'asc';
    }

    this.producto.sort((a, b) => {
      const aValue = this.getNestedValue(a, column);
      const bValue = this.getNestedValue(b, column);

      if (aValue < bValue) {
        return this.sortDirection === 'asc' ? -1 : 1;
      } else if (aValue > bValue) {
        return this.sortDirection === 'asc' ? 1 : -1;
      } else {
        return 0;
      }
    });
  }

  getNestedValue(obj: any, path: string) {
    return path.split('.').reduce((value, key) => value[key], obj);
  }

  //FILTAR POR NOMBRE PRODUCTO
  filtrarPorProducto(event: any) {
    const nombreProducto = event.target.value;
    this.producto = this.productoFiltrado.filter(producto =>
      producto.nombreProducto.toLowerCase().includes(nombreProducto.toLowerCase())
    );
  }
    
}
