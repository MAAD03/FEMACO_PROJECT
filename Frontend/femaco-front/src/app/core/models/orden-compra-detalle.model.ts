export interface OrdenCompraDetalle {
  idOrdenCompraDetalle?: number;
  cantidad: number;
  precioUnitario: number;
  total: number;
  idOrdenCompra?: number;
  idArticulo?: number;
  usuarioCreacion?: number;
  usuarioModif?: number;
  fechaCreacion?: string | Date;
  fechaModif?: string | Date;
}