package com.femaco.main.Service.Seguridad;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Seguridad.Usuario;
import com.femaco.main.Repository.Seguridad.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario crear(Usuario usuario) {
        LocalDateTime ahora = LocalDateTime.now();
        usuario.setIdUsuario(null);
        usuario.setFechaCreacion(ahora);
        usuario.setFechaModif(ahora);
        usuario.setUsuarioModif(usuario.getUsuarioCreacion());
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Optional<Usuario> actualizar(Long idUsuario, Usuario datosNuevos) {
        return usuarioRepository.findById(idUsuario).map(existente -> {
            existente.setNombre(datosNuevos.getNombre());
            existente.setApellido(datosNuevos.getApellido());
            existente.setPassword(datosNuevos.getPassword());
            existente.setCorreoElectronico(datosNuevos.getCorreoElectronico());
            existente.setRequiereCambioPassword(datosNuevos.getRequiereCambioPassword());
            existente.setPregunta(datosNuevos.getPregunta());
            existente.setRespuesta(datosNuevos.getRespuesta());
            existente.setIdGenero(datosNuevos.getIdGenero());
            existente.setIdEstadoUsuario(datosNuevos.getIdEstadoUsuario());
            existente.setIdSucursal(datosNuevos.getIdSucursal());
            existente.setIdRol(datosNuevos.getIdRol());

            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            return usuarioRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            return false;
        }
        usuarioRepository.deleteById(idUsuario);
        return true;
    }
    
}
