package com.femaco.main.Controller.Ventas;

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

import com.femaco.main.Entity.Ventas.Venta;
import com.femaco.main.Service.Ventas.VentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Venta>> buscar() {
        return ResponseEntity.ok(ventaService.buscarTodos());
    }

    @PostMapping("/crear")
    public ResponseEntity<Venta> crear(@RequestBody Venta venta) {
        Venta creada = ventaService.crear(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/editar/{idVenta}")
    public ResponseEntity<Venta> editar(@PathVariable Long idVenta,
                                         @RequestBody Venta venta) {
        return ventaService.actualizar(idVenta, venta)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{idVenta}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idVenta) {
        boolean eliminado = ventaService.eliminar(idVenta);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    
}
