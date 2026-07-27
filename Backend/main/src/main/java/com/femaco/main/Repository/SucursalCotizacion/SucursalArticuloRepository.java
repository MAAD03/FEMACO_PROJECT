package com.femaco.main.Repository.SucursalCotizacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.SucursalCotizacion.SucursalArticulo;

@Repository("sucursalArticuloRepository")
public interface SucursalArticuloRepository extends JpaRepository<SucursalArticulo, Long> {
    
}
