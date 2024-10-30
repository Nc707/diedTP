/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.vista;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.RowSorter;

/**
 *
 * @author nicol
 */
public class ItemMenuRowSorter extends RowSorter<ItemMenuTableModelo> {
    private List<? extends SortKey> keys;
    private JTable tabla;
    public ItemMenuRowSorter(JTable tabla){
        this.tabla = tabla;
        this.keys = new ArrayList<>();
    }
    @Override
    public ItemMenuTableModelo getModel() {
        return (ItemMenuTableModelo)tabla.getModel();
    }

    @Override
    public void toggleSortOrder(int column) {
        
    }

    @Override
    public int convertRowIndexToModel(int index) {
        return index;
    }

    @Override
    public int convertRowIndexToView(int index) {
        return index;
    }

    @Override
    public void setSortKeys(List keys) {
          this.keys = keys;
    }

    @Override
    public List getSortKeys() {
        return keys;
    }

    @Override
    public int getViewRowCount() {
        return getModelRowCount();
    }

    @Override
    public int getModelRowCount() {
        return tabla.getModel().getRowCount();
    }

    @Override
    public void modelStructureChanged() {
        
    }

    @Override
    public void allRowsChanged() {
        
    }

    @Override
    public void rowsInserted(int firstRow, int endRow) {
       
    }

    @Override
    public void rowsDeleted(int firstRow, int endRow) {
       
    }

    @Override
    public void rowsUpdated(int firstRow, int endRow) {
       
    }

    @Override
    public void rowsUpdated(int firstRow, int endRow, int column) {
       
    }
    
}
