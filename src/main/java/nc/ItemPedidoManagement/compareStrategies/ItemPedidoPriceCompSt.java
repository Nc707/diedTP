/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.ItemPedidoManagement.compareStrategies;

import nc.ItemPedidoManagement.CompareStrategyInterface;
import nc.diedtp.ItemPedido;

/**
 *
 * @author nicol
 */
public class ItemPedidoPriceCompSt implements CompareStrategyInterface {
    private ItemPedido self;
    private boolean ascendente;
    public ItemPedidoPriceCompSt(ItemPedido self, boolean ascendente){
        this.self= self;
        this.ascendente = ascendente;
    }
    @Override
    public int compareTo(ItemPedido otherPedido) {
        if(ascendente){
        if(self.getPrecio()>otherPedido.getPrecio())
            return 1;
        else if(self.getPrecio()<otherPedido.getPrecio())
            return -1;
        else
            return 0;
        }
        else{
        if(self.getPrecio()<otherPedido.getPrecio())
            return 1;
        else if(self.getPrecio()>otherPedido.getPrecio())
            return -1;
        else
            return 0;
        }
    }
}
