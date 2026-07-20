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
@Table(name = "genero")
public class Genero  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdGenero")
    private Long idGenero;  
    
    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "FechaCreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion", nullable = false, updatable = false)
    private Integer usuarioCreacion;

    @Column(name = "FechaModif", nullable = false)
    private LocalDateTime fechaModif;

    @Column(name = "UsuarioModif", nullable = false)
    private Integer usuarioModif;

}
