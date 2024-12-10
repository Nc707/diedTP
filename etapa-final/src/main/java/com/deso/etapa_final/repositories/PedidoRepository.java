package com.deso.etapa_final.repositories;

import org.springframework.data.repository.CrudRepository;

import com.deso.etapa_final.model.Cliente;
import com.deso.etapa_final.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {
    Pedido findByClienteAndEstado(Cliente cliente, Pedido.EstadoPedido estado);
}
