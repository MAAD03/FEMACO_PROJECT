package com.femaco.main.Repository.Inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.Inventario.MovimientoInventario;

@Repository("movimientoInventarioRepository")
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long> {
    
}
