package com.femaco.main.Repository.Seguridad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.Seguridad.Rol;

@Repository("rolRepository")
public interface RolRepository extends JpaRepository<Rol, Long> {
    
}
