package com.femaco.main.Service.Catalogo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.femaco.main.Entity.Catalogo.EstadoArticulo;
import com.femaco.main.Repository.Catalogo.EstadoArticuloRepository;

import jakarta.transaction.Transactional;

@Service
public class EstadoArticuloService {

    private final EstadoArticuloRepository estadoArticuloRepository;
    
    public EstadoArticuloService(EstadoArticuloRepository estadoArticuloRepository) {
        this.estadoArticuloRepository = estadoArticuloRepository;
    }

    public List<EstadoArticulo> buscarTodos() {
        return estadoArticuloRepository.findAll();
    }

    @Transactional
    public EstadoArticulo guardar(EstadoArticulo estadoArticulo) {
        return estadoArticuloRepository.save(estadoArticulo);
    }

    @Transactional
    public boolean eliminar(Long idEstadoArticulo) {
        if (!estadoArticuloRepository.existsById(idEstadoArticulo)) {
            return false;
        }
        estadoArticuloRepository.deleteById(idEstadoArticulo);
        return true;
        
    }
}