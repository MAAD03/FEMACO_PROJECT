package com.femaco.main.DTOs;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuloMenuDto {
    private Integer idModulo;
    private String nombre;
    private Integer ordenMenu;
    private List<MenuDto> menus = new ArrayList<>();
}
