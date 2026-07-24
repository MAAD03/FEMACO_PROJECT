package com.femaco.main.Entity.SucursalCotizacion;

import java.math.BigDecimal;
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
@Table(name = "cotizacion")
public class Cotizacion  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCotizacion")
    private Long idCotizacion;

    @Column(name = "Nombre", nullable = true, length = 100)
    private String nombre;

    @Column(name = "Nit", nullable = true, length = 50)
    private String nit;

    @Column(name = "Subtotal", precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "DescuentoTotal", precision = 5, scale = 2)
    private BigDecimal descuentoTotal;

    @Column(name = "Total", precision = 12, scale = 2)
    private BigDecimal total;

    @Column(name = "FechaCreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion", nullable = false, updatable = false)
    private Integer usuarioCreacion;

    @Column(name = "FechaModif", nullable = false)
    private LocalDateTime fechaModif;

    @Column(name = "UsuarioModif", nullable = false)
    private Integer usuarioModif;

    @Column(name = "IdUsuario")
    private Long idUsuario;  
    
}
