package com.femaco.main.Entity.Ventas;

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
@Table(name = "venta")
public class Venta  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdVenta")
    private Long idVenta;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Subtotal", precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "DescuentoTotal", precision = 5, scale = 2)
    private BigDecimal descuentoTotal;

    @Column(name = "Total", precision = 12, scale = 2)
    private BigDecimal total;

    @Column(name = "EsPedido", nullable = false)
    private Boolean esPedido;

    @Column(name = "NumeroFactura", nullable = false, length = 45)
    private String numeroFactura;

    @Column(name = "FechaCreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion", nullable = false, updatable = false)
    private Integer usuarioCreacion;

    @Column(name = "FechaModif", nullable = false)
    private LocalDateTime fechaModif;

    @Column(name = "UsuarioModif", nullable = false)
    private Integer usuarioModif;

    @Column(name = "IdEstadoVenta")
    private Long idEstadoVenta;

    @Column(name = "IdCliente")
    private Long idCliente;

    @Column(name = "IdUsuario")
    private Long idUsuario;  


}
