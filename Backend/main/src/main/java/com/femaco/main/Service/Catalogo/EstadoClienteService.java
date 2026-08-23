package com.femaco.main.Service.Catalogo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Catalogo.EstadoCliente;
import com.femaco.main.Repository.Catalogo.EstadoClienteRepository;


@Service
public class EstadoClienteService {

    private final EstadoClienteRepository estadoClienteRepository;

    public EstadoClienteService(EstadoClienteRepository estadoClienteRepository) {
        this.estadoClienteRepository = estadoClienteRepository;
    }

    @Cacheable("estadoCliente")
    public List<EstadoCliente> buscarTodos() {
        return estadoClienteRepository.findAll();
    }

    @Transactional
    public EstadoCliente crear(EstadoCliente estadoCliente) {
        LocalDateTime ahora = LocalDateTime.now();
        estadoCliente.setIdEstadoCliente(null);
        estadoCliente.setFechaCreacion(ahora);
        estadoCliente.setFechaModif(ahora);
        estadoCliente.setUsuarioModif(estadoCliente.getUsuarioCreacion());
        return estadoClienteRepository.save(estadoCliente);
    }

    @Transactional
    public Optional<EstadoCliente> actualizar(Long idEstadoCliente, EstadoCliente datosNuevos) {
        return estadoClienteRepository.findById(idEstadoCliente).map(existente -> {
            existente.setNombre(datosNuevos.getNombre());
            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            return estadoClienteRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idEstadoCliente) {
        if (!estadoClienteRepository.existsById(idEstadoCliente)) {
            return false;
        }
        estadoClienteRepository.deleteById(idEstadoCliente);
        return true;
    }
    
}
