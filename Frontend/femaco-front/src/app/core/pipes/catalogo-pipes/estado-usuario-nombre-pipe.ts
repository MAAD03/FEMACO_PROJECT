import { inject, Pipe, PipeTransform } from '@angular/core';
import { EstadoUsuarioService } from '../../services/catalogo-services/estado-usuario.service';

@Pipe({
  name: 'estadoUsuarioNombre',
  standalone: true,
  pure: false,
})
export class EstadoUsuarioNombrePipe implements PipeTransform {
  private readonly estadoUsuarioService = inject(EstadoUsuarioService);

  transform(idEstadoUsuario: number | null | undefined): string {
    return this.estadoUsuarioService.getNombreById(idEstadoUsuario);
  }
}
