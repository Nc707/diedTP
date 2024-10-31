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
import nc.modelo.ItemPedido;
import nc.modelo.ItemPedidoManagement.ItemPedidoDAO;
import nc.modelo.ItemPedidoManagement.ItemPedidoMemory;
import nc.modelo.excepciones.ItemNoEncontradoException;

/**
 *
 * @author nicol/*
 */
public class ItemPedidoController {
    private int pedidoID = -1;
    public ItemPedidoController(){}
    public ItemPedidoController(int pedidoID){
        this.pedidoID = pedidoID;
    }
    public List<List> loadData(){
        ItemPedidoMemory database = ItemPedidoMemory.getItemPedidoMemory();
        List<ItemPedido> data = new ArrayList<>();
        if(pedidoID<0){
            data = database.getAll();
        }else{
            try{
            data = database.filtrarPor(ItemPedidoDAO.tipoFiltrado.ID_PEDIDO, pedidoID);
            } catch (ItemNoEncontradoException ex) {return new ArrayList();}}
        return data.stream().map((ItemPedido item) -> {
            ArrayList list = new ArrayList();
            list.add(item.getId());
            list.add(item.getItemMenu().getNombre());
            if(pedidoID<0){
            list.add(item.getVendedor().getNombre());
            }
            list.add(item.getPrecio());
            return list;
        }).collect(Collectors.toList());
    }
    
}
afsdfas