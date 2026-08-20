import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { LoginRequest, LoginResponse, UserData  } from '../models/auth.model';
import { Router } from '@angular/router';
import { API_BASE_URL } from '../config/api.config';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private http = inject(HttpClient);
  private router = inject(Router);
  private apiBaseUrl = inject(API_BASE_URL);

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