export interface Cotizacion {
  idCotizacion?: number;
  nombre?: string;
  nit?: string;
  subtotal?: number;
  descuentoTotal?: number;
  total?: number;
  idUsuario?: number;
  usuarioCreacion?: number;
  usuarioModif?: number;
  fechaCreacion?: string | Date;
  fechaModif?: string | Date;
}