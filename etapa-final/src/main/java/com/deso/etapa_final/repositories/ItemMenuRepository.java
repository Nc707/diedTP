package com.deso.etapa_final.repositories;

import org.springframework.data.repository.CrudRepository;

import com.deso.etapa_final.model.ItemMenu;
import com.deso.etapa_final.model.Vendedor;

import java.util.List;

public interface ItemMenuRepository extends CrudRepository<ItemMenu, Long> {
    List<ItemMenu> findByVendedor(Vendedor vendedor);
    void deleteByVendedor_vendedorid(Long id);
}
