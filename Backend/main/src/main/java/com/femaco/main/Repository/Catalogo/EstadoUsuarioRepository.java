package com.femaco.main.Repository.Catalogo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.Catalogo.EstadoUsuario;

@Repository("estadoUsuarioRepository")
public interface EstadoUsuarioRepository extends JpaRepository<EstadoUsuario, Long> {
    
}
