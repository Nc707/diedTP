/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;

import java.util.ArrayList;
import nc.diedtp.excepciones.PedidoIncorrectoException;
import nc.diedtp.excepciones.VendedorIncorrectoException;

/**
 *
 * @author jereb
 */
public class Pedido {
    private static int next_id=0;
    private int id;
    private Vendedor vendedor;
    private Cliente cliente;
    private float precio;
    public int getId() {
        return id;
    }
        
    //cons
    public Pedido(Vendedor vendedor, Cliente cliente){
        this.vendedor = vendedor;
        this.id = next_id;
        next_id++;
        this.cliente = cliente;
        this.precio = 0.0f;
    }
    public void addItem(ItemPedido item) throws VendedorIncorrectoException, PedidoIncorrectoException{
        if(item.getVendedor() != this.vendedor) throw new VendedorIncorrectoException("El vendedor del item:" + item.toString() + " no coincide con el pedido");
        if(item.getPedido() != this) throw new PedidoIncorrectoException("El item: "+ item.toString() + " no corresponde al pedido");
        this.precio += item.getPrecio();
    }
    public void deleteItem(ItemPedido item) throws VendedorIncorrectoException, PedidoIncorrectoException{
        if(item.getVendedor() != this.vendedor) throw new VendedorIncorrectoException("El vendedor del item:" + item.toString() + " no coincide con el pedido");
        if(item.getPedido() != this) throw new PedidoIncorrectoException("El item: "+ item.toString() + " no corresponde al pedido");
        this.precio -= item.getPrecio();
    }
    public void updatePrecio(float precioViejo, float precioNuevo){
        this.precio+=(precioNuevo - precioViejo);
        
    }
    public float getPrecio(){
        return this.precio;
    }
    
    //gets
    /*public ArrayList<ItemPedido> getItemsPedido(){
        return listaItemsPedido;
    }
    private void addItems(ArrayList<ItemPedido> items) throws VendedorIncorrectoException{
        for(ItemPedido item: items){
            listaItemsPedido.add(item);
        }
    }
    public void addItemPedido(ItemPedido item)  throws VendedorIncorrectoException{
        if(item.getVendedor() != this.vendedor){
            throw new VendedorIncorrectoException("El item con ID: " + item.getId()+ "no corresponde al vendedor: " + vendedor.toString());
        }
        this.listaItemsPedido.add(item);
        
    }
       public void mostrarItems(){
         String cant, nombreItemMenu, precio;
        for(ItemPedido item:listaItemsPedido){
            cant = String.valueOf(item.getCantidad());
            nombreItemMenu = item.getItemMenu().getNombre();
            precio = String.valueOf(item.getItemMenu().getPrecio());
            System.out.println(cant + " "+nombreItemMenu+" "+precio);
        }
        
    }*/
    public Cliente getCliente(){
        return cliente;
    }
    public Vendedor getVendedor(){
        return vendedor;
    }
         
}
