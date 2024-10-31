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
    
    private List<List> items;
    ItemMenuTableModelo(){
        this.items = new ArrayList();
    }
    ItemMenuTableModelo(List<List> items){
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
        return items.get(rowIndex).get(columnIndex);
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
    public void setItems(List<List> items) {
        this.items = items;
    }
    
    
}
