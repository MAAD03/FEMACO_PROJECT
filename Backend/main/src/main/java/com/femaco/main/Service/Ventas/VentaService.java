package com.femaco.main.Service.Ventas;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Ventas.Venta;
import com.femaco.main.Repository.Ventas.VentaRepository;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<Venta> buscarTodos() {
        return ventaRepository.findAll();
    }

    @Transactional
    public Venta crear(Venta venta) {
        LocalDateTime ahora = LocalDateTime.now();
        venta.setIdVenta(null);
        venta.setFechaCreacion(ahora);
        venta.setFechaModif(ahora);
        venta.setUsuarioModif(venta.getUsuarioCreacion());
        return ventaRepository.save(venta);
    }

    @Transactional
    public Optional<Venta> actualizar(Long idVenta, Venta datosNuevos) {
        return ventaRepository.findById(idVenta).map(existente -> {
            existente.setFecha(datosNuevos.getFecha());
            existente.setSubtotal(datosNuevos.getSubtotal());
            existente.setTotal(datosNuevos.getTotal());
            existente.setEsPedido(datosNuevos.getEsPedido());
            existente.setNumeroFactura(datosNuevos.getNumeroFactura());
            existente.setIdEstadoVenta(datosNuevos.getIdEstadoVenta());
            existente.setIdCliente(datosNuevos.getIdCliente());
            existente.setIdUsuario(datosNuevos.getIdUsuario());

            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            return ventaRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idVenta) {
        if (!ventaRepository.existsById(idVenta)) {
            return false;
        }
        ventaRepository.deleteById(idVenta);
        return true;
    }
    
}
