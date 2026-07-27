package com.femaco.main.Repository.Seguridad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.Seguridad.Genero;

@Repository("generoRepository")
public interface GeneroRepository extends JpaRepository<Genero, Long> {
    
}
