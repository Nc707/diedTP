/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.modelo.itemMenuManagement.compareStrategies;

import nc.modelo.ItemMenu;
import nc.modelo.ItemMenuDAOandMemory.CompareItemMenuStrategyInterface;
/**
 *
 * @author Pc
 */
public class PriceComp implements CompareItemMenuStrategyInterface {
    private ItemMenu self;
    private boolean ascendente;
    public PriceComp(ItemMenu self, boolean ascendente) {
        this.self = self;
        this.ascendente = ascendente;
    }
    
    
    @Override
    public int compareTo(ItemMenu otherItem) {
        if(ascendente){
            if(self.getPrecio() > otherItem.getPrecio() )
                return 1;
            else if( self.getPrecio() < otherItem.getPrecio() )
                return -1;
            else
                return 0;
        }else{
            if(self.getPrecio() < otherItem.getPrecio() )
                return 1;
            else if( self.getPrecio() > otherItem.getPrecio() )
                return -1;
            else
                return 0;
        
        }
    }
}