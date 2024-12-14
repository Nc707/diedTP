package com.deso.etapa_final.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deso.etapa_final.exception.AlreadyExistentCarritoException;
import com.deso.etapa_final.exception.NonExistentCarritoException;
import com.deso.etapa_final.exception.NonExistentException;
import com.deso.etapa_final.exception.NonSettedMetodoPagoException;
import com.deso.etapa_final.model.Cliente;
import com.deso.etapa_final.model.ItemMenu;
import com.deso.etapa_final.model.ItemPedido;
import com.deso.etapa_final.model.Pedido;
import com.deso.etapa_final.model.Vendedor;
import com.deso.etapa_final.model.metodosDePago.EstrategiasDePago;

@Service
public class CarritoService {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VendedorService vendedorService;

    @Autowired
    private ItemMenuService itemMenuService;

    @Autowired
    private EstrategiasDePagoService estrategiasDePagoService;

    public Long obtenerCarrito(Long clienteId) throws NonExistentCarritoException {
        Cliente cliente = clienteService.getClienteById(clienteId);

        Pedido pedido = pedidoService.obtenerPedidoPorClienteYEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        if (pedido == null) throw new NonExistentCarritoException(clienteId);
    

        return pedido.getPedidoid();



    }
    public Long crearCarrito(Long clienteId, Long vendedorId) throws AlreadyExistentCarritoException {
        Cliente cliente = clienteService.getClienteById(clienteId);
        if(pedidoService.obtenerPedidoPorClienteYEstado(cliente,Pedido.EstadoPedido.EN_CARRITO)!=null)
        throw new AlreadyExistentCarritoException(clienteId);
        Vendedor vendedor = vendedorService.getVendedorById(vendedorId);
        Pedido pedido = pedidoService.crearPedido(cliente, vendedor);

        return pedido.getPedidoid();


    }

    public void agregarItem(Long clienteId, Long itemMenuId, int cantidad) throws NonExistentCarritoException, NonExistentException {
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoService.obtenerPedidoPorClienteYEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        ItemMenu item = itemMenuService.obtenerItemMenuPorId(itemMenuId);
        // ItemMenu item = platoService.getPlatoById(itemMenuId);
        // if (item == null) item = bebidaService.getBebidaById(itemMenuId);
        
        if(item==null) throw new NonExistentException("El item no existe");
        if(pedido==null) throw new NonExistentCarritoException(clienteId);
        if(!(pedido.getVendedor().equals(item.getVendedor()))) throw new IllegalArgumentException("El item no pertenece al vendedor del pedido");
        if(cantidad<=0) throw new IllegalArgumentException("La cantidad debe ser mayor a 0");

        ItemPedido itemPedido = new ItemPedido(item, cantidad);


        pedidoService.agregarItem(pedido, itemPedido);
    }

    public void eliminarItem(Long clienteId, Long itemPedidoId) throws NonExistentCarritoException, NonExistentException {
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoService.obtenerPedidoPorClienteYEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        ItemPedido itemPedido = pedidoService.obtenerItemPedidoPorId(itemPedidoId);
        if(pedido == null) throw new NonExistentCarritoException(clienteId);
        if(itemPedido == null) throw new NonExistentException("El item no existe");
        pedidoService.eliminarItem(pedido, itemPedido);
    }

    public void modificarCantidad(Long clienteId, Long itemPedidoId, int cantidad) throws NonExistentCarritoException, NonExistentException {
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoService.obtenerPedidoPorClienteYEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        ItemPedido itemPedido = pedidoService.obtenerItemPedidoPorId(itemPedidoId);
        if(pedido == null) throw new NonExistentCarritoException(clienteId);
        if(cantidad<=0) throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        if(itemPedido == null) throw new NonExistentException("El item no existe");
        pedidoService.modificarCantidad(itemPedido, cantidad);
    }

    public void cancelarPedido(Long clienteId) throws NonExistentCarritoException {
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoService.obtenerPedidoPorClienteYEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        if(pedido == null) throw new NonExistentCarritoException(clienteId);
        pedidoService.cancelarPedido(pedido);
    }

    public void setMercadoPago(Long clienteId, String alias) throws NonExistentCarritoException {
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoService.obtenerPedidoPorClienteYEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        if(pedido == null) throw new NonExistentCarritoException(clienteId);

        EstrategiasDePago metodoDePago = estrategiasDePagoService.guardarMercadoPago(alias);

        pedidoService.setMetodoDePago(pedido, metodoDePago);
        
    }

    public void setTransferencia(Long clienteId, String cbu, long cuit) throws NonExistentCarritoException {
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoService.obtenerPedidoPorClienteYEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        if(pedido == null) throw new NonExistentCarritoException(clienteId);

        EstrategiasDePago metodoDePago = estrategiasDePagoService.guardarTransferencia(cbu, cuit);

        pedidoService.setMetodoDePago(pedido, metodoDePago);
    }

    public void cerrarPedido(Long clienteId) throws NonSettedMetodoPagoException, NonExistentCarritoException {
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoService.obtenerPedidoPorClienteYEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        if(pedido == null) throw new NonExistentCarritoException(clienteId);
        if(pedido.getMetodoDePago() == null) throw new NonSettedMetodoPagoException();
        pedidoService.cerrarPedido(pedido);
        
    }
}
