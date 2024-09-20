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
        String cant, nombreItemMenu, precio;
        for (Pedido ped : listaPedidos) {
            for (ItemPedido item : ped.getItemsPedido()){
            if ((item.getItemMenu().getPrecio() >= piso) && (item.getItemMenu().getPrecio() <= tope)){
                 aux.add(item);
                 cant = String.valueOf(item.getCantidad());
                 nombreItemMenu = item.getItemMenu().getNombre();
                 precio = String.valueOf(item.getItemMenu().getPrecio());
                 System.out.println(cant + " "+nombreItemMenu+" "+precio); 
                }
            }
        }
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