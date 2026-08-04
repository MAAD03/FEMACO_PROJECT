package com.femaco.main.Service.Catalogo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Catalogo.EstadoSucursal;
import com.femaco.main.Repository.Catalogo.EstadoSucursalRepository;


@Service
public class EstadoSucursalService {

    private final EstadoSucursalRepository estadoSucursalRepository;

    public EstadoSucursalService(EstadoSucursalRepository estadoSucursalRepository) {
        this.estadoSucursalRepository = estadoSucursalRepository;
    }

    public List<EstadoSucursal> buscarTodos() {
        return estadoSucursalRepository.findAll();
    }

    @Transactional
    public EstadoSucursal crear(EstadoSucursal estadoSucursal) {
        LocalDateTime ahora = LocalDateTime.now();
        estadoSucursal.setIdEstadoSucursal(null);
        estadoSucursal.setFechaCreacion(ahora);
        estadoSucursal.setFechaModif(ahora);
        estadoSucursal.setUsuarioModif(estadoSucursal.getUsuarioCreacion());
        return estadoSucursalRepository.save(estadoSucursal);
    }

    @Transactional
    public Optional<EstadoSucursal> actualizar(Long idEstadoSucursal, EstadoSucursal datosNuevos) {
        return estadoSucursalRepository.findById(idEstadoSucursal).map(existente -> {
            existente.setNombre(datosNuevos.getNombre());
            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            return estadoSucursalRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idEstadoSucursal) {
        if (!estadoSucursalRepository.existsById(idEstadoSucursal)) {
            return false;
        }
        estadoSucursalRepository.deleteById(idEstadoSucursal);
        return true;
    }
    
}
