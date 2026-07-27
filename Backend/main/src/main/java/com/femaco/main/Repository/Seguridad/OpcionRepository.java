package com.femaco.main.Repository.Seguridad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.Seguridad.Opcion;

@Repository("opcionRepository")
public interface OpcionRepository extends JpaRepository<Opcion, Long> {
    
}
