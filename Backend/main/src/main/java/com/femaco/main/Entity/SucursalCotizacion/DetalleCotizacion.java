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
@Table(name = "detalle_cotizacion")
public class DetalleCotizacion  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDetalleCotizacion")
    private Long idDetalleCotizacion;

   @Column(name = "Cantidad", precision = 12, scale = 2)
    private BigDecimal cantidad; 

    @Column(name = "PrecioUnitario", precision = 12, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "DescuentoAplicado", precision = 5, scale = 2)
    private BigDecimal descuentoAplicado;

    @Column(name = "Subtotal", precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "FechaCreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion", nullable = false, updatable = false)
    private Integer usuarioCreacion;

    @Column(name = "FechaModif", nullable = false)
    private LocalDateTime fechaModif;

    @Column(name = "UsuarioModif", nullable = false)
    private Integer usuarioModif;

    @Column(name = "IdCotizacion")
    private Long idCotizacion;

    @Column(name = "IdArticulo")
    private Long idArticulo;

}