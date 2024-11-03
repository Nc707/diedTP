/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package nc.vista.vendedor;

/**
 *
 * @author nicol
 */
public class CreacionBebida extends javax.swing.JPanel {

    /**
     * Creates new form CreacionBebida
     */
    public CreacionBebida() {
        initComponents();
    }
     public float getPeso(){
        return Float.parseFloat(textoPeso.getText());
    }
    public int getGrado(){
        return Integer.parseInt(textoGrado.getText());
    }
    public float getTam(){
        return Float.parseFloat(textoTam.getText());
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textoPeso = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textoGrado = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textoTam = new javax.swing.JTextPane();

        setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Peso");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 0);
        add(jLabel1, gridBagConstraints);

        textoPeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoPesoKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(textoPeso);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jScrollPane1, gridBagConstraints);

        jLabel2.setText("Grado Alcoholico");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 0);
        add(jLabel2, gridBagConstraints);

        textoGrado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoGradoKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(textoGrado);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jScrollPane2, gridBagConstraints);

        jLabel3.setText("Tamaño");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 0);
        add(jLabel3, gridBagConstraints);

        textoTam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoTamKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(textoTam);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jScrollPane3, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void textoPesoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoPesoKeyTyped
        char car = evt.getKeyChar();
        if((car < '0' || car > '9') && car != '.') evt.consume();
    }//GEN-LAST:event_textoPesoKeyTyped

    private void textoGradoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoGradoKeyTyped
         char car = evt.getKeyChar();
    if((car < '0' || car > '9') && car != '.') evt.consume();
    }//GEN-LAST:event_textoGradoKeyTyped

    private void textoTamKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoTamKeyTyped
       char car = evt.getKeyChar();
    if((car < '0' || car > '9') && car != '.') evt.consume();
    }//GEN-LAST:event_textoTamKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane textoGrado;
    private javax.swing.JTextPane textoPeso;
    private javax.swing.JTextPane textoTam;
    // End of variables declaration//GEN-END:variables
}