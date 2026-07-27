package com.femaco.main.Repository.Ventas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.Ventas.VentaDetalle;

@Repository("ventaDetalleRepository")
public interface VentaDetalleRepository extends JpaRepository<VentaDetalle, Long> {
    
}
