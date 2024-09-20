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
    /*public class Vendedor {
    private static int next_id = 0;
    private int id;
    private String nombre;
    private String direccion;
    private Coordenada coordenada;
    private ArrayList<ItemMenu> menu;*/
    
    @Override
    public ArrayList<ItemPedido> buscarPorVendedor(int idVendedor){
      ArrayList<ItemPedido> aux = new ArrayList();
      for(Pedido pedido: listaPedidos){
          for(ItemPedido item: pedido.getItemsPedido()){
              if(item.getItemMenu().getVendedor().getId() == idVendedor){
                aux.add(item);  
              }
          }
      }
        return aux;
    }
       @Override
    public ArrayList<ItemPedido> filtrarCliente(int id){
        ArrayList<ItemPedido> aux = new ArrayList();
        Cliente cliente = new Cliente();
        
        for(Pedido ped:listaPedidos){
           if( id == ped.getCliente().getId()){
               cliente = ped.getCliente();
           }      
        }
        
        System.out.println("Items pedidos por "+cliente.getNombre()+":");
        for(Pedido ped:listaPedidos){
            if(cliente.equals(ped.getCliente())){
                aux.addAll(ped.getItemsPedido());
                ped.mostrarItems();
            }
        }
        return aux;
     }
    
  
    @Override
    public void filtrarCategoria(){
        
    }

}