package com.femaco.main.Repository.Seguridad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.Seguridad.Menu;

@Repository("menuRepository")
public interface MenuRepository extends JpaRepository<Menu, Long> {
    
}
