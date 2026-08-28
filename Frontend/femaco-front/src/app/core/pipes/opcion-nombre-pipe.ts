import { inject, Pipe, PipeTransform } from '@angular/core';
import { OpcionService } from '../services/opcion.service';

@Pipe({
  name: 'opcionNombre',
  standalone: true,
  pure: false,
})
export class OpcionNombrePipe implements PipeTransform {
  private readonly opcionService = inject(OpcionService);

  transform(idOpcion: number | null | undefined): string {
    return this.opcionService.getNombreById(idOpcion);
  }
}
