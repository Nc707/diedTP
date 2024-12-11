package com.deso.etapa_final.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deso.etapa_final.model.Cliente;
import com.deso.etapa_final.model.ItemMenu;
import com.deso.etapa_final.model.ItemPedido;
import com.deso.etapa_final.model.Pedido;
import com.deso.etapa_final.model.Vendedor;
import com.deso.etapa_final.model.interfaces.EstrategiasDePagoInterface;
import com.deso.etapa_final.model.metodosDePago.PagoMercadoPago;
import com.deso.etapa_final.model.metodosDePago.PagoTransferencia;

@Service
public class CarritoService {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VendedorService vendedorService;

    @Autowired
    private PlatoService platoService;

    @Autowired
    private BebidaService bebidaService;

    public Long obtenerCarrito(Long clienteId, Long vendedorId) {
        Cliente cliente = clienteService.getClienteById(clienteId);
        Vendedor vendedor = vendedorService.getVendedorById(vendedorId);

        Pedido pedido = pedidoService.obtenerPedidoPorClienteYEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        if (pedido == null) {
            pedido = pedidoService.crearPedido(cliente, vendedor);
        }
        return pedido.getPedido_id();

    }

    public void agregarItem(Long clienteId, Long itemMenuId, int cantidad) {
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoService.obtenerPedidoPorClienteYEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        ItemMenu item = platoService.getPlatoById(itemMenuId);
        if (item == null) {
            item = bebidaService.getBebidaById(itemMenuId);
        }
        if (pedido != null && item != null && cantidad > 0) {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setCantidad(cantidad);
            itemPedido.setItemMenu(item);
            pedidoService.agregarItem(pedido, itemPedido);
        }
    }

    public void eliminarItem(Long clienteId, Long itemPedidoId) {
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoService.obtenerPedidoPorClienteYEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        ItemPedido itemPedido = pedidoService.obtenerItemPedidoPorId(itemPedidoId);
        if (pedido != null && itemPedido != null) {
            pedidoService.eliminarItem(pedido, itemPedido);
        }
    }

    public void modificarCantidad(Long clienteId, Long itemPedidoId, int cantidad) {
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoService.obtenerPedidoPorClienteYEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        ItemPedido itemPedido = pedidoService.obtenerItemPedidoPorId(itemPedidoId);
        if (pedido != null && itemPedido != null && cantidad > 0) {
            pedidoService.modificarCantidad(itemPedido, cantidad);
        }
    }

    public void confirmarPedido(Long clienteId) {
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoService.obtenerPedidoPorClienteYEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        if (pedido != null) {
            pedidoService.confirmarPedido(pedido);
        }
    }

    public void cancelarPedido(Long clienteId) {
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoService.obtenerPedidoPorClienteYEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        if (pedido != null) {
            pedidoService.cancelarPedido(pedido);
        }
    }

    public void setMercadoPago(Long clienteId, String alias) {
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoService.obtenerPedidoPorClienteYEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        if (pedido != null) {
            EstrategiasDePagoInterface metodoDePago = new PagoMercadoPago(alias);
            pedidoService.setMetodoDePago(pedido, metodoDePago);
        }
    }

    public void setTransferencia(Long clienteId, String cbu, long cuit) {
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoService.obtenerPedidoPorClienteYEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        if (pedido != null) {
            EstrategiasDePagoInterface metodoDePago = new PagoTransferencia(cbu, cuit);
            pedidoService.setMetodoDePago(pedido, metodoDePago);
        }
    }

    public void cerrarPedido(Long clienteId) {
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoService.obtenerPedidoPorClienteYEstado(cliente, Pedido.EstadoPedido.RECIBIDO);
        if (pedido != null && pedido.getMetodoDePago() != null) {
            pedidoService.cerrarPedido(pedido);
        }
    }
}
