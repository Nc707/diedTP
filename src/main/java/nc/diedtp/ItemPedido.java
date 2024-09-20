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
    private int id;
    private int cantidad;
    private ItemMenu itemMenu;
    
    //cons
    public ItemPedido(int cant, ItemMenu item){
        this.cantidad=cant;
        this.id=next_id;
        next_id ++;
        this.itemMenu=item;
    }
    public ItemPedido(){}
    public ItemMenu getItemMenu(){
        return itemMenu;
    }
}

