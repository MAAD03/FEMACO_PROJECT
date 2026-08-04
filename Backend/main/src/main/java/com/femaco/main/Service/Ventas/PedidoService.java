package com.femaco.main.Service.Ventas;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Ventas.Pedido;
import com.femaco.main.Repository.Ventas.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }

    @Transactional
    public Pedido crear(Pedido pedido) {
        LocalDateTime ahora = LocalDateTime.now();
        pedido.setIdPedido(null);
        pedido.setFechaCreacion(ahora);
        pedido.setFechaModif(ahora);
        pedido.setUsuarioModif(pedido.getUsuarioCreacion());
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Optional<Pedido> actualizar(Long idPedido, Pedido datosNuevos) {
        return pedidoRepository.findById(idPedido).map(existente -> {
            existente.setFechaEntrega(datosNuevos.getFechaEntrega());
            existente.setDireccionEntrega(datosNuevos.getDireccionEntrega());
            existente.setNotasEntrega(datosNuevos.getNotasEntrega());
            existente.setNumeroEntrega(datosNuevos.getNumeroEntrega());
            existente.setIdVenta(datosNuevos.getIdVenta());
            existente.setIdEstadoPedido(datosNuevos.getIdEstadoPedido());

            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            return pedidoRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idPedido) {
        if (!pedidoRepository.existsById(idPedido)) {
            return false;
        }
        pedidoRepository.deleteById(idPedido);
        return true;
    }
    
}
