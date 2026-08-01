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

import com.femaco.main.Entity.Catalogo.EstadoOrdenCompra;
import com.femaco.main.Service.Catalogo.EstadoOrdenCompraService;


@RestController
@RequestMapping("/estadoOrdenCompra")
public class EstadoOrdenCompraController {

    private final EstadoOrdenCompraService estadoOrdenCompraService;

    public EstadoOrdenCompraController(EstadoOrdenCompraService estadoOrdenCompraService) {
        this.estadoOrdenCompraService = estadoOrdenCompraService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EstadoOrdenCompra>> buscar() {
        return ResponseEntity.ok(estadoOrdenCompraService.buscarTodos());
    }

    @PostMapping("/crear")
    public ResponseEntity<EstadoOrdenCompra> crear(@RequestBody EstadoOrdenCompra estadoOrdenCompra) {
        EstadoOrdenCompra creado = estadoOrdenCompraService.crear(estadoOrdenCompra);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/editar/{idEstadoOrdenCompra}")
    public ResponseEntity<EstadoOrdenCompra> editar(@PathVariable Long idEstadoOrdenCompra,
                                                   @RequestBody EstadoOrdenCompra estadoOrdenCompra) {
        return estadoOrdenCompraService.actualizar(idEstadoOrdenCompra, estadoOrdenCompra)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{idEstadoOrdenCompra}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idEstadoOrdenCompra) {
        boolean eliminado = estadoOrdenCompraService.eliminar(idEstadoOrdenCompra);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    
}
