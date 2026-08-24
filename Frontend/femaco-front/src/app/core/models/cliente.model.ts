export interface Cliente {
  idCliente?: number;
  nit: string;
  nombre: string;
  telefono?: string;
  correo?: string;
  idEstadoCliente?: number;
  usuarioCreacion?: number;
  usuarioModif?: number;
  fechaCreacion?: string | Date;
  fechaModif?: string | Date;
}