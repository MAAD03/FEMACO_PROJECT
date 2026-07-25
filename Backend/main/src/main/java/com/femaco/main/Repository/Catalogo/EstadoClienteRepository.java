package com.femaco.main.Repository.Catalogo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.Catalogo.EstadoCliente;

@Repository("estadoClienteRepository")
public interface EstadoClienteRepository extends JpaRepository<EstadoCliente, Long> {
    
}
