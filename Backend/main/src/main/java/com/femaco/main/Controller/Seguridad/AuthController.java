package com.femaco.main.Controller.Seguridad;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.femaco.main.Config.JwtUtil;
import com.femaco.main.Entity.Seguridad.Usuario;
import com.femaco.main.Service.Seguridad.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    public AuthController(UsuarioService usuarioService, JwtUtil jwtUtil) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    Optional<Usuario> usuarioOpt = usuarioService.autenticar(
            request.correoElectronico(),
            request.password()
    );

    if (usuarioOpt.isEmpty()) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("mensaje", "Credenciales inválidas"));
    }

    Usuario usuario = usuarioOpt.get();
    String token = jwtUtil.generateToken(usuario.getCorreoElectronico());

    return ResponseEntity.ok(new LoginResponse(
            token,
            usuario.getIdUsuario(),
            usuario.getNombre()
    ));
}

public record LoginRequest(String correoElectronico, String password) {}
public record LoginResponse(String token, Long idUsuario, String nombre) {}
}