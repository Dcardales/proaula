import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InicioSesionComponent } from './inicio-sesion/inicio-sesion.component';
import { TuristaComponent } from './turista/turista.component';
import { CRUDAEComponent } from './crud-ae/crud-ae.component';
import { CrearUsuarioComponent } from './crear-usuario/crear-usuario.component';
import { PerfilesComponent } from './perfiles/perfiles.component';
import { CrudEComponent } from './crud-e/crud-e.component';
import { CrearPerfilComponent } from './crear-perfil/crear-perfil.component';
import { PerfilesPComponent } from './perfiles-p/perfiles-p.component';
import { CrearProductoComponent } from './crear-producto/crear-producto.component';
import { CrearZonaComponent } from './crear-zona/crear-zona.component';
import { CrearCategoriaComponent } from './crear-categoria/crear-categoria.component';
import { ProductosComponent } from './productos/productos.component';
import { ZonasComponent } from './zonas/zonas.component';
import { CategoriasComponent } from './categorias/categorias.component';
import { ModalModule } from 'ngx-bootstrap/modal';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

@NgModule({
  declarations: [
    AppComponent,
    InicioSesionComponent,
    TuristaComponent,
    CRUDAEComponent,
    CrearUsuarioComponent,
    PerfilesComponent,
    CrudEComponent,
    CrearPerfilComponent,
    PerfilesPComponent,
    CrearProductoComponent,
    CrearZonaComponent,
    CrearCategoriaComponent,
    ProductosComponent,
    ZonasComponent,
    CategoriasComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ModalModule.forRoot()
  ],
  providers: [
    provideClientHydration(),
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
