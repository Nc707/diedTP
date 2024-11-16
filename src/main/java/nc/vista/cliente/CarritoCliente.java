 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package nc.vista.cliente;

import java.awt.Dialog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import nc.controller.ClientController;
import nc.vista.PersonalizatedTableModel;
import nc.vista.cliente.dialog.CerrarPedido;

/**
 *
 * @author nicol
 */
public class CarritoCliente extends javax.swing.JPanel {
    private enum filterMode{
        ID,
        NAME,
        PRICE,
        INDIVIDUAL_PRICE,
        ID_PEDIDO,
        CANTIDAD,
        NONE
    }
    private PersonalizatedTableModel modeloItemsPedido;
    private ClientController carrito;
    private TableRowSorter<PersonalizatedTableModel> sorter;
    private filterMode actualFilter;
    private int filterID = 0;
    int ID_Seleccionado = -1;
    private int ClientID = -1;
    public ClientesFrame frameSuperior;
    /**
     * Creates new form ItemMenuPanel
     */
    public CarritoCliente() {
        carrito = new ClientController();
        List<String> modeloTableName;
        modeloTableName = Arrays.asList("ID", "Item", "Precio Individual", "Cantidad", "Precio Final");
        modeloItemsPedido = new PersonalizatedTableModel( modeloTableName, new ArrayList());
        sorter = new TableRowSorter<>(modeloItemsPedido);
        actualFilter = filterMode.ID;
        initComponents();
        contentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
           @Override
           public void valueChanged(ListSelectionEvent evt){
               if(!evt.getValueIsAdjusting()){
                   int filaSeleccionada = contentTable.getSelectedRow();
                   if(filaSeleccionada != -1){
                       ID_Seleccionado = (Integer)contentTable.getValueAt(filaSeleccionada, 0);
                   }
               }   
           }});
    }
    public void setUpperFrame(ClientesFrame frame){
        this.frameSuperior = frame;
    }
    public void setCliente(int ID, ClientController carrito){
        this.ClientID = ID;
        this.carrito = carrito;
        List contenidoCarrito= this.carrito.getContenidoCarrito();
        this.modeloItemsPedido.setItems((contenidoCarrito==null)?new ArrayList():contenidoCarrito);
        }
    public void updateModel(){
       System.out.println(this.ClientID);
       List contenidoCarrito= this.carrito.getContenidoCarrito();
       this.modeloItemsPedido.setItems((contenidoCarrito==null)?new ArrayList():contenidoCarrito);
       this.contentTable.revalidate();
       this.contentTable.repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jButton1 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        contentTable = new javax.swing.JTable();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton3 = new javax.swing.JButton();
        cerrarPedidoButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        jButton1.setText("jButton1");

        jPanel7.setLayout(new java.awt.GridBagLayout());

        contentTable.setAutoCreateRowSorter(true);
        contentTable.setModel(this.modeloItemsPedido);
        contentTable.setRowSorter(sorter);
        jScrollPane10.setViewportView(contentTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel7.add(jScrollPane10, gridBagConstraints);

        jTextPane3.setEditable(false);
        jTextPane3.setText("Carrito");
        jTextPane3.setToolTipText("");
        jTextPane3.setAutoscrolls(false);
        jTextPane3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextPane3.setFocusable(false);
        jScrollPane13.setViewportView(jTextPane3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel7.add(jScrollPane13, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel7.add(jSeparator1, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(this.modeloItemsPedido.getColumnName()));
        jComboBox1.setMinimumSize(new java.awt.Dimension(135, 22));
        jComboBox1.setPreferredSize(new java.awt.Dimension(135, 22));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jComboBox1, gridBagConstraints);

        jTextPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextPane1.setToolTipText("");
        jTextPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextPane1.setPreferredSize(new java.awt.Dimension(180, 20));
        jTextPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextPane1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        jPanel7.add(jPanel1, gridBagConstraints);

        jButton3.setText("Ver detalle");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(jButton3, gridBagConstraints);

        cerrarPedidoButton.setText("Cerrar Pedido");
        cerrarPedidoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarPedidoButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(cerrarPedidoButton, gridBagConstraints);

        backButton.setText("Volver");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel7.add(backButton, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextPane1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPane1KeyPressed
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            String text;
            text = jTextPane1.getText();
            sorter.setRowFilter(null);
            jTextPane1.setText("");
            if(!text.isBlank()){
                try{
                    switch(actualFilter){
                        case ID-> sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, Integer.valueOf(text), 0));
                        case NAME -> sorter.setRowFilter(RowFilter.regexFilter(text, 1));
                        case INDIVIDUAL_PRICE -> sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, Double.valueOf(text), 2));
                        case ID_PEDIDO -> sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, Double.valueOf(text), 3));
                        case CANTIDAD -> sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, Double.valueOf(text), (this.filterID<0)? 4 : 3 ));
                        case PRICE -> sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, Double.valueOf(text), (this.filterID<0)? 5 : 4 ));
                    }
                }catch(NumberFormatException e){}
            }
            
        }
    }//GEN-LAST:event_jTextPane1KeyPressed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        String currentSelection = (String) jComboBox1.getSelectedItem();
        switch (currentSelection) {
            case "Tipo de Búsqueda":
                this.actualFilter = filterMode.NONE;
                break;
            case "ID":
                this.actualFilter = filterMode.ID;
                break;
            case "Nombre":
                this.actualFilter = filterMode.NAME;
                break;
            case "Precio Individual":
                this.actualFilter = filterMode.INDIVIDUAL_PRICE;
                break;
            case "ID Pedido":
                this.actualFilter = filterMode.ID_PEDIDO;
                break;
            case "Cantidad":
                this.actualFilter = filterMode.CANTIDAD;
                break;
            case "Precio Final":
            this.actualFilter = filterMode.PRICE;
                break;
            default:
                break;
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(this.ID_Seleccionado!=-1){}
        else JOptionPane.showMessageDialog((JFrame) SwingUtilities.getWindowAncestor(this) , "Error, Item no seleccionado. Por favor seleccione un Item"
                , "Item no seleccionado",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cerrarPedidoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarPedidoButtonActionPerformed
        if(carrito.getContenidoCarrito()==null) 
            JOptionPane.showMessageDialog(null, "No es posible cerrar su carrito, está vacío", "Carrito Vacío", JOptionPane.ERROR_MESSAGE);
        else
            new CerrarPedido((JFrame) SwingUtilities.getWindowAncestor(this),true, this.carrito, this).setVisible(true);
    }//GEN-LAST:event_cerrarPedidoButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.frameSuperior.goBack();
    }//GEN-LAST:event_backButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton cerrarPedidoButton;
    private javax.swing.JTable contentTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane3;
    // End of variables declaration//GEN-END:variables
}
