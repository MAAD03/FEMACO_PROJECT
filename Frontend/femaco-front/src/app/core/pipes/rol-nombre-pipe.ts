import { inject, Pipe, PipeTransform } from '@angular/core';
import { RolService } from '../services/rol.service';

@Pipe({
  name: 'rolNombre',
  standalone: true,
  pure: false,
})
export class RolNombrePipe implements PipeTransform {
  private readonly rolService = inject(RolService);

  transform(idRol: number | null | undefined): string {
    return this.rolService.getNombreById(idRol);
  }
}
