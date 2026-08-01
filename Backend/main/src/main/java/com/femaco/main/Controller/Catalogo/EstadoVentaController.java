package com.femaco.main.Controller.Catalogo;

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

import com.femaco.main.Entity.Catalogo.EstadoVenta;
import com.femaco.main.Service.Catalogo.EstadoVentaService;

@RestController
@RequestMapping("/estadoVenta")
public class EstadoVentaController {
    private final EstadoVentaService estadoVentaService;

    public EstadoVentaController(EstadoVentaService estadoVentaService) {
        this.estadoVentaService = estadoVentaService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EstadoVenta>> buscar() {
        return ResponseEntity.ok(estadoVentaService.buscarTodos());
    }

    @PostMapping("/crear")
    public ResponseEntity<EstadoVenta> crear(@RequestBody EstadoVenta estadoVenta) {
        EstadoVenta creado = estadoVentaService.crear(estadoVenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/editar/{idEstadoVenta}")
    public ResponseEntity<EstadoVenta> editar(@PathVariable Long idEstadoVenta,
                                                @RequestBody EstadoVenta estadoVenta) {
        return estadoVentaService.actualizar(idEstadoVenta, estadoVenta)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{idEstadoVenta}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idEstadoVenta) {
        boolean eliminado = estadoVentaService.eliminar(idEstadoVenta);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
