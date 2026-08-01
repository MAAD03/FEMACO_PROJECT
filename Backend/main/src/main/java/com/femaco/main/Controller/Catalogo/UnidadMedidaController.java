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

import com.femaco.main.Entity.Catalogo.UnidadMedida;
import com.femaco.main.Service.Catalogo.UnidadMedidaService;

@RestController
@RequestMapping("/unidadMedida")
public class UnidadMedidaController {

    private final UnidadMedidaService unidadMedidaService;

    public UnidadMedidaController(UnidadMedidaService unidadMedidaService) {
        this.unidadMedidaService = unidadMedidaService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<UnidadMedida>> buscar() {
        return ResponseEntity.ok(unidadMedidaService.buscarTodos());
    }

    @PostMapping("/crear")
    public ResponseEntity<UnidadMedida> crear(@RequestBody UnidadMedida unidadMedida) {
        UnidadMedida creado = unidadMedidaService.crear(unidadMedida);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/editar/{idUnidadMedida}")
    public ResponseEntity<UnidadMedida> editar(@PathVariable Long idUnidadMedida,
                                                   @RequestBody UnidadMedida unidadMedida) {
        return unidadMedidaService.actualizar(idUnidadMedida, unidadMedida)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{idUnidadMedida}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idUnidadMedida) {
        boolean eliminado = unidadMedidaService.eliminar(idUnidadMedida);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    
}
