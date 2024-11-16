/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nc.modelo.ItemPedido;
import nc.dao.ItemPedidoDAO;
import nc.dao.jdbc.ItemPedidoJDBC;
//import nc.dao.memory.ItemPedidoMemory;
import nc.excepciones.ItemNoEncontradoException;

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
    private ItemPedidoDAO itemsPedido = new ItemPedidoJDBC();

    public List getItemPedido(int ID) throws ItemNoEncontradoException{
        List itemMenuData = new ArrayList();
        ItemPedido item = itemsPedido.filtrarPor(ItemPedidoDAO.tipoFiltrado.ID_ITEMPEDIDO, ID).getFirst();
        itemMenuData.add(ID);
        itemMenuData.add(item.getItemMenu().getNombre());
        itemMenuData.add(item.getCantidad());
        itemMenuData.add((Float)item.getPrecio());
        return itemMenuData;
    }
    public List<List> loadData(){
       // ItemPedidoMemory database = ItemPedidoMemory.getItemPedidoMemory();
        List<ItemPedido> data = new ArrayList<>();
        if(pedidoID<0){
            data = itemsPedido.getAll();
        }else{
        try{
        data = itemsPedido.filtrarPor(ItemPedidoDAO.tipoFiltrado.ID_PEDIDO, pedidoID);
            } catch (ItemNoEncontradoException ex) {return new ArrayList();}}
        return data.stream().map((ItemPedido item) -> {
            ArrayList list = new ArrayList();
            list.add(item.getId());
            list.add(item.getItemMenu().getNombre());
            list.add(item.getItemMenu().getPrecio());
            if(pedidoID<0){
            list.add(item.getPedido().getId());
            }
            list.add(item.getCantidad());
            list.add(item.getPrecio());
            return list;
        }).collect(Collectors.toList());
    }
    public void setID(int pedidoId){
        this.pedidoID = pedidoId;
    }
}
