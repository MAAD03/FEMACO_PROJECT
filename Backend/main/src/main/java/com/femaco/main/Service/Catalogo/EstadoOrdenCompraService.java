package com.femaco.main.Service.Catalogo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Catalogo.EstadoOrdenCompra;
import com.femaco.main.Repository.Catalogo.EstadoOrdenCompraRepository;


@Service
public class EstadoOrdenCompraService {

    private final EstadoOrdenCompraRepository estadoOrdenCompraRepository;

    public EstadoOrdenCompraService(EstadoOrdenCompraRepository estadoOrdenCompraRepository) {
        this.estadoOrdenCompraRepository = estadoOrdenCompraRepository;
    }

    @Cacheable("estadoOrdenCompra")
    public List<EstadoOrdenCompra> buscarTodos() {
        return estadoOrdenCompraRepository.findAll();
    }

    @Transactional
    public EstadoOrdenCompra crear(EstadoOrdenCompra estadoOrdenCompra) {
        LocalDateTime ahora = LocalDateTime.now();
        estadoOrdenCompra.setIdEstadoOrdenCompra(null);
        estadoOrdenCompra.setFechaCreacion(ahora);
        estadoOrdenCompra.setFechaModif(ahora);
        estadoOrdenCompra.setUsuarioModif(estadoOrdenCompra.getUsuarioCreacion());
        return estadoOrdenCompraRepository.save(estadoOrdenCompra);
    }

    @Transactional
    public Optional<EstadoOrdenCompra> actualizar(Long idEstadoOrdenCompra, EstadoOrdenCompra datosNuevos) {
        return estadoOrdenCompraRepository.findById(idEstadoOrdenCompra).map(existente -> {
            existente.setNombre(datosNuevos.getNombre());
            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            return estadoOrdenCompraRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idEstadoOrdenCompra) {
        if (!estadoOrdenCompraRepository.existsById(idEstadoOrdenCompra)) {
            return false;
        }
        estadoOrdenCompraRepository.deleteById(idEstadoOrdenCompra);
        return true;
    }
    
}
