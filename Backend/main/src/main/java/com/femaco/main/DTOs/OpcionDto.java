package com.femaco.main.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpcionDto {
    private Integer idOpcion;
    private String nombre;
    private Integer ordenMenu;
    private String pagina;
    private Boolean alta;
    private Boolean baja;
    private Boolean cambio;
}
