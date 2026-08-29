export interface EstadoCliente {
  idEstadoCliente: number;
  nombre: string;
  fechaCreacion: Date | string;
  usuarioCreacion: number;
  fechaModif: Date | string;
  usuarioModif: number;
}