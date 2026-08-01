package com.femaco.main.Service.Catalogo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.femaco.main.Entity.Catalogo.EstadoPedido;
import com.femaco.main.Repository.Catalogo.EstadoPedidoRepository;

import jakarta.transaction.Transactional;



@Service
public class EstadoPedidoService {

     private final EstadoPedidoRepository estadoPedidoRepository;

    public EstadoPedidoService(EstadoPedidoRepository estadoPedidoRepository) {
        this.estadoPedidoRepository = estadoPedidoRepository;
    }

    public List<EstadoPedido> buscarTodos() {
        return estadoPedidoRepository.findAll();
    }

    @Transactional
    public EstadoPedido crear(EstadoPedido estadoPedido) {
        LocalDateTime ahora = LocalDateTime.now();
        estadoPedido.setIdEstadoPedido(null);
        estadoPedido.setFechaCreacion(ahora);
        estadoPedido.setFechaModif(ahora);
        estadoPedido.setUsuarioModif(estadoPedido.getUsuarioCreacion());
        return estadoPedidoRepository.save(estadoPedido);
    }

    @Transactional
    public Optional<EstadoPedido> actualizar(Long idEstadoPedido, EstadoPedido datosNuevos) {
        return estadoPedidoRepository.findById(idEstadoPedido).map(existente -> {
            existente.setNombre(datosNuevos.getNombre());
            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            // fechaCreacion y usuarioCreacion no se tocan
            return estadoPedidoRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idEstadoPedido) {
        if (!estadoPedidoRepository.existsById(idEstadoPedido)) {
            return false;
        }
        estadoPedidoRepository.deleteById(idEstadoPedido);
        return true;
    }
    
}
