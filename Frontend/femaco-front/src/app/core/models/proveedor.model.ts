export interface Proveedor {
  idProveedor?: number;
  nombre: string;
  nit?: string;
  telefono?: string;
  direccion?: string;
  nombreContacto?: string;
  idEstadoProveedor?: number;
  usuarioCreacion?: number;
  usuarioModif?: number;
  fechaCreacion?: string | Date;
  fechaModif?: string | Date;
}