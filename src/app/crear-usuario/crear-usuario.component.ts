import { Component } from '@angular/core';
import { RequestService } from '../requestService.service';
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-crear-usuario',
  templateUrl: './crear-usuario.component.html',
  styleUrl: './crear-usuario.component.css'
})
export class CrearUsuarioComponent {

  usuario: any = {}; // Objeto para almacenar los datos del nuevo usuario
  perfil: any[] = [];

  constructor(private router: Router, private requestService: RequestService) { }

  ngOnInit(){
    this.cargarPerfiles();
  }

  crearUsuario() {
    this.requestService.crearUsuario(this.usuario).subscribe(
      (response) => {
        console.log('Usuario creado correctamente:', response);
        setTimeout(() => {
          this.router.navigate(['/panel-admin/usuarios']);
        }, 100);
      },
      (error) => {
        console.error('Error al crear usuario:', error);
        
      }
    );
  }

  cargarPerfiles() {
    this.requestService.verPerfilesP().subscribe(
      (data: any) => {
        this.perfil = data.object;
      },
      (error) => {
        console.error('Error al cargar perfil por ID:', error);
      }
    );
  }

}
