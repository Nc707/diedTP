package com.deso.etapa_final.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.deso.etapa_final.model.ItemPedido;
import com.deso.etapa_final.model.Pedido;

public interface ItemPedidoRepository extends CrudRepository<ItemPedido, Long> {
    
    Set<ItemPedido> findByPedido(Pedido pedido);
    void deleteByItemMenu_Itemid(Long id);
}
