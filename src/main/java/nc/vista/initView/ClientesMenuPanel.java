/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package nc.vista.initView;

import nc.vista.initView.dialog.DialogCliente;
import nc.vista.initView.dialog.CreacionCliente;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import nc.controller.ClientController;
import nc.vista.PersonalizatedTableModel;
import nc.vista.VistaPrincipal;
import nc.vista.initView.dialog.ModificarCliente;

/**
 *
 * @author nicol
 */
public class ClientesMenuPanel extends javax.swing.JPanel {

    private enum filterMode {
        ID,
        NAME,
        DIRECC,
        EMAIL,
        NONE
    }
    private PersonalizatedTableModel clientTableModel;
    private ClientController clients;
    private TableRowSorter<PersonalizatedTableModel> sorter;
    private filterMode actualFilter;
    private int ID_Seleccionado = -1;
    private FirstFrame upperFrame;

    /**
     * Creates new form ClientesMenuPanel
     */
    public ClientesMenuPanel() {
        clients = new ClientController();
        List<String> clientTableNames = Arrays.asList("ID", "Nombre", "Direccion", "E-Mail");
        clientTableModel = new PersonalizatedTableModel(clientTableNames, clients.loadData());
        sorter = new TableRowSorter<>(clientTableModel);
        actualFilter = filterMode.ID;
        initComponents();
        contentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                if (!evt.getValueIsAdjusting()) {
                    int filaSeleccionada = contentTable.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        ID_Seleccionado = (Integer) contentTable.getValueAt(filaSeleccionada, 0);
                    }
                }
            }
        });

    }
    public void setUpperFrame(FirstFrame vista){
        this.upperFrame = vista;
    }
    public void setCliente(){
        this.upperFrame.upperPanel.setCliente(ID_Seleccionado);
    }
    public void modificar(int id, String name, String dir, String mail, String cuil, double coorX, double coorY){
        clients.modificarCliente(id, name, dir, mail, id, coorX, coorY);
        updateModel();
    }
    public void crearModificarCliente(int id, String name, String dir, String mail, String cuit, double coorX, double coorY){
         ModificarCliente modificarCliente = new ModificarCliente(id, name, dir, mail, cuit, coorX, coorY, this);
         modificarCliente.setVisible(true);
         modificarCliente.setLocationRelativeTo(null);
     }

    public void crearCliente(String nombre, int cuit, String email, String direccion, double latitud, double longitud) {
        clients.crear(nombre, cuit, email, direccion, latitud, longitud);
    }

    public void updateModel() {
        this.clientTableModel.setItems(clients.loadData());
        this.contentTable.updateUI();
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

        jPanel7 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        contentTable = new javax.swing.JTable();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();

        jPanel7.setLayout(new java.awt.GridBagLayout());

        contentTable.setAutoCreateRowSorter(true);
        contentTable.setModel(this.clientTableModel);
        contentTable.setRowSorter(sorter);
        jScrollPane10.setViewportView(contentTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel7.add(jScrollPane10, gridBagConstraints);

        jTextPane3.setEditable(false);
        jTextPane3.setText("Lista de Clientes");
        jTextPane3.setToolTipText("");
        jTextPane3.setAutoscrolls(false);
        jTextPane3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextPane3.setFocusable(false);
        jScrollPane13.setViewportView(jTextPane3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel7.add(jScrollPane13, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel7.add(jSeparator1, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(this.clientTableModel.getColumnName()));
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

        jButton2.setText("Darse de alta");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jButton2, gridBagConstraints);

        jTextPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextPane1.setToolTipText("");
        jTextPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
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

        jButton1.setText("Ver detalle");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(jButton1, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

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
            case "Dirección":
                this.actualFilter = filterMode.DIRECC;
                break;
            case "E-Mail":
                this.actualFilter = filterMode.EMAIL;
                break;
            default:
                break;
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CreacionCliente creacion = new CreacionCliente(this);
        setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextPane1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPane1KeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String text;
            text = jTextPane1.getText();
            sorter.setRowFilter(null);
            jTextPane1.setText("");
            if (!text.isBlank()) {
                try {
                    switch (actualFilter) {
                        case ID ->
                            sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, Integer.valueOf(text), 0));
                        case NAME ->
                            sorter.setRowFilter(RowFilter.regexFilter(text, 1));
                        case DIRECC ->
                            sorter.setRowFilter(RowFilter.regexFilter(text, 2));
                        case EMAIL ->
                            sorter.setRowFilter(RowFilter.regexFilter(text, 3));
                    }
                } catch (NumberFormatException e) {
                }
            }

        }
    }//GEN-LAST:event_jTextPane1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int selectedRow = contentTable.getSelectedRow();
        if (selectedRow != -1) {
            // Convierte la fila seleccionada al índice de modelo en caso de que esté ordenada
            int modelRow = contentTable.convertRowIndexToModel(selectedRow);

            List datosCliente = clients.getCliente((int) clientTableModel.getValueAt(modelRow, 0));

            DialogCliente dialog = new DialogCliente(null, true, (ArrayList) datosCliente, this);
            dialog.setVisible(true);
            dialog.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable contentTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
