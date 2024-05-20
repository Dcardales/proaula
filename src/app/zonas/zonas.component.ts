import { Component } from '@angular/core';
import { RequestService } from '../requestService.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-zonas',
  templateUrl: './zonas.component.html',
  styleUrl: './zonas.component.css'
})
export class ZonasComponent {

  zona: any[] = [];


  constructor(private requestService: RequestService, private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.obtenerZonas();
  }

  //MOSTRAR LAS ZONAS
  obtenerZonas() {
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

  crearZona() {
    this.router.navigate(['/panel-ente/crear-zona'])
  }

  borrarZona(zona: any) {

    if (zona.idZona) {
      this.requestService.eliminarZona(zona.idZona, zona).subscribe(
        (data: any) => {
          console.log('Zona Eliminada:', data);
          alert('Zona eliminada correctamente');
          setTimeout(() => {
            window.location.reload();
          }, 200);
        },
        (error) => {
          console.error('Error al eliminar la Zona:', error);
        }
      );
    } else {
      console.error('El ID de la Zona es inválido.')
    }

  }

  editarZona(zona: any) {
    zona.editando = true;
  }

  //GUARDAR EDICION
  guardarZona(zona: any) {
    console.log('ID de la zona a actualizar:', zona.idZona);
    this.requestService.updateZona(zona.idZona, zona).subscribe(
      (data: any) => {
        console.log('Zona actualizada:', data);
        zona.editando = false; // Desactivar modo edición después de guardar cambios
        alert('Zona actualizada correctamente');
      },
      (error) => {
        console.error('Error al actualizar la zona:', error);
      }
    );

  }

  //CANCELAR LA EDICION
  cancelarEdicion(zona: any) {
    zona.editando = false;
  }

}
