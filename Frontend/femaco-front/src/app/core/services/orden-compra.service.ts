import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../config/api.config';
import { OrdenCompra } from '../models/orden-compra.model';

@Injectable({
  providedIn: 'root'
})

export class OrdenCompraService {}