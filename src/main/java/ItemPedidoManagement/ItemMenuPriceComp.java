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
public class ItemMenuPriceComp implements CompareStrategyInterface {
    private ItemPedido self;

    public ItemMenuPriceComp(ItemPedido self) {
        this.self = self;
    }
    
    
    @Override
    public int compareTo(ItemPedido otherItem) {
        if(self.getItemMenu().getPrecio() > otherItem.getItemMenu().getPrecio() )
            return 1;
        else if( self.getItemMenu().getPrecio() < otherItem.getItemMenu().getPrecio() )
            return -1;
        else
            return 0;
    }
}