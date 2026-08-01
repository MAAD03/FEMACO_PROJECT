package com.femaco.main.Service.Catalogo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Catalogo.EstadoArticulo;
import com.femaco.main.Repository.Catalogo.EstadoArticuloRepository;

@Service
public class EstadoArticuloService {

    private final EstadoArticuloRepository estadoArticuloRepository;

    public EstadoArticuloService(EstadoArticuloRepository estadoArticuloRepository) {
        this.estadoArticuloRepository = estadoArticuloRepository;
    }

    public List<EstadoArticulo> buscarTodos() {
        return estadoArticuloRepository.findAll();
    }

    @Transactional
    public EstadoArticulo crear(EstadoArticulo estadoArticulo) {
        LocalDateTime ahora = LocalDateTime.now();
        estadoArticulo.setIdEstadoArticulo(null); 
        estadoArticulo.setFechaCreacion(ahora);
        estadoArticulo.setFechaModif(ahora);
        estadoArticulo.setUsuarioModif(estadoArticulo.getUsuarioCreacion());
        return estadoArticuloRepository.save(estadoArticulo);
    }

    @Transactional
    public Optional<EstadoArticulo> actualizar(Long idEstadoArticulo, EstadoArticulo datosNuevos) {
        return estadoArticuloRepository.findById(idEstadoArticulo).map(existente -> {
            existente.setNombre(datosNuevos.getNombre());
            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            // fechaCreacion y usuarioCreacion no se tocan
            return estadoArticuloRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idEstadoArticulo) {
        if (!estadoArticuloRepository.existsById(idEstadoArticulo)) {
            return false;
        }
        estadoArticuloRepository.deleteById(idEstadoArticulo);
        return true;
    }
}