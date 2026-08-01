package com.femaco.main.Service.Catalogo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.femaco.main.Entity.Catalogo.EstadoProveedor;
import com.femaco.main.Repository.Catalogo.EstadoProveedorRepository;

import jakarta.transaction.Transactional;



@Service
public class EstadoProveedorService {
    
    private final EstadoProveedorRepository estadoProveedorRepository;

    public EstadoProveedorService(EstadoProveedorRepository estadoProveedorRepository) {
        this.estadoProveedorRepository = estadoProveedorRepository;
    }

    public List<EstadoProveedor> buscarTodos() {
        return estadoProveedorRepository.findAll();
    }

    @Transactional
    public EstadoProveedor crear(EstadoProveedor estadoProveedor) {
        LocalDateTime ahora = LocalDateTime.now();
        estadoProveedor.setIdEstadoProveedor(null);
        estadoProveedor.setFechaCreacion(ahora);
        estadoProveedor.setFechaModif(ahora);
        estadoProveedor.setUsuarioModif(estadoProveedor.getUsuarioCreacion());
        return estadoProveedorRepository.save(estadoProveedor);
    }

    @Transactional
    public Optional<EstadoProveedor> actualizar(Long idEstadoProveedor, EstadoProveedor datosNuevos) {
        return estadoProveedorRepository.findById(idEstadoProveedor).map(existente -> {
            existente.setNombre(datosNuevos.getNombre());
            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            // fechaCreacion y usuarioCreacion no se tocan
            return estadoProveedorRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idEstadoProveedor) {
        if (!estadoProveedorRepository.existsById(idEstadoProveedor)) {
            return false;
        }
        estadoProveedorRepository.deleteById(idEstadoProveedor);
        return true;
    }

}
