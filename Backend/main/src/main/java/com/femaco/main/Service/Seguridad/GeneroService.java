package com.femaco.main.Service.Seguridad;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Seguridad.Genero;
import com.femaco.main.Repository.Seguridad.GeneroRepository;



@Service
public class GeneroService {

   private final GeneroRepository generoRepository;

    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public List<Genero> buscarTodos() {
        return generoRepository.findAll();
    }

    @Transactional
    public Genero crear(Genero genero) {
        LocalDateTime ahora = LocalDateTime.now();
        genero.setIdGenero(null); 
        genero.setFechaCreacion(ahora);
        genero.setFechaModif(ahora);
        genero.setUsuarioModif(genero.getUsuarioCreacion());
        return generoRepository.save(genero);
    }

    @Transactional
    public Optional<Genero> actualizar(Long idGenero, Genero datosNuevos) {
        return generoRepository.findById(idGenero).map(existente -> {
            existente.setNombre(datosNuevos.getNombre());

            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            return generoRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idGenero) {
        if (!generoRepository.existsById(idGenero)) {
            return false;
        }
        generoRepository.deleteById(idGenero);
        return true;
    }

    
    
}
