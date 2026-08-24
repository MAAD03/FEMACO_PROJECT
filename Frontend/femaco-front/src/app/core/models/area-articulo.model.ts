export interface AreaArticulo {
  idAreaArticulo?: number;
  nombre: string;
  descripcion?: string;
  usuarioCreacion?: number;
  usuarioModif?: number;
  fechaCreacion?: string | Date;
  fechaModif?: string | Date;
}