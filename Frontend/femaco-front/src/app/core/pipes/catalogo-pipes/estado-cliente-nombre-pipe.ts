import { inject, Pipe, PipeTransform } from '@angular/core';
import { EstadoClienteService } from '../../services/catalogo-services/estado-cliente.service';

@Pipe({
  name: 'estadoClienteNombre',
  standalone: true,
  pure: false,
})
export class EstadoClienteNombrePipe implements PipeTransform {
  private readonly estadoClienteService = inject(EstadoClienteService);

  transform(idEstadoCliente: number | null | undefined): string {
    return this.estadoClienteService.getNombreById(idEstadoCliente);
  }
}
