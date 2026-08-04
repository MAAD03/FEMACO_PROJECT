package com.femaco.main.Service.Seguridad;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Seguridad.Modulo;
import com.femaco.main.Repository.Seguridad.ModuloRepository;

@Service
public class ModuloService {

    private final ModuloRepository moduloRepository;

    public ModuloService(ModuloRepository moduloRepository) {
        this.moduloRepository = moduloRepository;
    }

    public List<Modulo> buscarTodos() {
        return moduloRepository.findAll();
    }

    @Transactional
    public Modulo crear(Modulo modulo) {
        LocalDateTime ahora = LocalDateTime.now();
        modulo.setIdModulo(null);
        modulo.setFechaCreacion(ahora);
        modulo.setFechaModif(ahora);
        modulo.setUsuarioModif(modulo.getUsuarioCreacion());
        return moduloRepository.save(modulo);
    }

    @Transactional
    public Optional<Modulo> actualizar(Long idModulo, Modulo datosNuevos) {
        return moduloRepository.findById(idModulo).map(existente -> {
            existente.setNombre(datosNuevos.getNombre());
            existente.setOrdenMenu(datosNuevos.getOrdenMenu());
            
            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            return moduloRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idModulo) {
        if (!moduloRepository.existsById(idModulo)) {
            return false;
        }
        moduloRepository.deleteById(idModulo);
        return true;
    }
    
}
