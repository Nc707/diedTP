package nc.diedtp;

import java.util.ArrayList;

public class ItemPedidoMemory implements ItemPedidoDAO{
    private ArrayList <Pedido> listaPedidos;
    
 public ItemPedidoMemory(ArrayList<Pedido> listaPedido) {
        this.listaPedidos = listaPedido;
    }

    public ArrayList<Pedido> getListaItemsPedido() {
        return listaPedidos;
    }

    public void setListaItemsPedido(ArrayList<Pedido> listaPedido) {
        this.listaPedidos = listaPedido;
    }
 
    public void addItemPedido(Pedido pedido){
        this.listaPedidos.add(pedido);
        
    }
    @Override
    public ArrayList<ItemPedido> busquedaPorRango(float piso, float tope){
        ArrayList<ItemPedido> aux = new ArrayList();
        //for (Pedido ped : listaPedidos) {
        //    if ((ped.getItemsPedido().getItemMenu().getPrecio() >= piso) && (ped.getItemsPedido().getItemMenu().getPrecio() <= tope)){
        //       aux.add(ped);
        //    }
        //}
        return aux;
    }
    @Override
    public void buscarPorRestaurante(Vendedor vendedor){
        
    }
    @Override
    public void filtrarId(int id){
        
    }
    @Override
    public void filtrarCliente(Cliente cliente){
        
    }
    @Override
    public void filtrarCategoria(){
        
    }

}