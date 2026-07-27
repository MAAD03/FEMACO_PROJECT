package com.femaco.main.Repository.Seguridad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.Seguridad.Modulo;

@Repository("moduloRepository")
public interface ModuloRepository extends JpaRepository<Modulo, Long> {
    
}
