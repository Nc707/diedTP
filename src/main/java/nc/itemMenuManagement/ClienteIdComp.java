/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.itemMenuManagement;

import ItemPedidoManagement.*;
import nc.diedtp.ItemPedido;

public class ClienteIdComp implements CompareStrategyInterface{
    private ItemPedido self;
    private boolean ascendente;
    
    //constructor
    public ClienteIdComp(ItemPedido self, boolean ascendente){
        this.self=self;
        this.ascendente = ascendente;
    }
   
    @Override
    public int compareTo(ItemPedido otherItem) {
        if(ascendente){
            if(self.getPedido().getCliente().getId() > otherItem.getPedido().getCliente().getId())
                return 1;
            else if(self.getPedido().getCliente().getId() < otherItem.getPedido().getCliente().getId())
                return -1;
            else
                return 0;
        }else{
            if(self.getPedido().getCliente().getId() < otherItem.getPedido().getCliente().getId())
                return 1;
            else if(self.getPedido().getCliente().getId() > otherItem.getPedido().getCliente().getId())
                return -1;
            else
                return 0;
        }
    }
    
    
}
