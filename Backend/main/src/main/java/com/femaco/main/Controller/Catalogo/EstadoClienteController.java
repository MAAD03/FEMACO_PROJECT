package com.femaco.main.Controller.Catalogo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.femaco.main.Entity.Catalogo.EstadoCliente;
import com.femaco.main.Service.Catalogo.EstadoClienteService;

@RestController
@RequestMapping("/estadoCliente")
public class EstadoClienteController {

        private final EstadoClienteService estadoClienteService;

        public EstadoClienteController(EstadoClienteService estadoClienteService) {
        this.estadoClienteService = estadoClienteService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EstadoCliente>> buscar() {
        return ResponseEntity.ok(estadoClienteService.buscarTodos());
    }

    /* 
    @PostMapping("/crear")
    public ResponseEntity<EstadoCliente> crear(@RequestBody EstadoCliente estadoCliente) {
        EstadoCliente creado = estadoClienteService.crear(estadoCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/editar/{idEstadoCliente}")
    public ResponseEntity<EstadoCliente> editar(@PathVariable Long idEstadoCliente,
                                                   @RequestBody EstadoCliente estadoCliente) {
        return estadoClienteService.actualizar(idEstadoCliente, estadoCliente)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{idEstadoCliente}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idEstadoCliente) {
        boolean eliminado = estadoClienteService.eliminar(idEstadoCliente);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    */
}
