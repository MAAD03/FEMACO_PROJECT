package com.femaco.main.Service.Seguridad;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Seguridad.Usuario;
import com.femaco.main.Repository.Seguridad.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;  

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    @Transactional
    public boolean eliminar(Long idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            return false;
        }
        usuarioRepository.deleteById(idUsuario);
        return true;
    }

    @Transactional
    public Usuario crear(Usuario usuario) {
        LocalDateTime ahora = LocalDateTime.now();
        usuario.setIdUsuario(null);
        usuario.setFechaCreacion(ahora);
        usuario.setFechaModif(ahora);
        usuario.setUsuarioModif(usuario.getUsuarioCreacion());

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Optional<Usuario> actualizar(Long idUsuario, Usuario datosNuevos) {
        return usuarioRepository.findById(idUsuario).map(existente -> {
            existente.setNombre(datosNuevos.getNombre());
            existente.setApellido(datosNuevos.getApellido());          
            if (datosNuevos.getPassword() != null && !datosNuevos.getPassword().isBlank()) {
                existente.setPassword(passwordEncoder.encode(datosNuevos.getPassword()));
            }
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

    public Optional<Usuario> autenticar(String correo, String passwordPlano) {
        return usuarioRepository.findByCorreoElectronico(correo)
                .filter(u -> passwordEncoder.matches(passwordPlano, u.getPassword()));
    }
    
    
}
