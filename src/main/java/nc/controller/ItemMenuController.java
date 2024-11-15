/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nc.dao.ItemMenuDAO;
import nc.dao.VendedorDAO;
import nc.dao.jdbc.ItemMenuJDBC;
import nc.dao.jdbc.VendedorJDBC;
import nc.excepciones.ItemNoEncontradoException;
import nc.modelo.Bebida;
import nc.modelo.ItemMenu;
import nc.modelo.Plato;
import nc.modelo.Vendedor;

/*eSTE ES UN COMENTARIO*/
/**
 *
 * @author nicol/*
 */
public class ItemMenuController {

    private int vendedorID = 0;

    public ItemMenuController() {
    }

    public ItemMenuController(int vendedorID) {
        this.vendedorID = vendedorID;
    }
    /*
    0: ID Int
    1: Nombre String
    2: Descripcion String
    3: NombreVendedor String
    4: Precio Float

     */
    ItemMenuDAO itemsMenu = new ItemMenuJDBC();
    VendedorDAO vendedores = new VendedorJDBC();

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
        //ItemMenuDAO database = ItemMenuMemory.getInstancia();
        List<ItemMenu> data = new ArrayList<>();
        //if(vendedorID<0){
        //    data = database.getAll();
        //}else{
        try {
            data = itemsMenu.filtrarPor(ItemMenuDAO.tipoFiltrado.ID_VENDEDOR, this.vendedorID);
        } catch (ItemNoEncontradoException ex) {
            return new ArrayList();
        }//}
        return data.stream().map((ItemMenu item) -> {
            ArrayList list = new ArrayList();
            list.add(item.getId());
            list.add(item.getNombre());
            //    if(vendedorID<0){
            //    list.add(item.getVendedor().getNombre());
            //    }
            list.add(item.getPrecio());
            return list;
        }).collect(Collectors.toList());
    }

    public void crearPlato(String nombre, int idVendedor, float precio, float peso, float calorias) {
        //VendedorDAO baseDatosVendedor = VendedorMemory.getInstancia();
        //ItemMenuMemory database = ItemMenuMemory.getInstancia();
        Vendedor vendedor = vendedores.buscar(idVendedor);
        itemsMenu.add(new Plato(nombre, vendedor, precio, peso, calorias));
        //database.crearPlato(nombre, vendedor, precio, peso, calorias);
    }

    public void crearBebida(String nombre, int idVendedor, float precio, float pes, int grado, float tam) {
        //VendedorDAO baseDatosVendedor = VendedorMemory.getInstancia();
        //ItemMenuMemory database = ItemMenuMemory.getInstancia();
        //Vendedor vendedor = baseDatosVendedor.buscar(idVendedor);
        //database.crearBebida(nombre, vendedor, precio, pes, grado, tam);
        Vendedor vendedor = vendedores.buscar(idVendedor);
        itemsMenu.add(new Bebida(nombre, vendedor, precio, pes, grado, tam));

    }

}
