import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { ModuloMenu } from '../models/conjunto-menu.model';
import { API_BASE_URL } from '../config/api.config';

@Injectable({
  providedIn: 'root'
})
export class ConjuntoMenuService {
  private http = inject(HttpClient);
  private apiBaseUrl = inject(API_BASE_URL);

  private menuSubject = new BehaviorSubject<ModuloMenu[]>([]);
  public menu$ = this.menuSubject.asObservable();

  cargarMenu(): Observable<ModuloMenu[]> {
    return this.http.get<ModuloMenu[]>(`${this.apiBaseUrl}/conjuntoMenu/usuario`).pipe(
      tap(menu => this.menuSubject.next(menu))
    );
  }

  getMenuActual(): ModuloMenu[] {
    return this.menuSubject.value;
  }

  limpiarMenu(): void {
    this.menuSubject.next([]);
  }


  tienePermiso(idOpcion: number, tipo: 'alta' | 'baja' | 'cambio'): boolean {
    const menu = this.menuSubject.value;

    for (const modulo of menu) {
      for (const m of modulo.menus) {
        const opcion = m.opciones.find(o => o.idOpcion === idOpcion);
        if (opcion) {
          return opcion[tipo];
        }
      }
    }
    return false;
  }
}