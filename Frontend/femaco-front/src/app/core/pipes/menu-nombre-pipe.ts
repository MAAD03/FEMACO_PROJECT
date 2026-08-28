import { inject, Pipe, PipeTransform } from '@angular/core';
import { MenuService } from '../services/menu.service';

@Pipe({
  name: 'menuNombre',
  standalone: true,
  pure: false,
})
export class MenuNombrePipe implements PipeTransform {
  private readonly menuService = inject(MenuService)
  transform(idMenu: number | null | undefined): string {
    return this.menuService.getNombreById(idMenu);
  }
}
