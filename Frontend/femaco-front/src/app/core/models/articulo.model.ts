export interface Articulo {
  idArticulo?: number;
  codigo?: string;
  nombre: string;
  descripcion?: string;
  stockActual?: number;
  stockMinimo?: number;
  precioCompraUltimoProveedor?: number;
  margenGanancia?: number;
  cantidadMinimaDescuento?: number;
  descuentoMayorista?: number;
  idAreaArticulo?: number;
  idUnidadMedida?: number;
  idEstadoArticulo?: number;
  usuarioCreacion?: number;
  usuarioModif?: number;
  fechaCreacion?: string | Date;
  fechaModif?: string | Date;
}