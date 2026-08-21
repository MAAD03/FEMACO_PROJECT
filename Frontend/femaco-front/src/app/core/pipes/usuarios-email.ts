import { Pipe, PipeTransform, Inject, inject } from '@angular/core';
import { UsuarioService } from '../services/usuario.service';

@Pipe({
  name: 'userEmail',
  standalone: true,
  pure: true
})
export class UsuariosEmailPipe implements PipeTransform {
  private readonly usuarioService = inject(UsuarioService)

  transform(idUsuario: number | null | undefined): string {
    return this.usuarioService.getEmailById(idUsuario);
  }
}
