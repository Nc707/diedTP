/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.modelo;

import nc.util.compareStategies.itemPedido.implementations.ItemPedidoPriceCompSt;
import nc.util.compareStrategies.itemPedido.CompareItemPedidoStrategyInterface;


/**
 *
 * @author jereb
 */
public class ItemPedido implements Comparable {
    private static int next_id = 0;
    private final int id;
    private int cantidad;
    private float precio;
    private ItemMenu itemMenu;
    private Pedido pedido;
    
    private CompareItemPedidoStrategyInterface compareStrategy;
    
    //cons
    public ItemPedido(ItemMenu item,int cant, Pedido pedido){
        this.cantidad=cant;
        this.id=next_id;
        next_id ++;
        this.itemMenu=item;
        this.precio = item.getPrecio() * cant;
        this.pedido= pedido;
        this.compareStrategy = new ItemPedidoPriceCompSt(this, true);
    }
    //gets
    public ItemMenu getItemMenu(){
        return itemMenu;
    }
    public int getCantidad(){
        return cantidad;
    }
    public Vendedor getVendedor(){
        return itemMenu.getVendedor();
    }
    public int getId(){
        return id;
    }
    public Pedido getPedido(){
        return pedido;
    }
    public float getPrecio(){
        return precio;
    }
    public void setStrategy(CompareItemPedidoStrategyInterface cmp){
        this.compareStrategy = cmp;
    }
    public void modificarCantidad(int nuevaCantidad){
        float precioNuevo = nuevaCantidad * this.itemMenu.getPrecio();
        this.pedido.updatePrecio(this.precio, precioNuevo);
        this.precio = precioNuevo;
        this.cantidad = nuevaCantidad;
        
    }
    @Override
    public String toString(){
        return  ("Item: " + itemMenu.toString() + " ,Cantidad: " + cantidad + " ,Coste:" + precio + ", PedidoID: " + pedido.getId());
    }
    @Override
    public int compareTo(Object o) {
        return this.compareStrategy.compareTo((ItemPedido)o);
    }
}

