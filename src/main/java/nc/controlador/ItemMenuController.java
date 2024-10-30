/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.controlador;

import java.util.List;
import nc.modelo.ItemMenu;
import nc.modelo.ItemMenuDAOandMemory.ItemMenuDAO;

/**
 *
 * @author nicol/*
 */
public class ItemMenuController {
    private ItemMenuDAO database;
    public ItemMenuController(ItemMenuDAO database){
        this.database = database;
    }
    
    public List<ItemMenu> loadData(){
        return database.getAll();
    }
    
}
