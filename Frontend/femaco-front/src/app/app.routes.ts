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
    { path: 'dashboard', canActivate: [authGuard], loadComponent: () => import('./components/Seguridad/dashboard/dashboard').then(m => m.Dashboard)},
    { path: 'genero', canActivate: [authGuard], loadComponent: () => import('./components/Seguridad/genero/genero').then(m => m.Genero) },
    { path: 'usuario', canActivate: [authGuard], loadComponent: () => import('./components/Seguridad/usuario/usuario').then(m => m.Usuario) },
    { path: 'unidad-medida', canActivate: [authGuard], loadComponent: () => import('./components/Catalogo/unidad-medida/unidad-medida').then(m => m.UnidadMedida) },
    { path: 'ajuste-inventario', canActivate: [authGuard], loadComponent: () => import('./components/Inventario/ajuste-inventario/ajuste-inventario').then(m => m.AjusteInventario) },
    { path: 'area-articulo', canActivate: [authGuard], loadComponent: () => import('./components/Inventario/area-articulo/area-articulo').then(m => m.AreaArticulo) },
    { path: 'articulo', canActivate: [authGuard], loadComponent: () => import('./components/Inventario/articulo/articulo').then(m => m.Articulo) },
    { path: 'movimiento-inventario', canActivate: [authGuard], loadComponent: () => import('./components/Inventario/movimiento-inventario/movimiento-inventario').then(m => m.MovimientoInventario) },
    { path: 'cotizacion', canActivate: [authGuard], loadComponent: () => import('./components/SucursalCotizacion/cotizacion/cotizacion').then(m => m.Cotizacion) },
    { path: 'sucursal', canActivate: [authGuard], loadComponent: () => import('./components/SucursalCotizacion/sucursal/sucursal').then(m => m.Sucursal) },
    { path: 'sucursal-articulo', canActivate: [authGuard], loadComponent: () => import('./components/SucursalCotizacion/sucursal-articulo/sucursal-articulo').then(m => m.SucursalArticulo) },
    { path: 'orden-compra', canActivate: [authGuard], loadComponent: () => import('./components/Suministro/orden-compra/orden-compra').then(m => m.OrdenCompra) },
    { path: 'proveedor', canActivate: [authGuard], loadComponent: () => import('./components/Suministro/proveedor/proveedor').then(m => m.Proveedor) },
    { path: 'cliente', canActivate: [authGuard], loadComponent: () => import('./components/Ventas/cliente/cliente').then(m => m.Cliente) },
    { path: 'pedidos', canActivate: [authGuard], loadComponent: () => import('./components/Ventas/pedidos/pedidos').then(m => m.Pedidos) },
    { path: 'ventas', canActivate: [authGuard], loadComponent: () => import('./components/Ventas/ventas/ventas').then(m => m.Ventas) }

];
