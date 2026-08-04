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

import com.femaco.main.Entity.Seguridad.Genero;
import com.femaco.main.Service.Seguridad.GeneroService;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    private final GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Genero>> buscar() {
        return ResponseEntity.ok(generoService.buscarTodos());
    }

    @PostMapping("/crear")
    public ResponseEntity<Genero> crear(@RequestBody Genero genero) {
        Genero creado = generoService.crear(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/editar/{idGenero}")
    public ResponseEntity<Genero> editar(@PathVariable Long idGenero,
                                                   @RequestBody Genero genero) {
        return generoService.actualizar(idGenero, genero)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{idGenero}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idGenero) {
        boolean eliminado = generoService.eliminar(idGenero);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    
}
