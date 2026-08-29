import { inject, Pipe, PipeTransform } from '@angular/core';
import { EstadoArticuloService } from '../../services/catalogo-services/estado-articulo.service';

@Pipe({
  name: 'estadoArticuloNombre',
  standalone: true,
  pure: false,
})
export class EstadoArticuloNombrePipe implements PipeTransform {
  private readonly estadoArticuloService = inject(EstadoArticuloService);

  transform(idEstadoArticulo: number | null | undefined): string {
    return this.estadoArticuloService.getNombreById(idEstadoArticulo);
  }
}
