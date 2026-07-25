package com.femaco.main.Repository.Catalogo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.Catalogo.EstadoProveedor;

@Repository("estadoProveedorRepository")
public interface EstadoProveedorRepository extends JpaRepository<EstadoProveedor, Long> {
    
}
