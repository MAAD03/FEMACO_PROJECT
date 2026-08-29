import { inject, Pipe, PipeTransform } from '@angular/core';
import { EstadoVentaService } from '../../services/catalogo-services/estado-venta.service';

@Pipe({
  name: 'estadoVentaNombre',
  standalone: true,
  pure: false,
})
export class EstadoVentaNombrePipe implements PipeTransform {
  private readonly estadoVentaService = inject(EstadoVentaService);

  transform(idEstadoVenta: number | null | undefined): string {
    return this.estadoVentaService.getNombreById(idEstadoVenta);
  }
}
