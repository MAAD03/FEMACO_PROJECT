package com.femaco.main.Service.Seguridad;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Seguridad.Opcion;
import com.femaco.main.Repository.Seguridad.OpcionRepository;

@Service
public class OpcionService {
    
    private final OpcionRepository opcionRepository;

    public OpcionService(OpcionRepository opcionRepository) {
        this.opcionRepository = opcionRepository;
    }

    public List<Opcion> buscarTodos() {
        return opcionRepository.findAll();
    }

    @Transactional
    public Opcion crear(Opcion opcion) {
        LocalDateTime ahora = LocalDateTime.now();
        opcion.setIdOpcion(null);
        opcion.setFechaCreacion(ahora);
        opcion.setFechaModif(ahora);
        opcion.setUsuarioModif(opcion.getUsuarioCreacion());
        return opcionRepository.save(opcion);
    }

    @Transactional
    public Optional<Opcion> actualizar(Long idOpcion, Opcion datosNuevos) {
        return opcionRepository.findById(idOpcion).map(existente -> {
            existente.setNombre(datosNuevos.getNombre());
            existente.setOrdenMenu(datosNuevos.getOrdenMenu());
            existente.setPagina(datosNuevos.getPagina());
            existente.setIdMenu(datosNuevos.getIdMenu());

            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            return opcionRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idOpcion) {
        if (!opcionRepository.existsById(idOpcion)) {
            return false;
        }
        opcionRepository.deleteById(idOpcion);
        return true;
    }

}
