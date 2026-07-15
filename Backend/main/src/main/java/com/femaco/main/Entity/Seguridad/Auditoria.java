package com.femaco.main.Entity.Seguridad;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@MappedSuperclass
@Data
public class Auditoria {

    @Column(name = "FechaCreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion", nullable = false, updatable = false)
    private Long usuarioCreacion;

    @Column(name = "FechaModif", nullable = false)
    private LocalDateTime fechaModif;

    @Column(name = "UsuarioModif", nullable = false)
    private Long usuarioModif;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaModif = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaModif = LocalDateTime.now();
    }
    
}
