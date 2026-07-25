package com.femaco.main.Repository.Catalogo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.Catalogo.UnidadMedida;

@Repository("unidadMedidaRepository")
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Long> {
    
}
