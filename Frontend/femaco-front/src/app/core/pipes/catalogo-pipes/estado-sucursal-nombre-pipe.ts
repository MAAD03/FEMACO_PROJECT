import { inject, Pipe, PipeTransform } from '@angular/core';
import { EstadoSucursalService } from '../../services/catalogo-services/estado-sucursal.service';

@Pipe({
  name: 'estadoSucursalNombre',
  standalone: true,
  pure: false,
})
export class EstadoSucursalNombrePipe implements PipeTransform {
  private readonly estadoSucursalService = inject(EstadoSucursalService);

  transform(idEstadoSucursal: number | null | undefined): string {
    return this.estadoSucursalService.getNombreById(idEstadoSucursal);
  }
}
