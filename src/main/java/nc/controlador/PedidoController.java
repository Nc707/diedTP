/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nc.modelo.ItemPedidoManagement.ItemPedidoMemory;
import nc.modelo.Pedido;

/**
 *
 * @author nicol/*
 */
public class PedidoController {
    public List<List> loadData(){
        ItemPedidoMemory database = ItemPedidoMemory.getItemPedidoMemory();
        List<Pedido> data = database.getPedidos();
        return data.stream().map((Pedido p) -> {
            ArrayList list = new ArrayList();
            list.add(p.getId());
            list.add(p.getVendedor().getNombre());
            list.add(p.getCliente().getNombre());
            list.add(p.getItemAmount());
            list.add(p.getPrecio());
            switch(p.getEstado()){
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
