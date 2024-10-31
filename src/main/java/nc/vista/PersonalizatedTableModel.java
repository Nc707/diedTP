/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.vista;

import java.lang.reflect.Array;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nicol
 */
public class PersonalizatedTableModel extends AbstractTableModel{
    
    private List<List> items;
    private List<String> colName;
    
    PersonalizatedTableModel(List<String> colName, List<List> items){
        this.items = items;
        this.colName = colName;
    }
    @Override
    public int getRowCount() {
       return this.items.size();
    }

    @Override
    public int getColumnCount() {
        return colName.size();
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
        return colName.get(columnIndex);
    }
    public void setItems(List<List> items) {
        this.items = items;
    }
    public void setColumnName(List<String> names){
        this.colName = names;
    }
    public String[] getColumnName(){
        String[] names = new String[getColumnCount()+1];
        for(int i = 0; i<this.colName.size(); i++){
            names[i+1] = this.colName.get(i);
        }
        names[0] = "Tipo de Búsqueda";
        return names;
    }
    
}
