import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { MenuItem, ModuloMenu } from '../../core/models/conjunto-menu.model';
import { ConjuntoMenuService } from '../../core/services/conjunto-menu.service';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-sidebar',
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.css'
})
export class Sidebar {

  menuService = inject(ConjuntoMenuService);
  authService = inject(AuthService);
  private router = inject(Router);

  private expandedModulos = new Set<number>();
  private expandedMenus = new Set<number>();

  logout(): void {
    this.authService.logout();
  }
  
  isModuloExpanded(modulo: ModuloMenu): boolean {
    return this.expandedModulos.has(modulo.idModulo);
  }

  toggleModulo(modulo: ModuloMenu): void {
    this.toggleSetValue(this.expandedModulos, modulo.idModulo);
  }

  isMenuExpanded(menu: MenuItem): boolean {
    return this.expandedMenus.has(menu.idMenu);
  }

  toggleMenu(menu: MenuItem): void {
    this.toggleSetValue(this.expandedMenus, menu.idMenu);
  }

  isMenuActive(menu: MenuItem): boolean {
    return menu.opciones.some(opcion => this.router.isActive(opcion.pagina, false));
  }

  moduloActivo(): ModuloMenu | undefined {
    return this.menuService.getMenuActual().find(modulo =>
      modulo.menus.some(menu => this.isMenuActive(menu))
    );
  }

  menuActivo(modulo: ModuloMenu | undefined): MenuItem | undefined {
    return modulo?.menus.find(menu => this.isMenuActive(menu));
  }

  private toggleSetValue(collection: Set<number>, id: number): void {
    if (collection.has(id)) {
      collection.delete(id);
    } else {
      collection.add(id);
    }
  }
}