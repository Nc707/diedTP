/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.util.compareStategies.itemPedido.implementations;
 
import nc.modelo.ItemPedido;
import nc.util.compareStrategies.itemPedido.CompareItemPedidoStrategyInterface;
/**
 *
 * @author Pc
 */
public class PedidoIdComp implements CompareItemPedidoStrategyInterface{
    private ItemPedido self;
    private boolean ascendente;
    
    //Constructor
    public PedidoIdComp(ItemPedido self, boolean ascendente){
        this.ascendente = ascendente;
        this.self=self;
    }
    
    
    @Override
    public int compareTo(ItemPedido otherPedido) {
        if(ascendente){
            if(self.getPedido().getId() > otherPedido.getPedido().getId()){
                return 1;
            }else if(self.getPedido().getId() < otherPedido.getPedido().getId()){
                return -1;
            }
        }else{
            if(self.getPedido().getId() < otherPedido.getPedido().getId()){
                return 1;
            }else if(self.getPedido().getId() > otherPedido.getPedido().getId()){
                return -1;
            }
        }
        return 0;
    }
}
