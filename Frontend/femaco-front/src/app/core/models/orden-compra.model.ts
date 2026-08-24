export interface OrdenCompra {
  idOrdenCompra?: number;
  total?: number;
  notas?: string;
  idProveedor?: number;
  idEstadoOrdenCompra?: number;
  idUsuario?: number;
  usuarioCreacion?: number;
  usuarioModif?: number;
  fechaCreacion?: string | Date;
  fechaModif?: string | Date;
}