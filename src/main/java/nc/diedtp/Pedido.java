/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;

import java.util.ArrayList;
import nc.diedtp.excepciones.VendedorIncorrectoException;

/**
 *
 * @author jereb
 */
public class Pedido {
    private static int next_id=0;
    private int id;
    private Vendedor vendedor;
    public int getId() {
        return id;
    }
    private ArrayList <ItemPedido> listaItemsPedido;
    private Cliente cliente;
        
    //cons
    public Pedido(Vendedor vendedor, Cliente cliente){
        this.listaItemsPedido = new ArrayList<>();
        this.vendedor = vendedor;
        this.id = next_id;
        next_id++;
        this.cliente = cliente;
    }
    
    public Pedido(Vendedor vendedor, ArrayList<ItemPedido> items, Cliente clie) throws VendedorIncorrectoException{
        this(vendedor, clie);
        this.addItems(items);
        this.cliente=clie;
    }
    //gets
    public ArrayList<ItemPedido> getItemsPedido(){
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
        
    }
    public Cliente getCliente(){
        return cliente;
    }
    public Vendedor getVendedor(){
        return vendedor;
    }
         
}
