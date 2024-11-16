/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package nc.vista.cliente;

import java.util.List;
import nc.controller.ClientController;
import nc.vista.VistaPrincipal;

/**
 *
 * @author nicol
 */
public class ClientesFrame extends javax.swing.JPanel {

    /**
     * Creates new form ClientesFrame
     */
    private VistaPrincipal upperFrame;
    private int cliente = -1;
    private int IDCurrentVendedor;
    private int IDCurrentPedido;
    private ClientController controlador;
    
    @SuppressWarnings("")
    public ClientesFrame() {
        initComponents();
        this.controlador = new ClientController();
        itemMenuPanelCliente1.setUpperFrame(this);
        vendedoresSelectPanelCliente1.setUpperFrame(this);
        pedidosMenuPanelCliente1.setUpperFrame(this);
        this.itemPedidoPanelCliente1.setFrameSuperior(this);
        this.carritoCliente1.setUpperFrame(this);
        jTabbedPane1.setEnabledAt(jTabbedPane1.indexOfComponent(itemMenuPanelCliente1), false);
        jTabbedPane1.setEnabledAt(jTabbedPane1.indexOfComponent(itemPedidoPanelCliente1), false);
        pedidosMenuPanelCliente1.setUpperFrame(this);
    }
    public void setFrameSuperior(VistaPrincipal upperFrame){
        this.upperFrame = upperFrame;
    }
    public void setVendedor(int ID){
        this.IDCurrentVendedor = ID;
        itemMenuPanelCliente1.setID(ID);
        itemMenuPanelCliente1.setCliente(cliente);
        itemMenuPanelCliente1.setClientController(controlador);
        itemMenuPanelCliente1.updateModel();
        jTabbedPane1.setEnabledAt(jTabbedPane1.indexOfComponent(itemMenuPanelCliente1), true);
        jTabbedPane1.setSelectedIndex(jTabbedPane1.indexOfComponent(itemMenuPanelCliente1));
        jTabbedPane1.setEnabledAt(jTabbedPane1.indexOfComponent(vendedoresSelectPanelCliente1), false);
    }
    public void unsetVendedor(){
        this.IDCurrentVendedor = -1;
        jTabbedPane1.setEnabledAt(jTabbedPane1.indexOfComponent(itemMenuPanelCliente1), false);
        jTabbedPane1.setEnabledAt(jTabbedPane1.indexOfComponent(vendedoresSelectPanelCliente1), true);
    }
    public void updateCarrito(){
        this.carritoCliente1.updateModel();
    }
    public void updateMisPedidos(){
        this.pedidosMenuPanelCliente1.updateTable();
    }
    public void deleteCarrito(){
        this.IDCurrentVendedor = -1;
        jTabbedPane1.setEnabledAt(jTabbedPane1.indexOfComponent(itemMenuPanelCliente1), false);
        jTabbedPane1.setEnabledAt(jTabbedPane1.indexOfComponent(vendedoresSelectPanelCliente1), true);
        jTabbedPane1.setSelectedIndex(jTabbedPane1.indexOfComponent(vendedoresSelectPanelCliente1));
    }
    public void unsetPedido(){
        this.IDCurrentPedido = -1;
        jTabbedPane1.setEnabledAt(jTabbedPane1.indexOfComponent(itemPedidoPanelCliente1), false);
    }
    public void setPedido(int IDPedido){
       this.itemPedidoPanelCliente1.setPedido(IDPedido);
       this.itemPedidoPanelCliente1.updateModel();
       jTabbedPane1.setEnabledAt(jTabbedPane1.indexOfComponent(itemPedidoPanelCliente1), true);
       jTabbedPane1.setSelectedIndex(jTabbedPane1.indexOfComponent(itemPedidoPanelCliente1));
    }
    public void setCliente(int IDCliente){
        this.cliente = IDCliente;
        this.controlador.setCliente(IDCliente);
        this.carritoCliente1.setCliente(IDCliente, this.controlador);
        int idVendedor = controlador.getVendedorCarrito();
        pedidosMenuPanelCliente1.setClientID(IDCliente);
        pedidosMenuPanelCliente1.updateTable();
        if(idVendedor<0)
            jTabbedPane1.setSelectedIndex(jTabbedPane1.indexOfComponent(vendedoresSelectPanelCliente1));
        else
            setVendedor(idVendedor);        
    }
    public void goBack(){
        unsetVendedor();
        unsetPedido();
        upperFrame.volverInicio();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        vendedoresSelectPanelCliente1 = new nc.vista.cliente.VendedoresSelectPanelCliente();
        itemMenuPanelCliente1 = new nc.vista.cliente.ItemMenuPanelCliente();
        carritoCliente1 = new nc.vista.cliente.CarritoCliente();
        pedidosMenuPanelCliente1 = new nc.vista.cliente.PedidosMenuPanelCliente();
        itemPedidoPanelCliente1 = new nc.vista.cliente.ItemPedidoPanelCliente();
        pedidosMenuPanelCliente1 = new nc.vista.cliente.PedidosMenuPanelCliente();

        setLayout(new java.awt.BorderLayout());

        jTabbedPane1.addTab("Seleccionar vendedor", vendedoresSelectPanelCliente1);
        jTabbedPane1.addTab("ItemsMenu", itemMenuPanelCliente1);
        jTabbedPane1.addTab("Carrito", carritoCliente1);
        jTabbedPane1.addTab("Mis Pedidos", pedidosMenuPanelCliente1);
        jTabbedPane1.addTab("Pedido", itemPedidoPanelCliente1);
        jTabbedPane1.addTab("Mis Pedidos", pedidosMenuPanelCliente1);

        add(jTabbedPane1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private nc.vista.cliente.CarritoCliente carritoCliente1;
    private nc.vista.cliente.ItemMenuPanelCliente itemMenuPanelCliente1;
    private nc.vista.cliente.ItemPedidoPanelCliente itemPedidoPanelCliente1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private nc.vista.cliente.PedidosMenuPanelCliente pedidosMenuPanelCliente1;
    private nc.vista.cliente.VendedoresSelectPanelCliente vendedoresSelectPanelCliente1;
    // End of variables declaration//GEN-END:variables
}
