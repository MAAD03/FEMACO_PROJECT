export interface OpcionMenu {
  idOpcion: number;
  nombre: string;
  ordenMenu: number;
  pagina: string;
  alta: boolean;
  baja: boolean;
  cambio: boolean;
}

export interface MenuItem {
  idMenu: number;
  nombre: string;
  ordenMenu: number;
  opciones: OpcionMenu[];
}

export interface ModuloMenu {
  idModulo: number;
  nombre: string;
  ordenMenu: number;
  menus: MenuItem[];
}