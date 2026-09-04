import { inject, Pipe, PipeTransform } from '@angular/core';
import { AreaArticuloService } from '../services/area-articulo.service';

@Pipe({
  name: 'areaArticuloNombre',
  standalone: true,
  pure: false,
})
export class AreaArticuloNombrePipe implements PipeTransform {
  private readonly areaArticuloService = inject(AreaArticuloService);
  transform(idAreaArticulo: number | null | undefined): string {
    return this.areaArticuloService.getNombreById(idAreaArticulo);
  }
}
