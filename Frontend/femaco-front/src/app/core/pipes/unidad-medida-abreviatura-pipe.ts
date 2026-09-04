import { inject, Pipe, PipeTransform } from '@angular/core';
import { UnidadMedidaService } from '../services/unidad-medida.service';

@Pipe({
  name: 'unidadMedidaAbreviatura',
  standalone: true,
  pure: false,
})
export class UnidadMedidaAbreviaturaPipe implements PipeTransform {
  private readonly unidadMedidaService = inject(UnidadMedidaService);

  transform(idUnidadMedida: number | null | undefined): string {
    return this.unidadMedidaService.getAbreviaturaById(idUnidadMedida);
  }
}
