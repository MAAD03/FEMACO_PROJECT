package com.femaco.main.DTOs;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {
    private Integer idMenu;
    private String nombre;
    private Integer ordenMenu;
    private List<OpcionDto> opciones = new ArrayList<>();
}
