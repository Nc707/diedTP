/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nc.dao.ItemPedidoDAO;
import nc.dao.PedidoDAO;
import nc.dao.jdbc.ItemPedidoJDBC;
import nc.dao.jdbc.PedidoJDBC;
import nc.modelo.Pedido;
import nc.modelo.Pedido.EstadoPedido;

/**
 *
 * @author nicol/*
 */
public class PedidoController {

    private Boolean isVendedor;
    private int ID = 0;
    private ItemPedidoDAO itemsPedido = new ItemPedidoJDBC();
    private PedidoDAO pedidos = new PedidoJDBC();
    private PedidoDAO pedidoDAO;
    private EstadoPedido estado;
   
    public PedidoController(Boolean isVendedor) {
        this.isVendedor = isVendedor;
    }



    public PedidoController(int ID, Boolean isVendedor) {
        this.ID = ID;
        this.isVendedor = isVendedor;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public void setPedidos(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
}
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
}



    // public List<List> loadData(){
    //     ItemPedidoMemory database = ItemPedidoMemory.getItemPedidoMemory();
    //     List<Pedido> data;
    //     try {
    //         //data = itemsPedido.getPedidos(ID, isVendedor);
    //     } catch (ItemNoEncontradoException ex) {return new ArrayList();}
    //     return data.stream().map((Pedido p) -> {
    //         ArrayList list = new ArrayList();
    //         list.add(p.getId());
    //         if(isVendedor)
    //         {list.add(p.getCliente().getNombre());}
    //         else
    //         {  list.add(p.getVendedor().getNombre());}
    //         list.add(p.getItemAmount());
    //         list.add(p.getPrecio());
    //         switch(p.getEstado()){
    //             case EN_CARRITO ->
    //                 list.add("En Carrito");
    //             case RECIBIDO ->
    //                 list.add("Recibido");
    //             case EN_ENVIO ->
    //                 list.add("En envío");
    //             }
    //         return list;
    //     }).collect(Collectors.toList());
    // }
    public List<List> loadData() {

        List<Pedido> data;
        if (isVendedor) {
            data = pedidos.listarPorVendedor(ID);
        } else {
            data = pedidos.listarPorCliente(ID);
        }
        return data.stream().map((Pedido p) -> {
            ArrayList list = new ArrayList();
            list.add(p.getId());
            if (isVendedor) {
                list.add(p.getCliente().getNombre());
            } else {
                list.add(p.getVendedor().getNombre());
            }
            list.add(p.getItemAmount());
            list.add(p.getPrecio());
            switch (p.getEstado()) {
                case EN_CARRITO ->
                    list.add("En Carrito");
                case RECIBIDO ->
                    list.add("Recibido");
                case EN_ENVIO ->
                    list.add("En envío");
            }
            return list;
        }).collect(Collectors.toList());
    }

}
