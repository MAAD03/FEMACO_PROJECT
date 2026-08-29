import { inject, Pipe, PipeTransform } from '@angular/core';
import { EstadoProveedorService } from '../../services/catalogo-services/estado-proveedor.service';

@Pipe({
  name: 'estadoProveedorNombre',
  standalone: true,
  pure: false,
})
export class EstadoProveedorNombrePipe implements PipeTransform {
  private readonly estadoProveedorService = inject(EstadoProveedorService);

  transform(idEstadoProveedor: number | null | undefined): string {
    return this.estadoProveedorService.getNombreById(idEstadoProveedor);
  }
}
