package nc.diedtp;

import java.util.ArrayList;

public class ItemPedidoMemory implements ItemPedidoDAO{
    private ArrayList <ItemPedido> listaItemsPedido;
    
 public ItemPedidoMemory(ArrayList<ItemPedido> listaItemsPedido) {
        this.listaItemsPedido = listaItemsPedido;
    }

    public ArrayList<ItemPedido> getListaItemsPedido() {
        return listaItemsPedido;
    }

    public void setListaItemsPedido(ArrayList<ItemPedido> listaItemsPedido) {
        this.listaItemsPedido = listaItemsPedido;
    }
 
    public void addItemPedido(ItemPedido item){
        this.listaItemsPedido.add(item);
        
    }
    @Override
    public void buscqedaPorRango(int x, int y){
        
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