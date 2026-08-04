package com.femaco.main.Service.Inventario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Inventario.AjusteInventario;
import com.femaco.main.Repository.Inventario.AjusteInventarioRepository;




@Service
public class AjusteInventarioService {

    private final AjusteInventarioRepository ajusteInventarioRepository;

    public AjusteInventarioService(AjusteInventarioRepository ajusteInventarioRepository) {
        this.ajusteInventarioRepository = ajusteInventarioRepository;
    }

    public List<AjusteInventario> buscarTodos() {
        return ajusteInventarioRepository.findAll();
    }

    @Transactional
    public AjusteInventario crear(AjusteInventario ajusteInventario) {
        LocalDateTime ahora = LocalDateTime.now();
        ajusteInventario.setIdAjusteInventario(null);
        ajusteInventario.setFechaCreacion(ahora);
        ajusteInventario.setFechaModif(ahora);
        ajusteInventario.setUsuarioModif(ajusteInventario.getUsuarioCreacion());
        return ajusteInventarioRepository.save(ajusteInventario);
    }

    @Transactional
    public Optional<AjusteInventario> actualizar(Long idAjusteInventario, AjusteInventario datosNuevos) {
        return ajusteInventarioRepository.findById(idAjusteInventario).map(existente -> {
            existente.setCantidadAjuste(datosNuevos.getCantidadAjuste());
            existente.setMotivo(datosNuevos.getMotivo());
            existente.setIdArticulo(datosNuevos.getIdArticulo());
            existente.setIdUsuario(datosNuevos.getIdUsuario());

            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            return ajusteInventarioRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idAjusteInventario) {
        if (!ajusteInventarioRepository.existsById(idAjusteInventario)) {
            return false;
        }
        ajusteInventarioRepository.deleteById(idAjusteInventario);
        return true;
    }
    
}
