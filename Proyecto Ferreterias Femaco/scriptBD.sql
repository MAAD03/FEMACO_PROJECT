
CREATE SCHEMA IF NOT EXISTS `femacodb` DEFAULT CHARACTER SET utf8mb4;
USE `femacodb`;

-- -----------------------------------------------------
-- Tabla: bitacora
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bitacora` (
  `IdBitacora` INT NOT NULL AUTO_INCREMENT,
  `Operacion` VARCHAR(100) NULL,
  `DetalleOperacion` VARCHAR(200) NULL,
  `NombreTabla` VARCHAR(100) NULL,
  `DatosNuevos` TEXT NULL,
  `DatosAnterior` TEXT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdBitacora`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla: estado_usuario
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estado_usuario` (
  `IdEstadoUsuario` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdEstadoUsuario`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla: estado_sucursal
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estado_sucursal` (
  `IdEstadoSucursal` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdEstadoSucursal`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla: sucursal
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sucursal` (
  `IdSucursal` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(150) NOT NULL,
  `Direccion` VARCHAR(255) NULL,
  `Telefono` VARCHAR(45) NULL,
  `IdEstadoSucursal` INT NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdSucursal`),
  CONSTRAINT `fk_sucursal_estado` FOREIGN KEY (`IdEstadoSucursal`) REFERENCES `estado_sucursal`(`IdEstadoSucursal`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tablas de Seguridad y Menú
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `genero` (
  `IdGenero` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(50) NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdGenero`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `rol` (
  `IdRol` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdRol`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `modulo` (
  `IdModulo` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  `OrdenMenu` INT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdModulo`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `menu` (
  `IdMenu` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  `OrdenMenu` INT NULL,
  `IdModulo` INT NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdMenu`),
  CONSTRAINT `fk_menu_modulo` FOREIGN KEY (`IdModulo`) REFERENCES `modulo`(`IdModulo`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `opcion` (
  `IdOpcion` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  `OrdenMenu` INT NULL,
  `Pagina` VARCHAR(150) NULL,
  `IdMenu` INT NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdOpcion`),
  CONSTRAINT `fk_opcion_menu` FOREIGN KEY (`IdMenu`) REFERENCES `menu`(`IdMenu`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `rol_opcion` (
  `IdRolOpcion` INT NOT NULL AUTO_INCREMENT,
  `IdRol` INT NOT NULL,
  `IdOpcion` INT NOT NULL,
  `Alta` TINYINT NOT NULL DEFAULT 0,
  `Baja` TINYINT NOT NULL DEFAULT 0,
  `Cambio` TINYINT NOT NULL DEFAULT 0,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdRolOpcion`),
  UNIQUE INDEX `IdRol_IdOpcion_UNIQUE` (`IdRol`, `IdOpcion`),
  CONSTRAINT `fk_roleop_rol` FOREIGN KEY (`IdRol`) REFERENCES `rol`(`IdRol`),
  CONSTRAINT `fk_roleop_opcion` FOREIGN KEY (`IdOpcion`) REFERENCES `opcion`(`IdOpcion`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla: usuario
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usuario` (
  `IdUsuario` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  `Apellido` VARCHAR(100) NOT NULL,
  `Password` VARCHAR(255) NOT NULL,
  `CorreoElectronico` VARCHAR(150) NOT NULL,
  `RequiereCambioPassword` TINYINT NOT NULL DEFAULT 1,
  `Pregunta` VARCHAR(150) NULL,
  `Respuesta` VARCHAR(150) NULL,
  `IdGenero` INT NOT NULL,
  `IdEstadoUsuario` INT NOT NULL,
  `IdSucursal` INT NOT NULL,
  `IdRol` INT NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdUsuario`),
  UNIQUE INDEX `CorreoElectronico_UNIQUE` (`CorreoElectronico`),
  CONSTRAINT `fk_usuario_genero` FOREIGN KEY (`IdGenero`) REFERENCES `genero`(`IdGenero`),
  CONSTRAINT `fk_usuario_estado` FOREIGN KEY (`IdEstadoUsuario`) REFERENCES `estado_usuario`(`IdEstadoUsuario`),
  CONSTRAINT `fk_usuario_sucursal` FOREIGN KEY (`IdSucursal`) REFERENCES `sucursal`(`IdSucursal`),
  CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`IdRol`) REFERENCES `rol`(`IdRol`)
) ENGINE = InnoDB;



-- -----------------------------------------------------
-- Tablas de Artículos e Inventario
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `area_articulo` (
  `IdAreaArticulo` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  `Descripcion` TEXT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdAreaArticulo`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `unidad_medida` (
  `IdUnidadMedida` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  `Abreviatura` VARCHAR(20) NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdUnidadMedida`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `estado_articulo` (
  `IdEstadoArticulo` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdEstadoArticulo`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `articulo` (
  `IdArticulo` INT NOT NULL AUTO_INCREMENT,
  `Codigo` VARCHAR(50) NULL,
  `Nombre` VARCHAR(200) NOT NULL,
  `Descripcion` TEXT NULL,
  `StockActual` DECIMAL(12,2) NULL DEFAULT 0.00,
  `StockMinimo` DECIMAL(12,2) NULL DEFAULT 0.00,
  `PrecioCompraUltimoProveedor` DECIMAL(12,2) NULL,
  `MargenGanancia` DECIMAL(5,2) NULL,
  `CantidadMinimaDescuento` DECIMAL(12,2) NULL,
  `DescuentoMayorista` DECIMAL(5,2) NULL,
  `IdAreaArticulo` INT NOT NULL,
  `IdUnidadMedida` INT NOT NULL,
  `IdEstadoArticulo` INT NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdArticulo`),
  CONSTRAINT `fk_art_area` FOREIGN KEY (`IdAreaArticulo`) REFERENCES `area_articulo`(`IdAreaArticulo`),
  CONSTRAINT `fk_art_unidad` FOREIGN KEY (`IdUnidadMedida`) REFERENCES `unidad_medida`(`IdUnidadMedida`),
  CONSTRAINT `fk_art_estado` FOREIGN KEY (`IdEstadoArticulo`) REFERENCES `estado_articulo`(`IdEstadoArticulo`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tablas de Proveedores
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estado_proveedor` (
  `IdEstadoProveedor` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdEstadoProveedor`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `proveedor` (
  `IdProveedor` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(150) NOT NULL,
  `Nit` VARCHAR(45) NULL,
  `Telefono` VARCHAR(45) NULL,
  `Direccion` TEXT NULL,
  `NombreContacto` VARCHAR(100) NULL,
  `IdEstadoProveedor` INT NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdProveedor`),
  UNIQUE INDEX `Nit_UNIQUE` (`Nit`),
  CONSTRAINT `fk_prov_estado` FOREIGN KEY (`IdEstadoProveedor`) REFERENCES `estado_proveedor`(`IdEstadoProveedor`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tablas de Compras
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estado_orden_compra` (
  `IdEstadoOrdenCompra` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdEstadoOrdenCompra`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `orden_compra` (
  `IdOrdenCompra` INT NOT NULL AUTO_INCREMENT,
  `Total` DECIMAL(12,2) NULL,
  `Notas` TEXT NULL,
  `IdProveedor` INT NOT NULL,
  `IdEstadoOrdenCompra` INT NOT NULL,
  `IdUsuario` INT NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdOrdenCompra`),
  CONSTRAINT `fk_oc_prov` FOREIGN KEY (`IdProveedor`) REFERENCES `proveedor`(`IdProveedor`),
  CONSTRAINT `fk_oc_estado` FOREIGN KEY (`IdEstadoOrdenCompra`) REFERENCES `estado_orden_compra`(`IdEstadoOrdenCompra`),
  CONSTRAINT `fk_oc_usuario` FOREIGN KEY (`IdUsuario`) REFERENCES `usuario`(`IdUsuario`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `orden_compra_detalle` (
  `IdOrdenCompraDetalle` INT NOT NULL AUTO_INCREMENT,
  `Cantidad` DECIMAL(12,2) NOT NULL,
  `PrecioUnitario` DECIMAL(12,2) NOT NULL,
  `Total` DECIMAL(12,2) NOT NULL,
  `IdOrdenCompra` INT NOT NULL,
  `IdArticulo` INT NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdOrdenCompraDetalle`),
  CONSTRAINT `fk_ocd_oc` FOREIGN KEY (`IdOrdenCompra`) REFERENCES `orden_compra`(`IdOrdenCompra`),
  CONSTRAINT `fk_ocd_art` FOREIGN KEY (`IdArticulo`) REFERENCES `articulo`(`IdArticulo`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tablas de Clientes y Ventas
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estado_cliente` (
  `IdEstadoCliente` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdEstadoCliente`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `cliente` (
  `IdCliente` INT NOT NULL AUTO_INCREMENT,
  `Nit` VARCHAR(45) NOT NULL,
  `Nombre` VARCHAR(150) NOT NULL,
  `Telefono` VARCHAR(45) NULL,
  `Correo` VARCHAR(100) NULL,
  `IdEstadoCliente` INT NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdCliente`),
  CONSTRAINT `fk_cliente_estado` FOREIGN KEY (`IdEstadoCliente`) REFERENCES `estado_cliente`(`IdEstadoCliente`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `estado_venta` (
  `IdEstadoVenta` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdEstadoVenta`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `venta` (
  `IdVenta` INT NOT NULL AUTO_INCREMENT,
  `Fecha` DATETIME NULL,
  `Subtotal` DECIMAL(12,2) NULL,
  `DescuentoTotal` DECIMAL(5,2) NULL,
  `Total` DECIMAL(12,2) NULL,
  `EsPedido` TINYINT NULL DEFAULT 0,
  `NumeroFactura` VARCHAR(45) NULL,
  `IdEstadoVenta` INT NOT NULL,
  `IdCliente` INT NOT NULL,
  `IdUsuario` INT NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdVenta`),
  UNIQUE INDEX `NumeroFactura_UNIQUE` (`NumeroFactura`),
  CONSTRAINT `fk_venta_estado` FOREIGN KEY (`IdEstadoVenta`) REFERENCES `estado_venta`(`IdEstadoVenta`),
  CONSTRAINT `fk_venta_cliente` FOREIGN KEY (`IdCliente`) REFERENCES `cliente`(`IdCliente`),
  CONSTRAINT `fk_venta_usuario` FOREIGN KEY (`IdUsuario`) REFERENCES `usuario`(`IdUsuario`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `venta_detalle` (
  `IdVentaDetalle` INT NOT NULL AUTO_INCREMENT,
  `Cantidad` DECIMAL(12,2) NOT NULL,
  `PrecioUnitario` DECIMAL(12,2) NOT NULL,
  `DescuentoAplicado` DECIMAL(5,2) NULL DEFAULT 0.00,
  `Subtotal` DECIMAL(12,2) NOT NULL,
  `IdVenta` INT NOT NULL,
  `IdArticulo` INT NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdVentaDetalle`),
  CONSTRAINT `fk_vd_venta` FOREIGN KEY (`IdVenta`) REFERENCES `venta`(`IdVenta`),
  CONSTRAINT `fk_vd_art` FOREIGN KEY (`IdArticulo`) REFERENCES `articulo`(`IdArticulo`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tablas de Pedidos y Movimientos
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estado_pedido` (
  `IdEstadoPedido` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdEstadoPedido`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `pedido` (
  `IdPedido` INT NOT NULL AUTO_INCREMENT,
  `FechaEntrega` DATE NULL,
  `DireccionEntrega` TEXT NULL,
  `NotasEntrega` TEXT NULL,
  `NumeroEntrega` VARCHAR(50) NULL,
  `IdVenta` INT NOT NULL,
  `IdEstadoPedido` INT NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdPedido`),
  CONSTRAINT `fk_pedido_venta` FOREIGN KEY (`IdVenta`) REFERENCES `venta`(`IdVenta`),
  CONSTRAINT `fk_pedido_estado` FOREIGN KEY (`IdEstadoPedido`) REFERENCES `estado_pedido`(`IdEstadoPedido`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ajuste_inventario` (
  `IdAjusteInventario` INT NOT NULL AUTO_INCREMENT,
  `CantidadAjuste` DECIMAL(12,2) NOT NULL,
  `Motivo` TEXT NULL,
  `IdArticulo` INT NOT NULL,
  `IdUsuario` INT NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdAjusteInventario`),
  CONSTRAINT `fk_ajuste_art` FOREIGN KEY (`IdArticulo`) REFERENCES `articulo`(`IdArticulo`),
  CONSTRAINT `fk_ajuste_usuario` FOREIGN KEY (`IdUsuario`) REFERENCES `usuario`(`IdUsuario`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `movimiento_inventario` (
  `IdMovimientoInventario` INT NOT NULL AUTO_INCREMENT,
  `TipoMovimiento` ENUM('entrada', 'salida', 'ajuste') NOT NULL,
  `Cantidad` DECIMAL(12,2) NOT NULL,
  `StockViejo` DECIMAL(12,2) NOT NULL,
  `StockNuevo` DECIMAL(12,2) NOT NULL,
  `Motivo` ENUM('venta', 'orden_compra', 'ajuste_manual', 'devolucion') NOT NULL,
  `IdArticulo` INT NOT NULL,
  `IdVenta` INT NULL,
  `IdOrdenCompra` INT NULL,
  `IdAjusteInventario` INT NULL,
  `IdUsuario` INT NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdMovimientoInventario`),
  CONSTRAINT `fk_mov_art` FOREIGN KEY (`IdArticulo`) REFERENCES `articulo`(`IdArticulo`),
  CONSTRAINT `fk_mov_venta` FOREIGN KEY (`IdVenta`) REFERENCES `venta`(`IdVenta`),
  CONSTRAINT `fk_mov_oc` FOREIGN KEY (`IdOrdenCompra`) REFERENCES `orden_compra`(`IdOrdenCompra`),
  CONSTRAINT `fk_mov_ajuste` FOREIGN KEY (`IdAjusteInventario`) REFERENCES `ajuste_inventario`(`IdAjusteInventario`),
  CONSTRAINT `fk_mov_usuario` FOREIGN KEY (`IdUsuario`) REFERENCES `usuario`(`IdUsuario`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tablas de relación y cotizaciones
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sucursal_articulo` (
  `IdSucursalArticulo` INT NOT NULL AUTO_INCREMENT,
  `IdSucursal` INT NOT NULL,
  `IdArticulo` INT NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdSucursalArticulo`),
  CONSTRAINT `fk_sa_suc` FOREIGN KEY (`IdSucursal`) REFERENCES `sucursal`(`IdSucursal`),
  CONSTRAINT `fk_sa_art` FOREIGN KEY (`IdArticulo`) REFERENCES `articulo`(`IdArticulo`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `cotizacion` (
  `IdCotizacion` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NULL,
  `Nit` VARCHAR(25) NULL,
  `Subtotal` DECIMAL(12,2) NULL,
  `DescuentoTotal` DECIMAL(5,2) NULL,
  `Total` DECIMAL(12,2) NULL,
  `IdUsuario` INT NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdCotizacion`),
  CONSTRAINT `fk_cot_usuario` FOREIGN KEY (`IdUsuario`) REFERENCES `usuario`(`IdUsuario`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `detalle_cotizacion` (
  `IdDetalleCotizacion` INT NOT NULL AUTO_INCREMENT,
  `Cantidad` DECIMAL(12,2) NULL,
  `PrecioUnitario` DECIMAL(12,2) NULL,
  `DescuentoAplicado` DECIMAL(5,2) NULL,
  `Subtotal` DECIMAL(12,2) NULL,
  `IdCotizacion` INT NOT NULL,
  `IdArticulo` INT NOT NULL,
  `FechaCreacion` DATETIME NOT NULL,
  `UsuarioCreacion` INT NOT NULL,
  `FechaModif` DATETIME NOT NULL,
  `UsuarioModif` INT NOT NULL,
  PRIMARY KEY (`IdDetalleCotizacion`),
  CONSTRAINT `fk_detalle_cot` FOREIGN KEY (`IdCotizacion`) REFERENCES `cotizacion`(`IdCotizacion`),
  CONSTRAINT `fk_detalle_art` FOREIGN KEY (`IdArticulo`) REFERENCES `articulo`(`IdArticulo`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- DATOS INICIALES - CATÁLOGOS Y ADMIN
-- -----------------------------------------------------

-- 1. estado_usuario
INSERT INTO `estado_usuario` 
(`IdEstadoUsuario`, `Nombre`, `FechaCreacion`, `UsuarioCreacion`, `FechaModif`, `UsuarioModif`) 
VALUES
(1, 'Activo', NOW(), 1, NOW(), 1),
(2, 'Inactivo', NOW(), 1, NOW(), 1),
(3, 'Bloqueado', NOW(), 1, NOW(), 1);

-- 2. estado_sucursal
INSERT INTO `estado_sucursal` 
(`IdEstadoSucursal`, `Nombre`, `FechaCreacion`, `UsuarioCreacion`, `FechaModif`, `UsuarioModif`) 
VALUES
(1, 'Activo', NOW(), 1, NOW(), 1),
(2, 'Inactivo', NOW(), 1, NOW(), 1);

-- 3. genero
INSERT INTO `genero` 
(`IdGenero`, `Nombre`, `FechaCreacion`, `UsuarioCreacion`, `FechaModif`, `UsuarioModif`) 
VALUES
(1, 'Masculino', NOW(), 1, NOW(), 1),
(2, 'Femenino', NOW(), 1, NOW(), 1),
(3, 'Otro', NOW(), 1, NOW(), 1);

-- 4. estado_articulo
INSERT INTO `estado_articulo` 
(`IdEstadoArticulo`, `Nombre`, `FechaCreacion`, `UsuarioCreacion`, `FechaModif`, `UsuarioModif`) 
VALUES
(1, 'Activo', NOW(), 1, NOW(), 1),
(2, 'Inactivo', NOW(), 1, NOW(), 1),
(3, 'Descontinuado', NOW(), 1, NOW(), 1);

-- 5. estado_proveedor
INSERT INTO `estado_proveedor` 
(`IdEstadoProveedor`, `Nombre`, `FechaCreacion`, `UsuarioCreacion`, `FechaModif`, `UsuarioModif`) 
VALUES
(1, 'Activo', NOW(), 1, NOW(), 1),
(2, 'Inactivo', NOW(), 1, NOW(), 1);

-- 6. estado_orden_compra
INSERT INTO `estado_orden_compra` 
(`IdEstadoOrdenCompra`, `Nombre`, `FechaCreacion`, `UsuarioCreacion`, `FechaModif`, `UsuarioModif`) 
VALUES
(1, 'Pendiente', NOW(), 1, NOW(), 1),
(2, 'Aprobada', NOW(), 1, NOW(), 1),
(3, 'Parcialmente recibida', NOW(), 1, NOW(), 1),
(4, 'Pospuesta', NOW(), 1, NOW(), 1),
(5, 'Cancelada', NOW(), 1, NOW(), 1),
(6, 'Finalizada', NOW(), 1, NOW(), 1);

-- 7. estado_cliente
INSERT INTO `estado_cliente` 
(`IdEstadoCliente`, `Nombre`, `FechaCreacion`, `UsuarioCreacion`, `FechaModif`, `UsuarioModif`) 
VALUES
(1, 'Activo', NOW(), 1, NOW(), 1),
(2, 'Inactivo', NOW(), 1, NOW(), 1);

-- 8. estado_venta
INSERT INTO `estado_venta` 
(`IdEstadoVenta`, `Nombre`, `FechaCreacion`, `UsuarioCreacion`, `FechaModif`, `UsuarioModif`) 
VALUES
(1, 'Completada', NOW(), 1, NOW(), 1),
(2, 'Anulada', NOW(), 1, NOW(), 1);

-- 9. estado_pedido
INSERT INTO `estado_pedido` 
(`IdEstadoPedido`, `Nombre`, `FechaCreacion`, `UsuarioCreacion`, `FechaModif`, `UsuarioModif`) 
VALUES
(1, 'Pendiente', NOW(), 1, NOW(), 1),
(2, 'Entregado', NOW(), 1, NOW(), 1),
(3, 'Cancelado', NOW(), 1, NOW(), 1),
(4, 'Entrega Parcial', NOW(), 1, NOW(), 1);

-- 10. sucursal (depende de estado_sucursal)
INSERT INTO `sucursal` 
(`IdSucursal`, `Nombre`, `Direccion`, `Telefono`, `IdEstadoSucursal`, `FechaCreacion`, `UsuarioCreacion`, `FechaModif`, `UsuarioModif`) 
VALUES
(1, 'Sucursal Femaco', 'Direccion 123', '1234-5678', 1, NOW(), 1, NOW(), 1);


-- =====================================================
-- 11. INSERTS PARA SUPER USUARIO
-- =====================================================

-- 1. ROL
INSERT INTO `rol` (`IdRol`, `Nombre`, `FechaCreacion`, `UsuarioCreacion`, `FechaModif`, `UsuarioModif`)
VALUES
(1, 'Super Usuario', NOW(), 1, NOW(), 1);

-- 2. MÓDULOS
INSERT INTO `modulo` (`IdModulo`, `Nombre`, `OrdenMenu`, `FechaCreacion`, `UsuarioCreacion`, `FechaModif`, `UsuarioModif`)
VALUES
(1, 'Seguridad', 1, NOW(), 1, NOW(), 1),
(2, 'Catálogo', 2, NOW(), 1, NOW(), 1),
(3, 'Inventario', 3, NOW(), 1, NOW(), 1),
(4, 'Sucursales y Cotizaciones', 4, NOW(), 1, NOW(), 1),
(5, 'Suministro', 5, NOW(), 1, NOW(), 1),
(6, 'Ventas', 6, NOW(), 1, NOW(), 1);

-- 3. MENÚS
INSERT INTO `menu` (`IdMenu`, `Nombre`, `OrdenMenu`, `IdModulo`, `FechaCreacion`, `UsuarioCreacion`, `FechaModif`, `UsuarioModif`)
VALUES
-- Seguridad
(1, 'Dashboard', 1, 1, NOW(), 1, NOW(), 1),
(2, 'Configuración de Seguridad', 2, 1, NOW(), 1, NOW(), 1),
(3, 'Usuarios y Catálogos Seguridad', 3, 1, NOW(), 1, NOW(), 1),

-- Catálogo
(4, 'Catálogos Generales', 1, 2, NOW(), 1, NOW(), 1),

-- Inventario
(5, 'Artículos e Inventario', 1, 3, NOW(), 1, NOW(), 1),

-- Sucursales y Cotizaciones
(6, 'Sucursales y Cotizaciones', 1, 4, NOW(), 1, NOW(), 1),

-- Suministro
(7, 'Compras y Proveedores', 1, 5, NOW(), 1, NOW(), 1),

-- Ventas
(8, 'Clientes y Ventas', 1, 6, NOW(), 1, NOW(), 1);

-- 4. OPCIONES (Pagina = path exacto )
INSERT INTO `opcion` (`IdOpcion`, `Nombre`, `OrdenMenu`, `Pagina`, `IdMenu`, `FechaCreacion`, `UsuarioCreacion`, `FechaModif`, `UsuarioModif`)
VALUES
-- Dashboard
(1, 'Dashboard', 1, 'dashboard', 1, NOW(), 1, NOW(), 1),

-- Configuración de Seguridad
(2, 'Módulos', 1, 'modulo', 2, NOW(), 1, NOW(), 1),
(3, 'Menús', 2, 'menu', 2, NOW(), 1, NOW(), 1),
(4, 'Opciones', 3, 'opcion', 2, NOW(), 1, NOW(), 1),
(5, 'Roles', 4, 'rol', 2, NOW(), 1, NOW(), 1),
(6, 'Roles - Opciones', 5, 'rol-opcion', 2, NOW(), 1, NOW(), 1),

-- Usuarios y Catálogos Seguridad
(7, 'Usuarios', 1, 'usuario', 3, NOW(), 1, NOW(), 1),
(8, 'Géneros', 2, 'genero', 3, NOW(), 1, NOW(), 1),

-- Catálogo
(9, 'Unidades de Medida', 1, 'unidad-medida', 4, NOW(), 1, NOW(), 1),

-- Inventario
(10, 'Artículos', 1, 'articulo', 5, NOW(), 1, NOW(), 1),
(11, 'Áreas de Artículo', 2, 'area-articulo', 5, NOW(), 1, NOW(), 1),
(12, 'Ajuste de Inventario', 3, 'ajuste-inventario', 5, NOW(), 1, NOW(), 1),
(13, 'Movimientos de Inventario', 4, 'movimiento-inventario', 5, NOW(), 1, NOW(), 1),

-- Sucursales y Cotizaciones
(14, 'Sucursales', 1, 'sucursal', 6, NOW(), 1, NOW(), 1),
(15, 'Sucursal - Artículos', 2, 'sucursal-articulo', 6, NOW(), 1, NOW(), 1),
(16, 'Cotizaciones', 3, 'cotizacion', 6, NOW(), 1, NOW(), 1),

-- Suministro
(17, 'Proveedores', 1, 'proveedor', 7, NOW(), 1, NOW(), 1),
(18, 'Órdenes de Compra', 2, 'orden-compra', 7, NOW(), 1, NOW(), 1),

-- Ventas
(19, 'Clientes', 1, 'cliente', 8, NOW(), 1, NOW(), 1),
(20, 'Pedidos', 2, 'pedidos', 8, NOW(), 1, NOW(), 1),
(21, 'Ventas', 3, 'ventas', 8, NOW(), 1, NOW(), 1);

-- 5. ROL_OPCION (Super Usuario con TODOS los permisos: Alta, Baja y Cambio = 1)
INSERT INTO `rol_opcion` (`IdRol`, `IdOpcion`, `Alta`, `Baja`, `Cambio`, `FechaCreacion`, `UsuarioCreacion`, `FechaModif`, `UsuarioModif`)
VALUES
(1, 1, 1, 1, 1, NOW(), 1, NOW(), 1),  -- Dashboard
(1, 2, 1, 1, 1, NOW(), 1, NOW(), 1),  -- Módulos
(1, 3, 1, 1, 1, NOW(), 1, NOW(), 1),  -- Menús
(1, 4, 1, 1, 1, NOW(), 1, NOW(), 1),  -- Opciones
(1, 5, 1, 1, 1, NOW(), 1, NOW(), 1),  -- Roles
(1, 6, 1, 1, 1, NOW(), 1, NOW(), 1),  -- Roles - Opciones
(1, 7, 1, 1, 1, NOW(), 1, NOW(), 1),  -- Usuarios
(1, 8, 1, 1, 1, NOW(), 1, NOW(), 1),  -- Géneros
(1, 9, 1, 1, 1, NOW(), 1, NOW(), 1),  -- Unidades de Medida
(1, 10, 1, 1, 1, NOW(), 1, NOW(), 1), -- Artículos
(1, 11, 1, 1, 1, NOW(), 1, NOW(), 1), -- Áreas de Artículo
(1, 12, 1, 1, 1, NOW(), 1, NOW(), 1), -- Ajuste de Inventario
(1, 13, 1, 1, 1, NOW(), 1, NOW(), 1), -- Movimientos de Inventario
(1, 14, 1, 1, 1, NOW(), 1, NOW(), 1), -- Sucursales
(1, 15, 1, 1, 1, NOW(), 1, NOW(), 1), -- Sucursal - Artículos
(1, 16, 1, 1, 1, NOW(), 1, NOW(), 1), -- Cotizaciones
(1, 17, 1, 1, 1, NOW(), 1, NOW(), 1), -- Proveedores
(1, 18, 1, 1, 1, NOW(), 1, NOW(), 1), -- Órdenes de Compra
(1, 19, 1, 1, 1, NOW(), 1, NOW(), 1), -- Clientes
(1, 20, 1, 1, 1, NOW(), 1, NOW(), 1), -- Pedidos
(1, 21, 1, 1, 1, NOW(), 1, NOW(), 1); -- Ventas

 -- 12. usuario (Super Administrador) - password: Admin2026+
INSERT INTO `usuario` 
(`IdUsuario`, `Nombre`, `Apellido`, `Password`, `CorreoElectronico`, `RequiereCambioPassword`, 
 `Pregunta`, `Respuesta`, `IdGenero`, `IdEstadoUsuario`, `IdSucursal`, `IdRol`, 
 `FechaCreacion`, `UsuarioCreacion`, `FechaModif`, `UsuarioModif`) 
VALUES
(1, 'Admin', 'Sistema', '$2y$10$8MbTCB/6rl4VfDgfUEMa8OQmZ3PVhnZBtIRm0tj3DYCXXUyAzTi4e', 
 'administrador@femaco.com', 0, NULL, NULL, 1, 1, 1, 1, NOW(), 1, NOW(), 1);
 
 