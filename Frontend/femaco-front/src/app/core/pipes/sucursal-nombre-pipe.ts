import { inject, Pipe, PipeTransform } from '@angular/core';
import { SucursalService } from '../services/sucursal.service';

@Pipe({
  name: 'sucursalNombre',
  standalone: true,
  pure: false,
})
export class SucursalNombrePipe implements PipeTransform {
  private readonly sucursalService = inject(SucursalService);
  transform(idSucursal: number | null | undefined): string {
    return this.sucursalService.getNombreById(idSucursal);
  }
}
