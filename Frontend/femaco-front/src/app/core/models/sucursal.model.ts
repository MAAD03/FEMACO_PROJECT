export interface Sucursal {
  idSucursal?: number;
  nombre: string;
  direccion?: string;
  telefono?: string;
  idEstadoSucursal?: number;
  usuarioCreacion?: number;
  usuarioModif?: number;
  fechaCreacion?: string | Date;
  fechaModif?: string | Date;
}