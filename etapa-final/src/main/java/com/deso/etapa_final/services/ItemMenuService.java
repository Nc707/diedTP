package com.deso.etapa_final.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deso.etapa_final.model.Vendedor;
import com.deso.etapa_final.model.ItemMenu;
import com.deso.etapa_final.repositories.ItemMenuRepository;

@Service
public class ItemMenuService {
    @Autowired
    private ItemMenuRepository itemMenuRepository;
    @Autowired
    private BebidaService bebidaService;
    @Autowired
    private PlatoService platoService;
    
    public List<ItemMenu> obtenerItemsMenuPorVendedor(Vendedor vendedor) {
        return itemMenuRepository.findByVendedor(vendedor);
    }

    public void agregarItemMenu(ItemMenu itemMenu) {
        itemMenuRepository.save(itemMenu);
    }

    public void eliminarItemMenu(Long id) {
        itemMenuRepository.deleteById(id);
    }

    public ItemMenu obtenerItemMenuPorId(Long id) {
        return itemMenuRepository.findById(id).orElse(null);
    }

    public Iterable<ItemMenu> obtenerTodosLosItemsMenu() {
        return itemMenuRepository.findAll();
    }

    public void actualizarItemMenu(ItemMenu itemMenu) {
        itemMenuRepository.save(itemMenu);
    }


}