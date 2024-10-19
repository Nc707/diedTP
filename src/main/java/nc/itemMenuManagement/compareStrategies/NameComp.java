/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.itemMenuManagement.compareStrategies;

import nc.diedtp.ItemMenu;
import nc.itemMenuManagement.CompareItemMenuStrategyInterface;


/**
 *
 * @author nicol
 */
public class NameComp implements CompareItemMenuStrategyInterface {
    private ItemMenu self;
    private boolean ascendente;
    public NameComp(ItemMenu self, boolean ascendente){
        this.self= self;
        this.ascendente = ascendente;
    }
    @Override
    public int compareTo(ItemMenu otherMenu) {
        if(ascendente){
         return self.getNombre().compareTo(otherMenu.getNombre());
           
        }
        else{
        return otherMenu.getNombre().compareTo(self.getNombre());
        }
    }
}
