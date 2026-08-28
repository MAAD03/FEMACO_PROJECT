import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { LoginRequest, LoginResponse, UserData  } from '../models/auth.model';
import { Router } from '@angular/router';
import { API_BASE_URL } from '../config/api.config';
import { ConjuntoMenuService } from './conjunto-menu.service';
import { MenuService } from './menu.service';
import { ModuloService } from './modulo.service';
import { OpcionService } from './opcion.service';
import { UsuarioService } from './usuario.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private http = inject(HttpClient);
  private router = inject(Router);
  private apiBaseUrl = inject(API_BASE_URL);
  private conjuntoMenuService = inject(ConjuntoMenuService);
  private menuService = inject(MenuService);
  private moduloService = inject(ModuloService);
  private opcionService = inject(OpcionService);
  private usuarioService = inject(UsuarioService);

  private readonly STORAGE_KEY = 'auth_user';
  private currentUserSubject = new BehaviorSubject<UserData | null>(this.getUserFromStorage());
  public currentUser$ = this.currentUserSubject.asObservable();

    login(credential: LoginRequest): Observable<LoginResponse> {
        return this.http.post<LoginResponse>(`${this.apiBaseUrl}/auth/login`, credential).pipe(
            tap(response => {
                const userData: UserData = {
                    token: response.token,
                    idUsuario: response.idUsuario,
                    nombre: response.nombre
                };
                this.saveUser(userData);
                this.currentUserSubject.next(userData);
            })
        );
    }

    logout(): void {
        localStorage.removeItem(this.STORAGE_KEY);
        this.currentUserSubject.next(null);
        this.conjuntoMenuService.limpiarMenu();
        this.menuService.clearCache();
        this.moduloService.clearCache();
        this.opcionService.clearCache();
        this.usuarioService.clearCache();
        this.router.navigate(['/login']);
    }

    getToken(): string | null {
        return this.getUserFromStorage()?.token || null;
    }

    isAuthenticated(): boolean {
        return !!this.getToken();
    }

    getCurrentUser(): UserData | null {
        return this.currentUserSubject.value;
    }

    private saveUser(user: UserData): void {
        localStorage.setItem(this.STORAGE_KEY, JSON.stringify(user));
    }
    
    private getUserFromStorage(): UserData | null {
        const data = localStorage.getItem(this.STORAGE_KEY);
        return data ? JSON.parse(data) : null;
    }
}