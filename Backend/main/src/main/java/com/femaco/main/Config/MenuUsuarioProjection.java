package com.femaco.main.Config;

public interface MenuUsuarioProjection {
    Integer getIdModulo();
    String getNombreModulo();
    Integer getOrdenModulo();
    Integer getIdMenu();
    String getNombreMenu();
    Integer getOrdenMenu();
    Integer getIdOpcion();
    String getNombreOpcion();
    Integer getOrdenOpcion();
    String getPagina();
    Byte getAlta();
    Byte getBaja();
    Byte getCambio();    
}
