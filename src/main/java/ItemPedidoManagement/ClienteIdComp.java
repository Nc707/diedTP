/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ItemPedidoManagement;

import nc.diedtp.ItemPedido;

public class ClienteIdComp implements CompareStrategyInterface{
    private ItemPedido self;
    
    //constructor
    public ClienteIdComp(ItemPedido self){
        this.self=self;
    }
   
    @Override
    public int compareTo(ItemPedido otherItem) {
        if(self.getPedido().getCliente().getId() > otherItem.getPedido().getCliente().getId())
            return 1;
        else if(self.getPedido().getCliente().getId() < otherItem.getPedido().getCliente().getId())
            return -1;
        else
            return 0;
    }
    
    
}
