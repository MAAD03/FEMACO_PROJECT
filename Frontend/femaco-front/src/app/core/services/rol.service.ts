import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../config/api.config';
import { Rol } from '../models/rol.model';

@Injectable({
  providedIn: 'root'
})
export class RolService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = inject(API_BASE_URL);

  private readonly api = `${this.baseUrl}/rol`;

  buscarTodos(): Observable<Rol[]> {
    return this.http.get<Rol[]>(`${this.api}/buscar`);
  }

  crear(Rol: Rol): Observable<Rol> {
    return this.http.post<Rol>(`${this.api}/crear`, Rol);
  }

  actualizar(idRol: number, datos: Rol): Observable<Rol> {
    return this.http.put<Rol>(`${this.api}/editar/${idRol}`, datos);
  }

  eliminar(idRol: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/eliminar/${idRol}`);
  }
}