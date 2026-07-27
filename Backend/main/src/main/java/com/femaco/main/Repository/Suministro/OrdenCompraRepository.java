package com.femaco.main.Repository.Suministro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.Suministro.OrdenCompra;

@Repository("ordenCompraRepository")
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {
    
}
