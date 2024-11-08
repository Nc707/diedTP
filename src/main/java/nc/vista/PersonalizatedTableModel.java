/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.vista;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Modelo de tabla personalizado que extiende AbstractTableModel.
 * Permite manejar una tabla con datos dinámicos.
 */
public class PersonalizatedTableModel extends AbstractTableModel {
    
    private List<List> items; // Lista de listas que contiene los datos de la tabla
    private List<String> colName; // Lista que contiene los nombres de las columnas
    
    /**
     * Constructor del modelo de tabla.
     * @param colName Lista con los nombres de las columnas.
     * @param items Lista de listas con los datos de la tabla.
     */
    public PersonalizatedTableModel(List<String> colName, List<List> items) {
        this.items = items;
        this.colName = colName;
    }

    /**
     * Devuelve el número de filas en la tabla.
     * @return Número de filas.
     */
    @Override
    public int getRowCount() {
        return this.items.size();
    }

    /**
     * Devuelve el número de columnas en la tabla.
     * @return Número de columnas.
     */
    @Override
    public int getColumnCount() {
        return colName.size();
    }

    /**
     * Devuelve el valor en una celda específica.
     * @param rowIndex Índice de la fila.
     * @param columnIndex Índice de la columna.
     * @return Valor en la celda especificada.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex >= items.size() || columnIndex >= colName.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return items.get(rowIndex).get(columnIndex);
    }

    /**
     * Devuelve la clase de la columna especificada.
     * @param col Índice de la columna.
     * @return Clase de la columna.
     */
    @Override
    public Class<?> getColumnClass(int col) {
        if (getRowCount() > 0) {
            return getValueAt(0, col).getClass();
        } else {
            return Object.class;
        }
    }

    /**
     * Devuelve el nombre de la columna especificada.
     * @param columnIndex Índice de la columna.
     * @return Nombre de la columna.
     */
    @Override
    public String getColumnName(int columnIndex) {
        return colName.get(columnIndex);
    }

    /**
     * Actualiza los datos de la tabla.
     * @param items Nueva lista de listas con los datos de la tabla.
     */
    public void setItems(List<List> items) {
        this.items = items;
        fireTableDataChanged(); // Notifica a la tabla que los datos han cambiado
    }

    /**
     * Actualiza los nombres de las columnas.
     * @param names Nueva lista con los nombres de las columnas.
     */
    public void setColumnName(List<String> names) {
        this.colName = names;
        fireTableStructureChanged(); // Notifica a la tabla que la estructura de las columnas ha cambiado
    }

    /**
     * Devuelve un array con los nombres de las columnas.
     * @return Array con los nombres de las columnas.
     */
    public String[] getColumnName() {
        String[] names = new String[getColumnCount() + 1];
        for (int i = 0; i < this.colName.size(); i++) {
            names[i + 1] = this.colName.get(i);
        }
        names[0] = "Tipo de Búsqueda";
        return names;
    }
}