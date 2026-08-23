package com.femaco.main.Controller.Catalogo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.femaco.main.Entity.Catalogo.EstadoSucursal;
import com.femaco.main.Service.Catalogo.EstadoSucursalService;

@RestController
@RequestMapping("/estadoSucursal")
public class EstadoSucursalController {

    private final EstadoSucursalService estadoSucursalService;

    public EstadoSucursalController(EstadoSucursalService estadoSucursalService) {
        this.estadoSucursalService = estadoSucursalService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EstadoSucursal>> buscar() {
        return ResponseEntity.ok(estadoSucursalService.buscarTodos());
    }

    /*@PostMapping("/crear")
    public ResponseEntity<EstadoSucursal> crear(@RequestBody EstadoSucursal estadoSucursal) {
        EstadoSucursal creado = estadoSucursalService.crear(estadoSucursal);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/editar/{idEstadoSucursal}")
    public ResponseEntity<EstadoSucursal> editar(@PathVariable Long idEstadoSucursal,
                                                   @RequestBody EstadoSucursal estadoSucursal) {
        return estadoSucursalService.actualizar(idEstadoSucursal, estadoSucursal)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{idEstadoSucursal}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idEstadoSucursal) {
        boolean eliminado = estadoSucursalService.eliminar(idEstadoSucursal);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
        */
    
}
