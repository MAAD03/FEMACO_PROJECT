
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


CREATE TABLE IF NOT EXISTS `role_opcion` (
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
  `DescuentoAplicado` DECIMAL(12,2) NULL DEFAULT 0.00,
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
  `cantidad_ajuste` DECIMAL(12,2) NOT NULL,
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
  `DescuentoTotal` DECIMAL(12,2) NULL,
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
  `DescuentoAplicado` DECIMAL(12,2) NULL,
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