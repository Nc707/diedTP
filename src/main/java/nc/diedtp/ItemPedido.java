/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;

/**
 *
 * @author jereb
 */
public class ItemPedido {
    private static int next_id = 0;
    private final int id;
    private int cantidad;
    private ItemMenu itemMenu;
    private float precio;
    //cons
    public ItemPedido(ItemMenu item,int cant){
        this.cantidad=cant;
        this.id=next_id;
        next_id ++;
        this.itemMenu=item;
        this.precio = item.getPrecio() * cant;
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
    @Override
    public String toString(){
        return  ("Item: " + itemMenu.toString() + " ,Cantidad: " + cantidad + " ,Coste:" + precio);
    }
    public float getPrecio(){
        return precio;
    }
}

