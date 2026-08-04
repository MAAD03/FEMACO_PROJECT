package com.femaco.main.Controller.SucursalCotizacion;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.femaco.main.Entity.SucursalCotizacion.SucursalArticulo;
import com.femaco.main.Service.SucursalCotizacion.SucursalArticuloService;

@RestController
@RequestMapping("/sucursalArticulo")
public class SucursalArticuloController {

    private final SucursalArticuloService sucursalArticuloService;

    public SucursalArticuloController(SucursalArticuloService sucursalArticuloService) {
        this.sucursalArticuloService = sucursalArticuloService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<SucursalArticulo>> buscar() {
        return ResponseEntity.ok(sucursalArticuloService.buscarTodos());
    }

    @PostMapping("/crear")
    public ResponseEntity<SucursalArticulo> crear(@RequestBody SucursalArticulo sucursalArticulo) {
        SucursalArticulo creado = sucursalArticuloService.crear(sucursalArticulo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/editar/{idSucursalArticulo}")
    public ResponseEntity<SucursalArticulo> editar(@PathVariable Long idSucursalArticulo,
                                                   @RequestBody SucursalArticulo sucursalArticulo) {
        return sucursalArticuloService.actualizar(idSucursalArticulo, sucursalArticulo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{idSucursalArticulo}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idSucursalArticulo) {
        boolean eliminado = sucursalArticuloService.eliminar(idSucursalArticulo);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    
}
