package com.femaco.main.Entity.Seguridad;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuario")
    private Long idUsuario;  

    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "Apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "Password", nullable = false, length = 255)
    private String password;

    @Column(name = "CorreoElectronico", nullable = false, length = 150)
    private String correoElectronico;

    @Column(name = "RequiereCambioPassword", nullable = false)
    private Boolean requiereCambioPassword;

    @Column(name = "Pregunta", nullable = false, length = 150)
    private String pregunta;

    @Column(name = "Respuesta", nullable = false, length = 150)
    private String respuesta;

    @Column(name = "FechaCreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion", nullable = false, updatable = false)
    private Integer usuarioCreacion;

    @Column(name = "FechaModif", nullable = false)
    private LocalDateTime fechaModif;

    @Column(name = "UsuarioModif", nullable = false)
    private Integer usuarioModif;

    @Column(name = "IdGenero")
    private Long idGenero;  

    @Column(name = "IdEstadoUsuario")
    private Long idEstadoUsuario;

    @Column(name = "IdSucursal")
    private Long idSucursal;  

    @Column(name = "IdRol")
    private Long idRol;  
    


    
}
