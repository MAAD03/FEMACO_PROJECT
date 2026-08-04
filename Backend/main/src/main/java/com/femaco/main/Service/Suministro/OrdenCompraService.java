package com.femaco.main.Service.Suministro;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Suministro.OrdenCompra;
import com.femaco.main.Repository.Suministro.OrdenCompraRepository;

@Service
public class OrdenCompraService {

    private final OrdenCompraRepository ordenCompraRepository;

    public OrdenCompraService(OrdenCompraRepository ordenCompraRepository) {
        this.ordenCompraRepository = ordenCompraRepository;
    }

    public List<OrdenCompra> buscarTodos() {
        return ordenCompraRepository.findAll();
    }

    @Transactional
    public OrdenCompra crear(OrdenCompra ordenCompra) {
        LocalDateTime ahora = LocalDateTime.now();
        ordenCompra.setIdOrdenCompra(null);
        ordenCompra.setFechaCreacion(ahora);
        ordenCompra.setFechaModif(ahora);
        ordenCompra.setUsuarioModif(ordenCompra.getUsuarioCreacion());
        return ordenCompraRepository.save(ordenCompra);
    }

    @Transactional
    public Optional<OrdenCompra> actualizar(Long idOrdenCompra, OrdenCompra datosNuevos) {
        return ordenCompraRepository.findById(idOrdenCompra).map(existente -> {
            existente.setTotal(datosNuevos.getTotal());
            existente.setNotas(datosNuevos.getNotas());
            existente.setIdProveedor(datosNuevos.getIdProveedor());
            existente.setIdEstadoOrdenCompra(datosNuevos.getIdEstadoOrdenCompra());
            existente.setIdUsuario(datosNuevos.getIdUsuario());

            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            return ordenCompraRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idOrdenCompra) {
        if (!ordenCompraRepository.existsById(idOrdenCompra)) {
            return false;
        }
        ordenCompraRepository.deleteById(idOrdenCompra);
        return true;
    }
    
}
