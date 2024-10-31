/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nc.modelo.ItemMenu;
import nc.modelo.ItemMenuDAOandMemory.ItemMenuDAO;
import nc.modelo.ItemMenuDAOandMemory.ItemMenuMemory;

/**
 *
 * @author nicol/*
 */
public class ItemMenuController {
    public List<List> loadData(){
        ItemMenuDAO database = ItemMenuMemory.getInstancia();
        List<ItemMenu> data = database.getAll();
        return data.stream().map((ItemMenu item) -> {
            ArrayList list = new ArrayList();
            list.add(item.getId());
            list.add(item.getNombre());
            list.add(item.getVendedor().getNombre());
            list.add(item.getPrecio());
            return list;
        }).collect(Collectors.toList());
    }
    
}
