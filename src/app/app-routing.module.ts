import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TuristaComponent } from './turista/turista.component';
import { InicioSesionComponent } from './inicio-sesion/inicio-sesion.component';
import { CRUDAEComponent } from './crud-ae/crud-ae.component';
import { CrearUsuarioComponent } from './crear-usuario/crear-usuario.component';
import { CrudEComponent } from './crud-e/crud-e.component';
import { PerfilesComponent } from './perfiles/perfiles.component';
import { CrearPerfilComponent } from './crear-perfil/crear-perfil.component';
import { PerfilesPComponent } from './perfiles-p/perfiles-p.component';
import { CrearCategoriaComponent } from './crear-categoria/crear-categoria.component';
import { CrearProductoComponent } from './crear-producto/crear-producto.component';
import { CrearZonaComponent } from './crear-zona/crear-zona.component';
import { ProductosComponent } from './productos/productos.component';
import { ZonasComponent } from './zonas/zonas.component';
import { CategoriasComponent } from './categorias/categorias.component';

const routes: Routes = [

  { path: '', redirectTo: '/turista', pathMatch: 'full' }, 
  { path: 'turista', component: TuristaComponent },
  { path: 'turista/productos', component: ProductosComponent },
  { path: 'inicio-sesion', component: InicioSesionComponent },

  //PANEL ADMIN
  { path: 'panel-admin', component: CRUDAEComponent, children: [
    { path: 'usuarios', component: PerfilesComponent }, { path: 'crear-usuario', component: CrearUsuarioComponent }, {path: 'crear-perfil', component: CrearPerfilComponent}, {path: 'perfiles', component: PerfilesPComponent}
  ]},

  //PANEL ENTE
  { path: 'panel-ente', component: CrudEComponent, children: [
    { path: 'crear-producto', component: CrearProductoComponent }, { path: 'crear-zona', component: CrearZonaComponent }, {path: 'crear-categoria', component: CrearCategoriaComponent}, {path: 'productos', component: ProductosComponent},
    {path: 'zonas', component: ZonasComponent}, {path: 'categorias', component: CategoriasComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
