import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../config/api.config';
import { SucursalArticulo } from '../models/sucursal-articulo.model';

@Injectable({
  providedIn: 'root'
})

export class SucursalArticuloService {}