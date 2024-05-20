import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class RequestService {

  //REQUESTS---

  private baseUrl = 'http://localhost:8090'; //URL Para las Requests

  constructor(private http: HttpClient) { }

  //Login Request
  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/api/auth/login`, { username, password });
  }

  //USUARIOS ---------------

  //Usuarios Requests - Crear
  crearUsuario(usuario: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/api/v1/usuarios`, usuario);
  }

  //Usuarios Requests - Ver perfiles
  verPerfiles(): Observable<any[]> {
    const url = `${this.baseUrl}/api/v1/usuarios`;
    return this.http.get<any[]>(url);
  }

  verTipoPerfil(usuario: any): Observable<any[]> {
    return this.http.post<any>(`${this.baseUrl}/api/v1/usuarios`, usuario);
  }

  //Usuarios Requests - Editar Usuarios
  updateUsuario(id: number, usuario: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/api/v1/usuarios/${id}`, usuario)
      .pipe(
        catchError(error => {
          throw 'Error al actualizar el usuario: ' + error;
        })
      );
  }

  //Usuarios - Eliminar usuarios
  eliminarUsuario(id: number, usuario: any): Observable<any> {
    return this.http.delete(`${this.baseUrl}/api/v1/usuarios/${id}`, usuario)
      .pipe(
        catchError(error => {
          throw 'Error al actualizar el usuario: ' + error;
        })
      );
  }

  //PERFILES -----------------

  //Crear Perfiles
  crearPerfil(perfil: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/api/v1/perfiles`, perfil);
  }

  //Ver perfiles
  verPerfilesP(): Observable<any[]> {
    const url = `${this.baseUrl}/api/v1/perfiles`;
    return this.http.get<any[]>(url);
  }

  //Editar perfiles
  updatePerfil(id: number, perfil: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/api/v1/perfiles/${id}`, perfil)
      .pipe(
        catchError(error => {
          throw 'Error al actualizar el perfil: ' + error;
        })
      );
  }

  //Eliminar perfiles
  eliminarPerfil(id: number, perfil: any): Observable<any> {
    return this.http.delete(`${this.baseUrl}/api/v1/perfiles/${id}`, perfil)
      .pipe(
        catchError(error => {
          throw 'Error al actualizar el perfil: ' + error;
        })
      );
  }

  //PRODUCTOS ------------

  //Crear productos
  crearProducto(producto: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/api/v1/productos`, producto);
  }

  //Ver productos
  verProducto(): Observable<any[]> {
    const url = `${this.baseUrl}/api/v1/productos`;
    return this.http.get<any[]>(url);
  }

  //Editar productos
  updateProducto(id: number, producto: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/api/v1/productos/${id}`, producto)
      .pipe(
        catchError(error => {
          throw 'Error al actualizar el producto: ' + error;
        })
      );
  }

  //Eliminar productos
  eliminarProducto(id: number, producto: any): Observable<any> {
    return this.http.delete(`${this.baseUrl}/api/v1/productos/${id}`, producto)
      .pipe(
        catchError(error => {
          throw 'Error al actualizar el producto: ' + error;
        })
      );
  }

  //ZONAS -------------

    //Crear zonas
    crearZona(zona: any): Observable<any> {
      return this.http.post<any>(`${this.baseUrl}/api/v1/zonas`, zona);
    }
  
    //Ver zonas
    verZona(): Observable<any[]> {
      const url = `${this.baseUrl}/api/v1/zonas`;
      return this.http.get<any[]>(url);
    }
  
    //Editar zonas
    updateZona(id: number, zona: any): Observable<any> {
      return this.http.put(`${this.baseUrl}/api/v1/zonas/${id}`, zona)
        .pipe(
          catchError(error => {
            throw 'Error al actualizar la zona: ' + error;
          })
        );
    }
  
    //Eliminar zonas
    eliminarZona(id: number, zona: any): Observable<any> {
      return this.http.delete(`${this.baseUrl}/api/v1/zonas/${id}`, zona)
        .pipe(
          catchError(error => {
            throw 'Error al actualizar la zona: ' + error;
          })
        );
    }

  
  //CATEGORIAS -----------


  //Crear Categorías
  crearCategoria(categoria: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/api/v1/categorias`, categoria);
  }

  //Ver categorias
  verCategoria(): Observable<any[]> {
    const url = `${this.baseUrl}/api/v1/categorias`;
    return this.http.get<any[]>(url);
  }

  //Editar categorias
  updateCategoria(id: number, categoria: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/api/v1/categorias/${id}`, categoria)
      .pipe(
        catchError(error => {
          throw 'Error al actualizar la categoria: ' + error;
        })
      );
  }

  //Eliminar categorias
  eliminarCategoria(id: number, categoria: any): Observable<any> {
    return this.http.delete(`${this.baseUrl}/api/v1/categorias/${id}`, categoria)
      .pipe(
        catchError(error => {
          throw 'Error al actualizar la categoria: ' + error;
        })
      );
  }
  

}