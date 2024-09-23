/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;

/**
 *
 * @author nicol
 */
public class ItemPedidoPriceCompSt implements CompareStrategyInterface {
    private ItemPedido self;
    ItemPedidoPriceCompSt(ItemPedido self){
        this.self= self;
        
    }
    @Override
    public int compareTo(ItemPedido otherPedido) {
        if(self.getPrecio()>otherPedido.getPrecio())
            return 1;
        else if(self.getPrecio()<otherPedido.getPrecio())
            return -1;
        else
            return 0;
    }
    
}
