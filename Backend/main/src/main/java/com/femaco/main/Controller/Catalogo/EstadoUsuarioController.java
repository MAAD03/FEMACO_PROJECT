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

import com.femaco.main.Entity.Catalogo.EstadoUsuario;
import com.femaco.main.Service.Catalogo.EstadoUsuarioService;

@RestController
@RequestMapping("/estadoUsuario")
public class EstadoUsuarioController {
    
     private final EstadoUsuarioService estadoUsuarioService;

    public EstadoUsuarioController(EstadoUsuarioService estadoUsuarioService) {
        this.estadoUsuarioService = estadoUsuarioService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EstadoUsuario>> buscar() {
        return ResponseEntity.ok(estadoUsuarioService.buscarTodos());
    }

    @PostMapping("/crear")
    public ResponseEntity<EstadoUsuario> crear(@RequestBody EstadoUsuario estadoUsuario) {
        EstadoUsuario creado = estadoUsuarioService.crear(estadoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/editar/{idEstadoUsuario}")
    public ResponseEntity<EstadoUsuario> editar(@PathVariable Long idEstadoUsuario,
                                                   @RequestBody EstadoUsuario estadoUsuario) {
        return estadoUsuarioService.actualizar(idEstadoUsuario, estadoUsuario)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{idEstadoUsuario}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idEstadoUsuario) {
        boolean eliminado = estadoUsuarioService.eliminar(idEstadoUsuario);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    
}
