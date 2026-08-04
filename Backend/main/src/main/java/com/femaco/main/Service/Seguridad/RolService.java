package com.femaco.main.Service.Seguridad;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Seguridad.Rol;
import com.femaco.main.Repository.Seguridad.RolRepository;

@Service
public class RolService {
    
    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Rol> buscarTodos() {
        return rolRepository.findAll();
    }

    @Transactional
    public Rol crear(Rol rol) {
        LocalDateTime ahora = LocalDateTime.now();
        rol.setIdRol(null);
        rol.setFechaCreacion(ahora);
        rol.setFechaModif(ahora);
        rol.setUsuarioModif(rol.getUsuarioCreacion());
        return rolRepository.save(rol);
    }

    @Transactional
    public Optional<Rol> actualizar(Long idRol, Rol datosNuevos) {
        return rolRepository.findById(idRol).map(existente -> {
            existente.setNombre(datosNuevos.getNombre());
            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            return rolRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idRol) {
        if (!rolRepository.existsById(idRol)) {
            return false;
        }
        rolRepository.deleteById(idRol);
        return true;
    }

}
