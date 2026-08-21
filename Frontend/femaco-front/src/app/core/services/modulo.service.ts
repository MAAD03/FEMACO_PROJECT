import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../config/api.config';
import { Modulo } from '../models/modulo.model';

@Injectable({
  providedIn: 'root'
})
export class ModuloService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = inject(API_BASE_URL);

  private readonly api = `${this.baseUrl}/modulo`;

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
}