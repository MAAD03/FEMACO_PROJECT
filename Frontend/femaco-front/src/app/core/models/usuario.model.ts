export interface Usuario {
  idUsuario?: number;
  nombre: string;
  apellido: string;
  correoElectronico: string;
  password?: string;
  requiereCambioPassword?: boolean;
  pregunta?: string;
  respuesta?: string;
  idGenero?: number;
  idEstadoUsuario?: number;
  idSucursal?: number;
  idRol?: number;
  usuarioCreacion?: number;
  usuarioModif?: number;
  fechaCreacion?: string | Date;
  fechaModif?: string | Date;
}

export interface UsuarioResumen {
  idUsuario: number;
  nombre: string;
  apellido: string;
  correoElectronico: string;
}