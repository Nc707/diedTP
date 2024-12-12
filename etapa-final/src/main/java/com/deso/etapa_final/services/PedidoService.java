package com.deso.etapa_final.services;

import com.deso.etapa_final.event.PedidoEnEnvioEvent;
import com.deso.etapa_final.model.*;
import com.deso.etapa_final.model.interfaces.EstrategiasDePagoInterface;
import com.deso.etapa_final.repositories.PedidoRepository;
import com.deso.etapa_final.repositories.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public Pedido obtenerPedidoPorId(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    public Pedido obtenerPedidoPorClienteYEstado(Cliente cliente, Pedido.EstadoPedido estado) {
        return pedidoRepository.findByClienteAndEstado(cliente, estado);
    }

    public EstrategiasDePagoInterface obtenerMetodoDePagoDePedido(Long pedidoId) {
        Pedido pedido = obtenerPedidoPorId(pedidoId);
        return pedido.getMetodoDePago(); // La deserialización ocurre automáticamente
    }

    public Pedido crearPedido(Cliente cliente, Vendedor vendedor) {
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setVendedor(vendedor);
        pedido.setEstado(Pedido.EstadoPedido.EN_CARRITO);
        return pedidoRepository.save(pedido);
    }

    public void guardarPedido(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    public void eliminarPedido(Pedido pedido) {
        pedidoRepository.delete(pedido);
    }

    public void agregarItem(Pedido pedido, ItemPedido itemPedido) {
        itemPedido.setPedido(pedido);
        itemPedidoRepository.save(itemPedido);
        actualizarPrecio(pedido);
    }

    public void eliminarItem(Pedido pedido, ItemPedido itemPedido) {
        itemPedidoRepository.delete(itemPedido);
        actualizarPrecio(pedido);
    }

    public void modificarCantidad(ItemPedido itemPedido, int cantidad) {
        itemPedido.setCantidad(cantidad);
        itemPedidoRepository.save(itemPedido);
        actualizarPrecio(itemPedido.getPedido());
    }

    public void actualizarPrecio(Pedido pedido) {
        double precio = 0;
        for (ItemPedido itemPedido : itemPedidoRepository.findByPedido(pedido)) {
            precio += itemPedido.getPrecio();
        }
        pedido.setPrecio(precio);
        pedidoRepository.save(pedido);
    }

    public void confirmarPedido(Pedido pedido) {
        pedido.setEstado(Pedido.EstadoPedido.RECIBIDO);
        pedidoRepository.save(pedido);
    }

    public void cancelarPedido(Pedido pedido) {
        pedidoRepository.delete(pedido);
    }

    public void setMetodoDePago(Pedido pedido, EstrategiasDePagoInterface metodoDePago) {
        pedido.setMetodoDePago(metodoDePago);
        pedidoRepository.save(pedido);
    }

    public void cerrarPedido(Pedido pedido) {
        pedido.setEstado(Pedido.EstadoPedido.RECIBIDO);
        pedidoRepository.save(pedido);
    }

    public ItemPedido obtenerItemPedidoPorId(Long itemPedidoId) {
        return itemPedidoRepository.findById(itemPedidoId)
                .orElseThrow(() -> new RuntimeException("ItemPedido no encontrado"));
    }

    public void avanzarEstadoPedido(Long pedidoId) {
        Pedido pedido = obtenerPedidoPorId(pedidoId);
        switch (pedido.getEstado()) {
            case EN_CARRITO:
                pedido.setEstado(Pedido.EstadoPedido.RECIBIDO);
                break;
            case RECIBIDO:
                pedido.setEstado(Pedido.EstadoPedido.EN_ENVIO);
                eventPublisher.publishEvent(new PedidoEnEnvioEvent(this, pedido));
                break;
            case EN_ENVIO:
                pedido.setEstado(Pedido.EstadoPedido.ENTREGADO);
                break;
            case ENTREGADO:
                throw new IllegalStateException("El pedido ya ha sido entregado");
        }
        pedidoRepository.save(pedido);
    }
}
