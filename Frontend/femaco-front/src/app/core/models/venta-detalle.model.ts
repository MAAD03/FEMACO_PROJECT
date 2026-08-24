export interface VentaDetalle {
  idVentaDetalle?: number;
  cantidad: number;
  precioUnitario: number;
  descuentoAplicado?: number;
  subtotal: number;
  idVenta?: number;
  idArticulo?: number;
  usuarioCreacion?: number;
  usuarioModif?: number;
  fechaCreacion?: string | Date;
  fechaModif?: string | Date;
}