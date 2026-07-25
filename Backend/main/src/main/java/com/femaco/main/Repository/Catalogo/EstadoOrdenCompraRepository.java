package com.femaco.main.Repository.Catalogo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.Catalogo.EstadoOrdenCompra;

@Repository("estadoOrdenCompraRepository")
public interface EstadoOrdenCompraRepository extends JpaRepository<EstadoOrdenCompra, Long> {
    
}
