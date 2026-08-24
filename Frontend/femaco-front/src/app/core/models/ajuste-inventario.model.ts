export interface AjusteInventario {
  idAjusteInventario?: number;
  cantidadAjuste: number;
  motivo?: string;
  idArticulo?: number;
  idUsuario?: number;
  usuarioCreacion?: number;
  usuarioModif?: number;
  fechaCreacion?: string | Date;
  fechaModif?: string | Date;
}