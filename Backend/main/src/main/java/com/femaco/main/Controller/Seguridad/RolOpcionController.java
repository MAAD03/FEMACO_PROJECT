package com.femaco.main.Controller.Seguridad;

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

import com.femaco.main.Entity.Seguridad.RolOpcion;
import com.femaco.main.Service.Seguridad.RolOpcionService;

@RestController
@RequestMapping("/rolOpcion")
public class RolOpcionController {
    
    private final RolOpcionService rolOpcionService;

    public RolOpcionController(RolOpcionService rolOpcionService) {
        this.rolOpcionService = rolOpcionService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<RolOpcion>> buscar() {
        return ResponseEntity.ok(rolOpcionService.buscarTodos());
    }

    @PostMapping("/crear")
    public ResponseEntity<RolOpcion> crear(@RequestBody RolOpcion rolOpcion) {
        RolOpcion creado = rolOpcionService.crear(rolOpcion);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/editar/{idRolOpcion}")
    public ResponseEntity<RolOpcion> editar(@PathVariable Long idRolOpcion,
                                             @RequestBody RolOpcion rolOpcion) {
        return rolOpcionService.actualizar(idRolOpcion, rolOpcion)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{idRolOpcion}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idRolOpcion) {
        boolean eliminado = rolOpcionService.eliminar(idRolOpcion);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
