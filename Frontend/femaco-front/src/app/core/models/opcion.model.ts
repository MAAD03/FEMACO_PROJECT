export interface Opcion {
  idOpcion?: number;
  nombre: string;
  ordenMenu?: number;
  pagina?: string;
  idMenu?: number;
  usuarioCreacion?: number;
  usuarioModif?: number;
  fechaCreacion?: string | Date;
  fechaModif?: string | Date;
}