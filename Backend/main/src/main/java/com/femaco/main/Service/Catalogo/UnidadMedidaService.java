package com.femaco.main.Service.Catalogo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Catalogo.UnidadMedida;
import com.femaco.main.Repository.Catalogo.UnidadMedidaRepository;


@Service
public class UnidadMedidaService {

    private final UnidadMedidaRepository unidadMedidaRepository;

    public UnidadMedidaService(UnidadMedidaRepository unidadMedidaRepository) {
        this.unidadMedidaRepository = unidadMedidaRepository;
    }

    public List<UnidadMedida> buscarTodos() {
        return unidadMedidaRepository.findAll();
    }

    @Transactional
    public UnidadMedida crear(UnidadMedida unidadMedida) {
        LocalDateTime ahora = LocalDateTime.now();
        unidadMedida.setIdUnidadMedida(null);
        unidadMedida.setFechaCreacion(ahora);
        unidadMedida.setFechaModif(ahora);
        unidadMedida.setUsuarioModif(unidadMedida.getUsuarioCreacion());
        return unidadMedidaRepository.save(unidadMedida);
    }

    @Transactional
    public Optional<UnidadMedida> actualizar(Long idUnidadMedida, UnidadMedida datosNuevos) {
        return unidadMedidaRepository.findById(idUnidadMedida).map(existente -> {
            existente.setNombre(datosNuevos.getNombre());
            existente.setAbreviatura(datosNuevos.getAbreviatura());
            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            return unidadMedidaRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idUnidadMedida) {
        if (!unidadMedidaRepository.existsById(idUnidadMedida)) {
            return false;
        }
        unidadMedidaRepository.deleteById(idUnidadMedida);
        return true;
    }
}
