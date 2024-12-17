package com.deso.etapa_final.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.deso.etapa_final.model.Cliente;
import com.deso.etapa_final.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {
    Pedido findByClienteAndEstado(Cliente cliente, Pedido.EstadoPedido estado);
    List<Pedido> findByDescripcionContaining(String descripcion);
    List<Pedido> findByPrecioGreaterThanEqual(Double precio);
    List<Pedido> findByPrecioLessThanEqual(Double precio);
    List<Pedido> findByPrecioBetween(Double precio1, Double precio2);
    List<Pedido> findByCantidadGreaterThanEqual(int cantidad);
    List<Pedido> findByCantidadLessThanEqual(int cantidad);
    List<Pedido> findByEstado(Pedido.EstadoPedido estado);
    List<Pedido> findByVendedor_vendedorid(Long vendedorId);
    List<Pedido> findByCliente_clienteid(Long clienteId);
    List<Pedido> findByCliente_nombreContaining(String clienteNombre);
    List<Pedido> findByItems_ItemMenu_itemid(Long itemMenuId);
    List<Pedido> findByItems_ItemMenu_nombreContaining(String itemMenuNombre);
    List<Pedido> findByVendedor_nombreContaining(String vendedorNombre);
    void deleteByVendedor_vendedorid(Long vendedorId);
}
