/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.ItemPedidoManagement.compareStrategies;

import nc.ItemPedidoManagement.CompareStrategyInterface;
import nc.diedtp.ItemPedido;

/**
 *
 * @author Pc
 */
public class ItemMenuPriceComp implements CompareStrategyInterface {
    private ItemPedido self;
    private boolean ascendente;
    public ItemMenuPriceComp(ItemPedido self, boolean ascendente) {
        this.self = self;
        this.ascendente = ascendente;
    }
    
    
    @Override
    public int compareTo(ItemPedido otherItem) {
        if(ascendente){
            if(self.getItemMenu().getPrecio() > otherItem.getItemMenu().getPrecio() )
                return 1;
            else if( self.getItemMenu().getPrecio() < otherItem.getItemMenu().getPrecio() )
                return -1;
            else
                return 0;
        }else{
            if(self.getItemMenu().getPrecio() < otherItem.getItemMenu().getPrecio() )
                return 1;
            else if( self.getItemMenu().getPrecio() > otherItem.getItemMenu().getPrecio() )
                return -1;
            else
                return 0;
        
        }
    }
}