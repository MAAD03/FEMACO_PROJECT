export interface UnidadMedida {
  idUnidadMedida?: number;
  nombre: string;
  abreviatura: string;
  usuarioCreacion?: number;
  usuarioModif?: number;
  fechaCreacion?: string | Date;
  fechaModif?: string | Date;
}