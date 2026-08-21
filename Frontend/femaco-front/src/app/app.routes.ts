import { Routes } from '@angular/router';

export const routes: Routes = [
    {path: '', redirectTo: '/login', pathMatch: 'full'},
    { path: 'login', loadComponent: () => import('./components/Seguridad/login/login').then(m => m.Login) },
    { path: 'menu', loadComponent: () => import('./components/Seguridad/menu/menu').then(m => m.Menu) },
    { path: 'modulo', loadComponent: () => import('./components/Seguridad/modulo/modulo').then(m => m.modulo) },
    { path: 'rol', loadComponent: () => import('./components/Seguridad/rol/rol').then(m => m.Rol) },
    { path: 'opcion', loadComponent: () => import('./components/Seguridad/opcion/opcion').then(m => m.Opcion) },
    { path: 'rol-opcion', loadComponent: () => import('./components/Seguridad/rol-opcion/rol-opcion').then(m => m.RolOpcion) },
    { path: 'dashboard' ,loadComponent: () => import('./components/Seguridad/dashboard/dashboard').then(m => m.Dashboard)}

];
