package nc.diedtp;

import java.util.ArrayList;

public class ItemPedidoMemory {
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


}