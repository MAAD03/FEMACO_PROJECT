package com.femaco.main.Controller.Inventario;

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

import com.femaco.main.Entity.Inventario.AjusteInventario;
import com.femaco.main.Service.Inventario.AjusteInventarioService;



@RestController
@RequestMapping("/ajusteInventario")
public class AjusteInventarioController {

     private final AjusteInventarioService ajusteInventarioService;

    public AjusteInventarioController(AjusteInventarioService ajusteInventarioService) {
        this.ajusteInventarioService = ajusteInventarioService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<AjusteInventario>> buscar() {
        return ResponseEntity.ok(ajusteInventarioService.buscarTodos());
    }

    @PostMapping("/crear")
    public ResponseEntity<AjusteInventario> crear(@RequestBody AjusteInventario ajusteInventario) {
        AjusteInventario creado = ajusteInventarioService.crear(ajusteInventario);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/editar/{idAjusteInventario}")
    public ResponseEntity<AjusteInventario> editar(@PathVariable Long idAjusteInventario,
                                                   @RequestBody AjusteInventario ajusteInventario) {
        return ajusteInventarioService.actualizar(idAjusteInventario, ajusteInventario)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{idAjusteInventario}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idAjusteInventario) {
        boolean eliminado = ajusteInventarioService.eliminar(idAjusteInventario);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    
}
