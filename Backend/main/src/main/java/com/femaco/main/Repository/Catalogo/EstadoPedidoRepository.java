package com.femaco.main.Repository.Catalogo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.Catalogo.EstadoPedido;

@Repository("estadoPedidoRepository")
public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido, Long> {
    
}
