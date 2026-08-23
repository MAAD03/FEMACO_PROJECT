import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
    {path: '', redirectTo: '/dashboard', pathMatch: 'full'},
    { path: 'login', loadComponent: () => import('./components/Seguridad/login/login').then(m => m.Login) },
    { path: 'menu', canActivate: [authGuard], loadComponent: () => import('./components/Seguridad/menu/menu').then(m => m.Menu) },
    { path: 'modulo', canActivate: [authGuard], loadComponent: () => import('./components/Seguridad/modulo/modulo').then(m => m.modulo) },
    { path: 'rol', canActivate: [authGuard], loadComponent: () => import('./components/Seguridad/rol/rol').then(m => m.rol) },
    { path: 'opcion', canActivate: [authGuard], loadComponent: () => import('./components/Seguridad/opcion/opcion').then(m => m.Opcion) },
    { path: 'rol-opcion', canActivate: [authGuard], loadComponent: () => import('./components/Seguridad/rol-opcion/rol-opcion').then(m => m.RolOpcion) },
    { path: 'dashboard', canActivate: [authGuard], loadComponent: () => import('./components/Seguridad/dashboard/dashboard').then(m => m.Dashboard)}

];
