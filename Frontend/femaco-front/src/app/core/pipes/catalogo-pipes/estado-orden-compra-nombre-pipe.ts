import { inject, Pipe, PipeTransform } from '@angular/core';
import { EstadoOrdenCompraService } from '../../services/catalogo-services/estado-orden-compra.service';

@Pipe({
  name: 'estadoOrdenCompraNombre',
  standalone: true,
  pure: false,
})
export class EstadoOrdenCompraNombrePipe implements PipeTransform {
  private readonly estadoOrdenCompraService = inject(EstadoOrdenCompraService);

  transform(idEstadoOrdenCompra: number | null | undefined): string {
    return this.estadoOrdenCompraService.getNombreById(idEstadoOrdenCompra);
  }
}
