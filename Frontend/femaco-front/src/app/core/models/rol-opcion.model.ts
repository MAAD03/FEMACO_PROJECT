export interface RolOpcion {
  idRolOpcion?: number;
  idRol?: number;
  idOpcion?: number;
  alta?: boolean;
  baja?: boolean;
  cambio?: boolean;
  usuarioCreacion?: number;
  usuarioModif?: number;
  fechaCreacion?: string | Date;
  fechaModif?: string | Date;
}