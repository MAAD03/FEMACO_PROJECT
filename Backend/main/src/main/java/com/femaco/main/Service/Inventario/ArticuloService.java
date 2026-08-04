package com.femaco.main.Service.Inventario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Inventario.Articulo;
import com.femaco.main.Repository.Inventario.ArticuloRepository;



@Service
public class ArticuloService {

    private final ArticuloRepository articuloRepository;

    public ArticuloService(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    public List<Articulo> buscarTodos() {
        return articuloRepository.findAll();
    }

    @Transactional
    public Articulo crear(Articulo articulo) {
        LocalDateTime ahora = LocalDateTime.now();
        articulo.setIdArticulo(null);
        articulo.setFechaCreacion(ahora);
        articulo.setFechaModif(ahora);
        articulo.setUsuarioModif(articulo.getUsuarioCreacion());
        return articuloRepository.save(articulo);
    }

    @Transactional
    public Optional<Articulo> actualizar(Long idArticulo, Articulo datosNuevos) {
        return articuloRepository.findById(idArticulo).map(existente -> {
            existente.setCodigo(datosNuevos.getCodigo());
            existente.setNombre(datosNuevos.getNombre());
            existente.setDescripcion(datosNuevos.getDescripcion());
            existente.setStockMinimo(datosNuevos.getStockMinimo());
            existente.setPrecioCompraUltimoProveedor(datosNuevos.getPrecioCompraUltimoProveedor());
            existente.setMargenGanancia(datosNuevos.getMargenGanancia());
            existente.setCantidadMinimaDescuento(datosNuevos.getCantidadMinimaDescuento());
            existente.setDescuentoMayorista(datosNuevos.getDescuentoMayorista());
            existente.setIdAreaArticulo(datosNuevos.getIdAreaArticulo());
            existente.setIdUnidadMedida(datosNuevos.getIdUnidadMedida());
            existente.setIdEstadoArticulo(datosNuevos.getIdEstadoArticulo());

            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            return articuloRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idArticulo) {
        if (!articuloRepository.existsById(idArticulo)) {
            return false;
        }
        articuloRepository.deleteById(idArticulo);
        return true;
    }
    
}
