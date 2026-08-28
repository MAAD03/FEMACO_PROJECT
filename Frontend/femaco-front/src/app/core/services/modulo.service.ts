import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable, of, tap } from 'rxjs';
import { API_BASE_URL } from '../config/api.config';
import { Modulo } from '../models/modulo.model';
import { modulo } from '../../components/Seguridad/modulo/modulo';

@Injectable({
  providedIn: 'root'
})
export class ModuloService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = inject(API_BASE_URL);
  private readonly api = `${this.baseUrl}/modulo`;
  private readonly moduloNombreCache = signal<Map<number, string>>(new Map());
  private cacheLoaded = false;


  buscarTodos(): Observable<Modulo[]> {
    return this.http.get<Modulo[]>(`${this.api}/buscar`);
  }

  crear(modulo: Modulo): Observable<Modulo> {
    return this.http.post<Modulo>(`${this.api}/crear`, modulo);
  }

  actualizar(idModulo: number, datos: Modulo): Observable<Modulo> {
    return this.http.put<Modulo>(`${this.api}/editar/${idModulo}`, datos);
  }

  eliminar(idModulo: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/eliminar/${idModulo}`);
  }

  loadNombreCache(): Observable<void> {
    if (this.cacheLoaded) {
      return of(void 0);
    }
  
    return this.buscarTodos().pipe(
      tap(modulo => {
        const map = new Map<number, string>();
        modulo.forEach(u => {
          if (u.idModulo != null) {
            map.set(u.idModulo, u.nombre);
          }
        });
        this.moduloNombreCache.set(map);
        this.cacheLoaded = true;
      }),
      map(() => void 0)
    );
  }
  
      getNombreById(idModulo: number | null | undefined): string {
          if (idModulo == null) return '—';
              return this.moduloNombreCache().get(idModulo) ?? `ID: ${idModulo}`;
      }
}