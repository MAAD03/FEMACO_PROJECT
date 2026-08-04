package com.femaco.main.Service.SucursalCotizacion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.SucursalCotizacion.SucursalArticulo;
import com.femaco.main.Repository.SucursalCotizacion.SucursalArticuloRepository;

@Service
public class SucursalArticuloService {

    private final SucursalArticuloRepository sucursalArticuloRepository;

    public SucursalArticuloService(SucursalArticuloRepository sucursalArticuloRepository) {
        this.sucursalArticuloRepository = sucursalArticuloRepository;
    }

    public List<SucursalArticulo> buscarTodos() {
        return sucursalArticuloRepository.findAll();
    }

    @Transactional
    public SucursalArticulo crear(SucursalArticulo sucursalArticulo) {
        LocalDateTime ahora = LocalDateTime.now();
        sucursalArticulo.setIdSucursalArticulo(null);
        sucursalArticulo.setFechaCreacion(ahora);
        sucursalArticulo.setFechaModif(ahora);
        sucursalArticulo.setUsuarioModif(sucursalArticulo.getUsuarioCreacion());
        return sucursalArticuloRepository.save(sucursalArticulo);
    }

    @Transactional
    public Optional<SucursalArticulo> actualizar(Long idSucursalArticulo, SucursalArticulo datosNuevos) {
        return sucursalArticuloRepository.findById(idSucursalArticulo).map(existente -> {
            existente.setIdSucursal(datosNuevos.getIdSucursal());
            existente.setIdArticulo(datosNuevos.getIdArticulo());

            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            return sucursalArticuloRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idSucursalArticulo) {
        if (!sucursalArticuloRepository.existsById(idSucursalArticulo)) {
            return false;
        }
        sucursalArticuloRepository.deleteById(idSucursalArticulo);
        return true;
    }
    
}
