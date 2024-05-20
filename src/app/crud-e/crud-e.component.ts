import { Component } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { RequestService } from '../requestService.service';

@Component({
  selector: 'app-crud-e',
  templateUrl: './crud-e.component.html',
  styleUrl: './crud-e.component.css'
})
export class CrudEComponent {

  showBackgroundImage: boolean = true;

  constructor(private router: Router, private requestService: RequestService, private route: ActivatedRoute) { 

    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.showBackgroundImage = this.router.url === '/panel-ente';
      }
    });
    
  }

  isSidebarCollapsed = false;

  toggleSidebar() {
    this.isSidebarCollapsed = !this.isSidebarCollapsed;
    const sidebar = document.getElementById('sidebar');
    const content = document.querySelector('.content');
    
    if (sidebar && content) {
      if (this.isSidebarCollapsed) {
        sidebar.classList.add('collapsed');
        content.classList.add('collapsed');
      } else {
        sidebar.classList.remove('collapsed');
        content.classList.remove('collapsed');
      }
    }
  }

  ngOnInit(){

  }

    //PARTE DEL ENTE

    //PRODUCTOS
    verProductos(){
      this.router.navigate(['/panel-ente/productos'])
    }
  
    //ZONAS
    verZonas(){
      this.router.navigate(['/panel-ente/zonas'])
    }
  
    //CATEGORIAS
    verCategorias(){
      this.router.navigate(['/panel-ente/categorias'])
    }

}
