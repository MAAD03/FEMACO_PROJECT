package com.femaco.main.Repository.Catalogo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.Catalogo.EstadoVenta;

@Repository("estadoVentaRepository")
public interface EstadoVentaRepository extends JpaRepository<EstadoVenta, Long> {
    
}
