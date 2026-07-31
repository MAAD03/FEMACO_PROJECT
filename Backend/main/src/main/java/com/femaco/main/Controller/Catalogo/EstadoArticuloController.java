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

import com.femaco.main.Entity.Catalogo.EstadoArticulo;
import com.femaco.main.Service.Catalogo.EstadoArticuloService;

@RestController
@RequestMapping("/estadoArticulo")
public class EstadoArticuloController {

    private final EstadoArticuloService estadoArticuloService;

    public EstadoArticuloController(EstadoArticuloService estadoArticuloService) {
        this.estadoArticuloService = estadoArticuloService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EstadoArticulo>> buscar() {
        return ResponseEntity.ok(estadoArticuloService.buscarTodos());
    }

    @PostMapping("/crear")
    public ResponseEntity<EstadoArticulo> crear(@RequestBody EstadoArticulo estadoArticulo) {
        EstadoArticulo creado = estadoArticuloService.crear(estadoArticulo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/editar/{idEstadoArticulo}")
    public ResponseEntity<EstadoArticulo> editar(@PathVariable Long idEstadoArticulo,
                                                   @RequestBody EstadoArticulo estadoArticulo) {
        return estadoArticuloService.actualizar(idEstadoArticulo, estadoArticulo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{idEstadoArticulo}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idEstadoArticulo) {
        boolean eliminado = estadoArticuloService.eliminar(idEstadoArticulo);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}