package com.femaco.main.Repository.SucursalCotizacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.SucursalCotizacion.Sucursal;

@Repository("sucursalRepository")
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
    
}
