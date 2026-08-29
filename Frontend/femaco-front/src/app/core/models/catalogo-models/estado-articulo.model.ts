export interface EstadoArticulo {
  idEstadoArticulo: number;
  nombre: string;
  fechaCreacion: Date | string;
  usuarioCreacion: number;
  fechaModif: Date | string;
  usuarioModif: number;
}