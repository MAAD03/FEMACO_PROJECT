import { inject, Pipe, PipeTransform } from '@angular/core';
import { ModuloService } from '../services/modulo.service';

@Pipe({
  name: 'modulosNombre',
})
export class ModulosNombrePipe implements PipeTransform {
  private readonly moduloService = inject(ModuloService)

  transform(idModulo: number | null | undefined): string {
    return this.moduloService.getNombreById(idModulo);
  }
}
