package com.femaco.main.Service.Catalogo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Catalogo.EstadoVenta;
import com.femaco.main.Repository.Catalogo.EstadoVentaRepository;


@Service
public class EstadoVentaService {
    
    private final EstadoVentaRepository estadoArticuloRepository;

    public EstadoVentaService(EstadoVentaRepository estadoArticuloRepository) {
        this.estadoArticuloRepository = estadoArticuloRepository;
    }

    public List<EstadoVenta> buscarTodos() {
        return estadoArticuloRepository.findAll();
    }

    @Transactional
    public EstadoVenta crear(EstadoVenta estadoVenta) {
        LocalDateTime ahora = LocalDateTime.now();
        estadoVenta.setIdEstadoVenta(null); 
        estadoVenta.setFechaCreacion(ahora);
        estadoVenta.setFechaModif(ahora);
        estadoVenta.setUsuarioModif(estadoVenta.getUsuarioCreacion());
        return estadoArticuloRepository.save(estadoVenta);
    }

    @Transactional
    public Optional<EstadoVenta> actualizar(Long idEstadoVenta, EstadoVenta datosNuevos) {
        return estadoArticuloRepository.findById(idEstadoVenta).map(existente -> {
            existente.setNombre(datosNuevos.getNombre());
            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            return estadoArticuloRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idEstadoVenta) {
        if (!estadoArticuloRepository.existsById(idEstadoVenta)) {
            return false;
        }
        estadoArticuloRepository.deleteById(idEstadoVenta);
        return true;
    }

}
