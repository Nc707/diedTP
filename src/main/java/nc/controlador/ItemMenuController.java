/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import nc.modelo.ItemMenu;
import nc.modelo.ItemMenuDAOandMemory.ItemMenuDAO;
import nc.modelo.ItemMenuDAOandMemory.ItemMenuMemory;
import nc.modelo.excepciones.ItemNoEncontradoException;

/**
 *
 * @author nicol/*
 */
public class ItemMenuController {
    private int vendedorID = -1;
    public ItemMenuController(){}
    public ItemMenuController(int vendedorID){
        this.vendedorID = vendedorID;
    }
    public void setID(int vendedorID){
        this.vendedorID = vendedorID;
    }
    public List<List> loadData(){
        ItemMenuDAO database = ItemMenuMemory.getInstancia();
        List<ItemMenu> data = new ArrayList<>();
        if(vendedorID<0){
            data = database.getAll();
        }else{
            try{
            data = database.filtrarPor(ItemMenuDAO.tipoFiltrado.ID_VENDEDOR, this.vendedorID);
            } catch (ItemNoEncontradoException ex) {return new ArrayList();}}
        return data.stream().map((ItemMenu item) -> {
            ArrayList list = new ArrayList();
            list.add(item.getId());
            list.add(item.getNombre());
            if(vendedorID<0){
            list.add(item.getVendedor().getNombre());
            }
            list.add(item.getPrecio());
            return list;
        }).collect(Collectors.toList());
    }
    
}
