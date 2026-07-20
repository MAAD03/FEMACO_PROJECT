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
@Table(name = "opcion")
public class Opcion  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdOpcion")
    private Long idOpcion;  

    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "OrdenMenu", nullable = false)
    private Integer ordenMenu;

    @Column(name = "Pagina", nullable = false)
    private Integer pagina;

    @Column(name = "FechaCreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion", nullable = false, updatable = false)
    private Integer usuarioCreacion;

    @Column(name = "FechaModif", nullable = false)
    private LocalDateTime fechaModif;

    @Column(name = "UsuarioModif", nullable = false)
    private Integer usuarioModif;

    @Column(name = "IdMenu")
    private Long idMenu;  
}
