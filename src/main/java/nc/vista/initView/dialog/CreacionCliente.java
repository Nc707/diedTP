/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package nc.vista.initView.dialog;

import nc.controlador.ClientController;
import nc.vista.initView.ClientesMenuPanel;
import nc.vista.initView.ClientesMenuPanel;

/**
 *
 * @author lucia
 */
public class CreacionCliente extends javax.swing.JFrame {
    ClientesMenuPanel panelSuperior;
    /**
     * Creates new form CreacionCliente
     */
    public CreacionCliente(ClientesMenuPanel panelSuperior) {
        this.panelSuperior=panelSuperior;
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCuit = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtCoordX = new javax.swing.JTextField();
        txtCoordY = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        getContentPane().setLayout(layout);

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 38;
        getContentPane().add(jButton1, gridBagConstraints);

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 38;
        getContentPane().add(jButton2, gridBagConstraints);

        jLabel1.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText("Cuit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        getContentPane().add(jLabel2, gridBagConstraints);

        jLabel3.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        getContentPane().add(jLabel3, gridBagConstraints);

        jLabel4.setText("Direccion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 22;
        getContentPane().add(jLabel4, gridBagConstraints);

        jLabel5.setText("Coordenada");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 28;
        getContentPane().add(jLabel5, gridBagConstraints);

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 60;
        getContentPane().add(txtNombre, gridBagConstraints);

        txtCuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCuitActionPerformed(evt);
            }
        });
        txtCuit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuitKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.ipadx = 60;
        getContentPane().add(txtCuit, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.ipadx = 60;
        getContentPane().add(txtEmail, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.ipadx = 60;
        getContentPane().add(txtDireccion, gridBagConstraints);

        txtCoordX.setText("x:");
        txtCoordX.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCoordXFocusGained(evt);
            }
        });
        txtCoordX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCoordXActionPerformed(evt);
            }
        });
        txtCoordX.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCoordXKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCoordXKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 28;
        gridBagConstraints.ipadx = 60;
        getContentPane().add(txtCoordX, gridBagConstraints);

        txtCoordY.setText("y:");
        txtCoordY.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCoordYFocusGained(evt);
            }
        });
        txtCoordY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCoordYActionPerformed(evt);
            }
        });
        txtCoordY.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCoordYKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 28;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.insets = new java.awt.Insets(1, 7, 1, 17);
        getContentPane().add(txtCoordY, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtCoordYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCoordYActionPerformed
    
    }//GEN-LAST:event_txtCoordYActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     panelSuperior.crearCliente(txtNombre.getText(), (int) Long.parseLong(txtCuit.getText()), txtEmail.getText(), txtDireccion.getText(), Double.parseDouble(txtCoordX.getText()), Double.parseDouble(txtCoordY.getText()));
     panelSuperior.updateModel();
      this.dispose();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtCoordXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCoordXActionPerformed

    }//GEN-LAST:event_txtCoordXActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtCuitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuitKeyTyped
       char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_txtCuitKeyTyped

    private void txtCoordXKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCoordXKeyTyped
        char car = evt.getKeyChar();
        if((car < '0' || car > '9') && car != '.') evt.consume();
    }//GEN-LAST:event_txtCoordXKeyTyped

    private void txtCoordYKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCoordYKeyTyped
    char car = evt.getKeyChar();
    if((car < '0' || car > '9') && car != '.') evt.consume();       
    }//GEN-LAST:event_txtCoordYKeyTyped

    private void txtCoordXKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCoordXKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCoordXKeyPressed

    private void txtCoordXFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCoordXFocusGained
        if (txtCoordX.getText().equals("x:")) {
                    txtCoordX.setText("");
                }
    }//GEN-LAST:event_txtCoordXFocusGained

    private void txtCoordYFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCoordYFocusGained
        if (txtCoordY.getText().equals("y:")) {
                    txtCoordY.setText("");
                }
    }//GEN-LAST:event_txtCoordYFocusGained

    private void txtCuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCuitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuitActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtCoordX;
    private javax.swing.JTextField txtCoordY;
    private javax.swing.JTextField txtCuit;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
