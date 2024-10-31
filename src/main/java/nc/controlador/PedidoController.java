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
import nc.modelo.ItemPedidoManagement.ItemPedidoMemory;
import nc.modelo.Pedido;
import nc.modelo.excepciones.ItemNoEncontradoException;

/**
 *
 * @author nicol/*
 */
public class PedidoController {
    private Boolean isVendedor = true;
    private int ID = -1;
    public PedidoController(){}
    public PedidoController(int ID, Boolean isVendedor){
        this.ID = ID;
        this.isVendedor = isVendedor;
    }
    public List<List> loadData(){
        ItemPedidoMemory database = ItemPedidoMemory.getItemPedidoMemory();
        List<Pedido> data;
        if(ID<0){
            data = database.getPedidos();
        }else{
            try {
                data = database.getPedidos(ID, isVendedor);
            } catch (ItemNoEncontradoException ex) {return new ArrayList();}
        }
        return data.stream().map((Pedido p) -> {
            ArrayList list = new ArrayList();
            list.add(p.getId());
            if(isVendedor)
                list.add(p.getVendedor().getNombre());
            else
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
