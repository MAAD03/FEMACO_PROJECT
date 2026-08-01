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

import com.femaco.main.Entity.Catalogo.EstadoPedido;
import com.femaco.main.Service.Catalogo.EstadoPedidoService;



@RestController
@RequestMapping("/estadoPedido")
public class EstadoPedidoController {
    
     private final EstadoPedidoService estadoPedidoService;

    public EstadoPedidoController(EstadoPedidoService estadoPedidoService) {
        this.estadoPedidoService = estadoPedidoService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EstadoPedido>> buscar() {
        return ResponseEntity.ok(estadoPedidoService.buscarTodos());
    }

    @PostMapping("/crear")
    public ResponseEntity<EstadoPedido> crear(@RequestBody EstadoPedido estadoPedido) {
        EstadoPedido creado = estadoPedidoService.crear(estadoPedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/editar/{idEstadoPedido}")
    public ResponseEntity<EstadoPedido> editar(@PathVariable Long idEstadoPedido,
                                                   @RequestBody EstadoPedido estadoPedido) {
        return estadoPedidoService.actualizar(idEstadoPedido, estadoPedido)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{idEstadoPedido}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idEstadoPedido) {
        boolean eliminado = estadoPedidoService.eliminar(idEstadoPedido);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
