package com.femaco.main.Repository.Seguridad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femaco.main.Entity.Seguridad.Usuario;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    List<Usuario> findByCorreoElectronicoAndPassword(String correoElectronico, String password);

}
