package com.femaco.main.Service.Suministro;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Suministro.OrdenCompraDetalle;
import com.femaco.main.Repository.Suministro.OrdenCompraDetalleRepository;

@Service
public class OrdenCompraDetalleService {

    private final OrdenCompraDetalleRepository ordenCompraDetalleRepository;

    public OrdenCompraDetalleService(OrdenCompraDetalleRepository ordenCompraDetalleRepository) {
        this.ordenCompraDetalleRepository = ordenCompraDetalleRepository;
    }

    public List<OrdenCompraDetalle> buscarTodos() {
        return ordenCompraDetalleRepository.findAll();
    }

    @Transactional
    public OrdenCompraDetalle crear(OrdenCompraDetalle ordenCompraDetalle) {
        LocalDateTime ahora = LocalDateTime.now();
        ordenCompraDetalle.setIdOrdenCompraDetalle(null);
        ordenCompraDetalle.setFechaCreacion(ahora);
        ordenCompraDetalle.setFechaModif(ahora);
        ordenCompraDetalle.setUsuarioModif(ordenCompraDetalle.getUsuarioCreacion());
        return ordenCompraDetalleRepository.save(ordenCompraDetalle);
    }

    @Transactional
    public Optional<OrdenCompraDetalle> actualizar(Long idOrdenCompraDetalle, OrdenCompraDetalle datosNuevos) {
        return ordenCompraDetalleRepository.findById(idOrdenCompraDetalle).map(existente -> {
            existente.setCantidad(datosNuevos.getCantidad());
            existente.setPrecioUnitario(datosNuevos.getPrecioUnitario());
            existente.setTotal(datosNuevos.getTotal());
            existente.setIdOrdenCompra(datosNuevos.getIdOrdenCompra());
            existente.setIdEstadoOrdenCompra(datosNuevos.getIdEstadoOrdenCompra());
            existente.setIdArticulo(datosNuevos.getIdArticulo());

            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            return ordenCompraDetalleRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idOrdenCompraDetalle) {
        if (!ordenCompraDetalleRepository.existsById(idOrdenCompraDetalle)) {
            return false;
        }
        ordenCompraDetalleRepository.deleteById(idOrdenCompraDetalle);
        return true;
    }
    
}
