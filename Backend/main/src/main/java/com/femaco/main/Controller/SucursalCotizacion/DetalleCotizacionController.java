package com.femaco.main.Controller.SucursalCotizacion;

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

import com.femaco.main.Entity.SucursalCotizacion.DetalleCotizacion;
import com.femaco.main.Service.SucursalCotizacion.DetalleCotizacionService;

@RestController
@RequestMapping("/detalleCotizacion")
public class DetalleCotizacionController {

    private final DetalleCotizacionService detalleCotizacionService;

    public DetalleCotizacionController(DetalleCotizacionService detalleCotizacionService) {
        this.detalleCotizacionService = detalleCotizacionService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<DetalleCotizacion>> buscar() {
        return ResponseEntity.ok(detalleCotizacionService.buscarTodos());
    }

    @PostMapping("/crear")
    public ResponseEntity<DetalleCotizacion> crear(@RequestBody DetalleCotizacion detalleCotizacion) {
        DetalleCotizacion creado = detalleCotizacionService.crear(detalleCotizacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/editar/{idDetalleCotizacion}")
    public ResponseEntity<DetalleCotizacion> editar(@PathVariable Long idDetalleCotizacion,
                                                   @RequestBody DetalleCotizacion detalleCotizacion) {
        return detalleCotizacionService.actualizar(idDetalleCotizacion, detalleCotizacion)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{idDetalleCotizacion}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idDetalleCotizacion) {
        boolean eliminado = detalleCotizacionService.eliminar(idDetalleCotizacion);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    
}
