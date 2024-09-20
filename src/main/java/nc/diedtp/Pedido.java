/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;

import java.util.ArrayList;

/**
 *
 * @author jereb
 */
public class Pedido {
    private static int next_id=0;
    private int id;
    private ArrayList <ItemPedido> listaItemsPedido;
    private Cliente cliente;
    
    //cons
    public Pedido(){
    this.id = next_id;
    nect:id ++;}
    public Pedido(ArrayList<ItemPedido> items, Cliente clie){
    this.id = next_id;
    nect:id ++;
    this.listaItemsPedido=items;
    this.cliente=clie;
    }
    public void addItemPedido(ItemPedido item){
        this.listaItemsPedido.add(item);
        
    }
}
