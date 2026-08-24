import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../config/api.config';
import { Sucursal } from '../models/sucursal.model';

@Injectable({
  providedIn: 'root'
})

export class SucursalService {}