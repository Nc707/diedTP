/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import nc.dao.ItemMenuDAO;
import nc.dao.VendedorDAO;
import nc.dao.memory.ItemMenuMemory;
import nc.dao.memory.VendedorMemory;
import nc.excepciones.ItemNoEncontradoException;
import nc.modelo.Bebida;
import nc.modelo.ItemMenu;
import nc.modelo.Plato;
import nc.modelo.Vendedor;
public class ItemMenuController {

    private int vendedorID = 0;

    public ItemMenuController() {
    }

    public ItemMenuController(int vendedorID) {
        this.vendedorID = vendedorID;
    }

    ItemMenuDAO itemsMenu = ItemMenuMemory.getInstancia();
    VendedorDAO vendedores = VendedorMemory.getInstancia();

    public List getItemMenu(int ID) throws ItemNoEncontradoException {
        List itemMenuData = new ArrayList();
        ItemMenu item = itemsMenu.getItem(ID);
        itemMenuData.add(ID);
        itemMenuData.add(item.getNombre());
        itemMenuData.add(item.getDescripcion());
        itemMenuData.add(item.getVendedor().getNombre());
        itemMenuData.add(item.getPrecio());
        return itemMenuData;
    }

    public ItemMenu getObjetItemMenu(int id) throws ItemNoEncontradoException {
        ItemMenu item = itemsMenu.getItem(id);
        return item;
    }

    public void setID(int vendedorID) {
        this.vendedorID = vendedorID;
    }

    public List<List> loadData() {
        
        List<ItemMenu> data = new ArrayList<>();
      
        try {
            data = itemsMenu.filtrarPor(ItemMenuDAO.tipoFiltrado.ID_VENDEDOR, this.vendedorID);
        } catch (ItemNoEncontradoException ex) {
            return new ArrayList();
        }
        return data.stream().map((ItemMenu item) -> {
            ArrayList list = new ArrayList();
            list.add(item.getId());
            list.add(item.getNombre());
           
            list.add(item.getPrecio());
            return list;
        }).collect(Collectors.toList());
    }

    public void crearPlato(String nombre, int idVendedor, float precio, float peso, float calorias) {
       
        Vendedor vendedor = vendedores.buscar(idVendedor);
        itemsMenu.add(new Plato(nombre, vendedor, precio, peso, calorias));

    }

    public void crearBebida(String nombre, int idVendedor, float precio, float pes, int grado, float tam) {
      
        Vendedor vendedor = vendedores.buscar(idVendedor);
        itemsMenu.add(new Bebida(nombre, vendedor, precio, pes, grado, tam));

    }
     public void editarItemMenu(int ID, String nombre, float precio, String descripcion) throws ItemNoEncontradoException{
        ItemMenu editar = itemsMenu.getItem(ID);
        editar.setDescripcion(descripcion);
        editar.setNombre(nombre);
        editar.setPrecio(precio);
        itemsMenu.update(ID, editar);
    }
     public void eliminar(int id){
        try {
            this.itemsMenu.delete(id);
        } catch (ItemNoEncontradoException ex) {
            Logger.getLogger(ItemMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

}
