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

import com.femaco.main.Entity.SucursalCotizacion.Cotizacion;
import com.femaco.main.Service.SucursalCotizacion.CotizacionService;

@RestController
@RequestMapping("/cotizacion")
public class CotizacionController {

    private final CotizacionService cotizacionService;

    public CotizacionController(CotizacionService cotizacionService) {
        this.cotizacionService = cotizacionService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Cotizacion>> buscar() {
        return ResponseEntity.ok(cotizacionService.buscarTodos());
    }

    @PostMapping("/crear")
    public ResponseEntity<Cotizacion> crear(@RequestBody Cotizacion cotizacion) {
        Cotizacion creado = cotizacionService.crear(cotizacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/editar/{idCotizacion}")
    public ResponseEntity<Cotizacion> editar(@PathVariable Long idCotizacion,
                                              @RequestBody Cotizacion cotizacion) {
        return cotizacionService.actualizar(idCotizacion, cotizacion)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{idCotizacion}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idCotizacion) {
        boolean eliminado = cotizacionService.eliminar(idCotizacion);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    
}
