/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ItemPedidoManagement;
 
import nc.diedtp.ItemPedido;
/**
 *
 * @author Pc
 */
public class PedidoIdComp implements CompareStrategyInterface{
    private ItemPedido self;
    //Constructor
    public PedidoIdComp(ItemPedido self){
        this.self=self;
    }
    
    
    @Override
    public int compareTo(ItemPedido otherPedido) {
        if(self.getPedido().getId() > otherPedido.getPedido().getId()){
            return 1;
        }else if(self.getPedido().getId() < otherPedido.getPedido().getId()){
            return -1;
        }
        return 0;
    }
}
