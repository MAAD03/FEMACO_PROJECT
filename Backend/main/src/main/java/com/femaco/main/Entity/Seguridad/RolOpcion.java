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
@Table(name = "RoleOpcion")
public class RolOpcion  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdRolOpcion")
    private Long idRolOpcion;  

    @Column(name = "Alta", nullable = false)
    private Boolean alta;

    @Column(name = "Baja", nullable = false)
    private Boolean baja;

    @Column(name = "Cambio", nullable = false)
    private Boolean cambio;

    @Column(name = "FechaCreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion", nullable = false, updatable = false)
    private Integer usuarioCreacion;

    @Column(name = "FechaModif", nullable = false)
    private LocalDateTime fechaModif;

    @Column(name = "UsuarioModif", nullable = false)
    private Integer usuarioModif;
    
    @Column(name = "IdOpcion")
    private Long idOpcion;  

    @Column(name = "IdRol")
    private Long idRol;  

}
