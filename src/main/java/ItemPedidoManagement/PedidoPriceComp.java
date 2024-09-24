/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ItemPedidoManagement;

import nc.diedtp.ItemPedido;

public class PedidoPriceComp implements CompareStrategyInterface {
    private ItemPedido self;
    
    public PedidoPriceComp(ItemPedido self){
        this.self=self;
    }
    
    @Override
    public int compareTo(ItemPedido otherPedido) {
        if(self.getPedido().getPrecio() > otherPedido.getPedido().getPrecio())
            return 1;
        else if(self.getPedido().getPrecio() < otherPedido.getPedido().getPrecio())
            return -1;
        else return 0;
    }
    
}
