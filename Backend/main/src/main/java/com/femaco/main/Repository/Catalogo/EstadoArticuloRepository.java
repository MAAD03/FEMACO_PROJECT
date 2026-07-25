package com.femaco.main.Repository.Catalogo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.Catalogo.EstadoArticulo;

@Repository("estadoArticuloRepository")
public interface EstadoArticuloRepository extends JpaRepository<EstadoArticulo, Long> {

    
}
