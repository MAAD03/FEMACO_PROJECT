package com.femaco.main.Controller.Seguridad;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.femaco.main.DTOs.ModuloMenuDto;
import com.femaco.main.Service.Seguridad.ConjuntoMenuService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/conjuntoMenu")
@RequiredArgsConstructor
public class ConjuntoMenuController {

    private final ConjuntoMenuService conjuntoMenuService;

    @GetMapping("/usuario")          
    public ResponseEntity<List<ModuloMenuDto>> obtenerMenuUsuario() {
        List<ModuloMenuDto> menu = conjuntoMenuService.obtenerMenuDelUsuarioAutenticado();
        return ResponseEntity.ok(menu);
    }
}
