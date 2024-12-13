package com.deso.etapa_final.services;

import com.deso.etapa_final.event.PedidoEnEnvioEvent;
import com.deso.etapa_final.model.*;
import com.deso.etapa_final.model.interfaces.EstrategiasDePagoInterface;
import com.deso.etapa_final.repositories.PedidoRepository;
import com.deso.etapa_final.repositories.ItemPedidoRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    public Iterable<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }
    public Iterable<Pedido> generalSearch(String searchable, String orderBy, String orderDirection){
        if(orderBy.equals("precio_mayor_que") || orderBy.equals("precio_menor_que")){
            try{
            Double precio = Double.parseDouble(searchable);
            List <Pedido> resultList = new ArrayList<>();
            if(orderBy.equals("precio_menor_que"))
                resultList.addAll(pedidoRepository.findByPrecioLessThanEqual(precio));
            else
                resultList.addAll(pedidoRepository.findByPrecioGreaterThanEqual(precio));
            resultList.sort((p1, p2) -> {
                int comparison = 0;
                if (p1.getPrecio() > p2.getPrecio()) {
                    comparison = 1;
                } else if (p1.getPrecio() < p2.getPrecio()) {
                    comparison = -1;
                }
                return comparison;
            });
            return resultList;
        }catch(NumberFormatException e){
            return new ArrayList<>();
        }
        }else if(orderBy.equals("cantidad_mayor_que") || orderBy.equals("cantidad_menor_que")){
            try{
            int cantidad = Integer.parseInt(searchable);
            List <Pedido> resultList = new ArrayList<>();
            if(orderBy.equals("cantidad_menor_que"))
                resultList.addAll(pedidoRepository.findByCantidadLessThanEqual(cantidad));
            else
                resultList.addAll(pedidoRepository.findByCantidadGreaterThanEqual(cantidad));
            resultList.sort((p1, p2) -> {
                int comparison = 0;
                if (p1.getCantidad() > p2.getCantidad()) {
                    comparison = 1;
                } else if (p1.getCantidad() < p2.getCantidad()) {
                    comparison = -1;
                }
                return comparison;
            });
            return resultList;
        }catch(NumberFormatException e){
            return new ArrayList<>();
        }
    }
    Set<Pedido> resultSet = new HashSet<>();
    List<Pedido> byDescripcion = pedidoRepository.findByDescripcionContaining(searchable);
    resultSet.addAll(byDescripcion);
    List<Pedido> byEstado = pedidoRepository.findByEstado(Pedido.EstadoPedido.valueOf(searchable));
    resultSet.addAll(byEstado);
    List<Pedido> byTipoMetodoDePago = pedidoRepository.findByTipoMetodoDePago(searchable);
    resultSet.addAll(byTipoMetodoDePago);
    List<Pedido> byClienteNombre = pedidoRepository.findByCliente_nombre(searchable);
    resultSet.addAll(byClienteNombre);
    List<Pedido> byVendedorNombre = pedidoRepository.findByVendedor_nombre(searchable);
    resultSet.addAll(byVendedorNombre);
    List<Pedido> byItemsMenuNombre = pedidoRepository.findByItems_ItemMenu_nombre(searchable);
    resultSet.addAll(byItemsMenuNombre);
    Long longValue = null;
    try {
        longValue = Long.parseLong(searchable);
        Pedido byId = pedidoRepository.findById(longValue).get();
        if(byId != null) resultSet.add(byId);
    } catch (NumberFormatException e) {}

    List<Pedido> resultList = new ArrayList<>(resultSet);
    resultList.sort((p1, p2) -> {
        int comparison = 0;
        switch (orderBy) {
            case "id":
                comparison = p1.getPedidoid().compareTo(p2.getPedidoid());
                break;
            case "precio":
                comparison = Double.compare(p1.getPrecio(), p2.getPrecio());
                break;
            case "precio_mayor_que":
            comparison = Double.compare(p1.getPrecio(), p2.getPrecio());
            break;
            case "precio_menor_que":
            comparison = Double.compare(p1.getPrecio(), p2.getPrecio());
            break;
            case "cantidad":
                comparison = Integer.compare(p1.getCantidad(), p2.getCantidad());
                break;
            case "cantidad_menor_que":
                comparison = Integer.compare(p1.getCantidad(), p2.getCantidad());
                break;
            case "cantidad_mayor_que":
            comparison = Integer.compare(p1.getCantidad(), p2.getCantidad());
            break;
            case "estado":
                comparison = p1.getEstado().compareTo(p2.getEstado());
                break;
            default:
                comparison = p1.getPedidoid().compareTo(p2.getPedidoid());
                break;
        }
        return orderDirection.equals("ASC") ? comparison : -comparison;
    });
    return resultList;
    }

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
