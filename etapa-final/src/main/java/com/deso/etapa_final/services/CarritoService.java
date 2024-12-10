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
import com.deso.etapa_final.repositories.ItemPedidoRepository;
import com.deso.etapa_final.repositories.PedidoRepository;

@Service
public class CarritoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VendedorService vendedorService;

    @Autowired
    private PlatoService PlatoService;

    @Autowired
    private BebidaService bebidaService;

    public Long obtenerCarrito(Long clienteId, Long vendedorId){
        Cliente cliente = clienteService.getClienteById(clienteId);
        Vendedor vendedor = vendedorService.getVendedorById(vendedorId);

        Pedido pedido = pedidoRepository.findByClienteAndEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        if(pedido == null){
            pedido = new Pedido();
            pedido.setCliente(cliente);
            pedido.setVendedor(vendedor);
            pedido.setEstado(Pedido.EstadoPedido.EN_CARRITO);
            pedidoRepository.save(pedido);
        }
        return pedido.getId();
        
    }

    public void agregarItem(Long clienteId, Long itemMenuId, int cantidad){
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoRepository.findById(obtenerCarrito(clienteId, itemMenuId)).orElse(null);
        ItemMenu item = PlatoService.getPlatoById(itemMenuId);
        if(item == null){
            item = bebidaService.getBebidaById(itemMenuId);
        }
        if(pedido != null && item != null && cantidad > 0){
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setCantidad(cantidad);
            itemPedido.setItemMenu(item);
            itemPedido.setPedido(pedido);
            actualizarPrecio(pedido);
            itemPedidoRepository.save(itemPedido);
        }
    }

    public void eliminarItem(Long clienteId, Long itemPedidoId){
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoRepository.findByClienteAndEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        ItemPedido itemPedido = itemPedidoRepository.findById(itemPedidoId).orElse(null);
        if(pedido != null && itemPedido != null){
            itemPedidoRepository.delete(itemPedido);
            actualizarPrecio(pedido);
        }
    }

    public void modificarCantidad(Long clienteId, Long itemPedidoId, int cantidad){
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoRepository.findByClienteAndEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        ItemPedido itemPedido = itemPedidoRepository.findById(itemPedidoId).orElse(null);
        if(pedido != null && itemPedido != null && cantidad > 0){
            itemPedido.setCantidad(cantidad);
            itemPedidoRepository.save(itemPedido);
            actualizarPrecio(pedido);
        }
    }

    public void confirmarPedido(Long clienteId){
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoRepository.findByClienteAndEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        if(pedido != null){
            pedido.setEstado(Pedido.EstadoPedido.RECIBIDO);
            pedidoRepository.save(pedido);
        }
    }

    public void cancelarPedido(Long clienteId){
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoRepository.findByClienteAndEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        if(pedido != null){
            pedidoRepository.delete(pedido);
        }
    }

    public void setMercadoPago(Long clienteId,  String alias){
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoRepository.findByClienteAndEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        if(pedido != null){
            EstrategiasDePagoInterface metodoDePago = new PagoMercadoPago(alias);
            pedido.setMetodoDePago(metodoDePago);
            pedidoRepository.save(pedido);
        }
    }

    public void setTransferencia(Long clienteId, String cbu, long cuit){
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoRepository.findByClienteAndEstado(cliente, Pedido.EstadoPedido.EN_CARRITO);
        if(pedido != null){
            EstrategiasDePagoInterface metodoDePago = new PagoTransferencia(cbu, cuit);
            pedido.setMetodoDePago(metodoDePago);
            pedidoRepository.save(pedido);
        }
    }

    private void actualizarPrecio(Pedido pedido){
        double precio = 0;
        for(ItemPedido itemPedido : itemPedidoRepository.findByPedido(pedido)){
            precio += itemPedido.getPrecio();
        }
        pedido.setPrecio(precio);
        pedidoRepository.save(pedido);
    }

    public void cerrarPedido(Long clienteId){
        Cliente cliente = clienteService.getClienteById(clienteId);
        Pedido pedido = pedidoRepository.findByClienteAndEstado(cliente, Pedido.EstadoPedido.RECIBIDO);
        if(pedido != null && pedido.getMetodoDePago() != null){
            pedido.setEstado(Pedido.EstadoPedido.RECIBIDO);
            pedidoRepository.save(pedido);
            pedido.notifyObservers();
        }
    }

 }   

