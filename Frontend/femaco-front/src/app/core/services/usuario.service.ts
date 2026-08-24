import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, tap, map, shareReplay } from 'rxjs';
import { API_BASE_URL } from '../config/api.config';
import { Usuario } from '../models/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = inject(API_BASE_URL);
  private readonly api = `${this.baseUrl}/usuario`;
  private readonly emailCache = signal<Map<number, string>>(new Map());
  private cacheLoaded = false;

  buscarTodos(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`${this.api}/buscar`);
  }

  crear(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(`${this.api}/crear`, usuario);
  }

  actualizar(idUsuario: number, datos: Usuario): Observable<Usuario> {
    return this.http.put<Usuario>(`${this.api}/editar/${idUsuario}`, datos);
  }

  eliminar(idUsuario: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/eliminar/${idUsuario}`);
  }

  loadEmailCache(): Observable<void> {
  if (this.cacheLoaded) {
    return of(void 0);
  }

  return this.buscarTodos().pipe(
    tap(usuarios => {
      const map = new Map<number, string>();
      usuarios.forEach(u => {
        if (u.idUsuario != null) {
          map.set(u.idUsuario, u.correoElectronico);
        }
      });
      this.emailCache.set(map);
      this.cacheLoaded = true;
    }),
    map(() => void 0)
  );
}

    getEmailById(idUsuario: number | null | undefined): string {
        if (idUsuario == null) return '—';
            return this.emailCache().get(idUsuario) ?? `ID: ${idUsuario}`;
    }
}
