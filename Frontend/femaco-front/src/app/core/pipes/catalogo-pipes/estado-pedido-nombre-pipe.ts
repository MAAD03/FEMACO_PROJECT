import { inject, Pipe, PipeTransform } from '@angular/core';
import { EstadoPedidoService } from '../../services/catalogo-services/estado-pedido.service';

@Pipe({
  name: 'estadoPedidoNombre',
  standalone: true,
  pure: false,
})
export class EstadoPedidoNombrePipe implements PipeTransform {
  private readonly estadoPedidoService = inject(EstadoPedidoService);

  transform(idEstadoPedido: number | null | undefined): string {
    return this.estadoPedidoService.getNombreById(idEstadoPedido);
  }
}
