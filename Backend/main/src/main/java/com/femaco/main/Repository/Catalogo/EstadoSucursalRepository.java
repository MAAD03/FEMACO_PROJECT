package com.femaco.main.Repository.Catalogo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.Catalogo.EstadoSucursal;

@Repository("estadoSucursalRepository")
public interface EstadoSucursalRepository extends JpaRepository<EstadoSucursal, Long> {
    
}
