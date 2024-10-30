/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.vista;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import nc.modelo.ItemMenu;

/**
 *
 * @author nicol
 */
public class ItemMenuTableModelo extends AbstractTableModel{
    
    private List<ItemMenu> items;
    ItemMenuTableModelo(){
        this.items = new ArrayList();
    }
    ItemMenuTableModelo(List<ItemMenu> items){
        this.items = items;
    }
    @Override
    public int getRowCount() {
       return this.items.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ItemMenu item = items.get(rowIndex);
        if(columnIndex == 0) return item.getId();
        if(columnIndex == 1) return item.getNombre();
        if(columnIndex == 2) return item.getVendedor().getNombre();
        if(columnIndex == 3) return item.getPrecio();
        return null;
    }
    @Override
    public Class getColumnClass(int col){
        return getValueAt(0, col).getClass();
    }
    @Override
    public String getColumnName(int columnIndex) {
        if(columnIndex == 0) return "ID";
        if(columnIndex == 1) return "Nombre";
        if(columnIndex == 2) return "Vendedor";
        if(columnIndex == 3) return "Precio($Dollars)";
        return null;
    }
    public void setItems(List<ItemMenu> items) {
        this.items = items;
    }
    
    
}
