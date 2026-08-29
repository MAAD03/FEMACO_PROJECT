import { inject, Pipe, PipeTransform } from '@angular/core';
import { GeneroService } from '../../services/catalogo-services/genero.service';

@Pipe({
  name: 'generoNombre',
  standalone: true,
  pure: false,
})
export class GeneroNombrePipe implements PipeTransform {
  private readonly generoService = inject(GeneroService);

  transform(idGenero: number | null | undefined): string {
    return this.generoService.getNombreById(idGenero);
  }
}
