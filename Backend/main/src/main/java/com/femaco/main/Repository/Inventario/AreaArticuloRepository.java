package com.femaco.main.Repository.Inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.Inventario.AreaArticulo;

@Repository("areaArticuloRepository")
public interface AreaArticuloRepository extends JpaRepository<AreaArticulo, Long> {
    
}
