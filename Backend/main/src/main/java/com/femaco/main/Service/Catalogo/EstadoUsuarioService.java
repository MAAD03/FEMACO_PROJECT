package com.femaco.main.Service.Catalogo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Catalogo.EstadoUsuario;
import com.femaco.main.Repository.Catalogo.EstadoUsuarioRepository;


@Service
public class EstadoUsuarioService {

     private final EstadoUsuarioRepository estadoUsuarioRepository;

    public EstadoUsuarioService(EstadoUsuarioRepository estadoUsuarioRepository) {
        this.estadoUsuarioRepository = estadoUsuarioRepository;
    }

    @Cacheable("estadoUsuario")
    public List<EstadoUsuario> buscarTodos() {
        return estadoUsuarioRepository.findAll();
    }

    @Transactional
    public EstadoUsuario crear(EstadoUsuario estadoUsuario) {
        LocalDateTime ahora = LocalDateTime.now();
        estadoUsuario.setIdEstadoUsuario(null);
        estadoUsuario.setFechaCreacion(ahora);
        estadoUsuario.setFechaModif(ahora);
        estadoUsuario.setUsuarioModif(estadoUsuario.getUsuarioCreacion());
        return estadoUsuarioRepository.save(estadoUsuario);
    }

    @Transactional
    public Optional<EstadoUsuario> actualizar(Long idEstadoUsuario, EstadoUsuario datosNuevos) {
        return estadoUsuarioRepository.findById(idEstadoUsuario).map(existente -> {
            existente.setNombre(datosNuevos.getNombre());
            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            return estadoUsuarioRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idEstadoUsuario) {
        if (!estadoUsuarioRepository.existsById(idEstadoUsuario)) {
            return false;
        }
        estadoUsuarioRepository.deleteById(idEstadoUsuario);
        return true;
    }
    
}
