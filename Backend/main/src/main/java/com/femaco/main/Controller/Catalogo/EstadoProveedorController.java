package com.femaco.main.Controller.Catalogo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.femaco.main.Entity.Catalogo.EstadoProveedor;
import com.femaco.main.Service.Catalogo.EstadoProveedorService;

@RestController
@RequestMapping("/estadoProveedor")
public class EstadoProveedorController {

    private final EstadoProveedorService estadoProveedorService;

    public EstadoProveedorController(EstadoProveedorService estadoProveedorService) {
        this.estadoProveedorService = estadoProveedorService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EstadoProveedor>> buscar() {
        return ResponseEntity.ok(estadoProveedorService.buscarTodos());
    }

    /* 
    @PostMapping("/crear")
    public ResponseEntity<EstadoProveedor> crear(@RequestBody EstadoProveedor estadoProveedor) {
        EstadoProveedor creado = estadoProveedorService.crear(estadoProveedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/editar/{idEstadoProveedor}")
    public ResponseEntity<EstadoProveedor> editar(@PathVariable Long idEstadoProveedor,
                                                   @RequestBody EstadoProveedor estadoProveedor) {
        return estadoProveedorService.actualizar(idEstadoProveedor, estadoProveedor)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{idEstadoProveedor}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idEstadoProveedor) {
        boolean eliminado = estadoProveedorService.eliminar(idEstadoProveedor);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    */
}
