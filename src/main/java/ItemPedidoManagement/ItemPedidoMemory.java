package ItemPedidoManagement;

import static ItemPedidoManagement.ItemPedidoDAO.tipoFiltrado.PEDIDO;
import nc.diedtp.ItemPedido;
import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.List;
import java.util.stream.Collectors;
import nc.diedtp.Categoria;
import nc.diedtp.Cliente;
import nc.diedtp.ItemMenu;
import nc.diedtp.Pedido;
import nc.diedtp.Vendedor;
import nc.diedtp.excepciones.*;

public class ItemPedidoMemory implements ItemPedidoDAO {
    
    private ArrayList<ItemPedido> memory;
    public ItemPedidoMemory() {
        this.memory = new ArrayList<>();
    }
    public void addPedido(List<ItemPedido> items) {
        memory.addAll(items);
    }
    public void removePedido(Pedido p) throws ItemNoEncontradoException{
        List<ItemPedido> elementsToRemove = this.filtrarPor(PEDIDO, p);
        memory.removeAll(elementsToRemove);
        
    }
    @Override
    public List<ItemPedido> filtrarPor(tipoFiltrado tipoFiltro, Object filtro) throws ItemNoEncontradoException{
        List<ItemPedido> filtrado = new ArrayList();
        switch(tipoFiltro){
            case PEDIDO -> filtrado = memory.stream().filter(item->item.getPedido() == (Pedido)filtro).collect(Collectors.toList());
            case VENDEDOR -> filtrado = memory.stream().filter(item->item.getVendedor() == (Vendedor)filtro).collect(Collectors.toList());
            case CLIENTE -> filtrado = memory.stream().filter(item->item.getPedido().getCliente() == (Cliente)filtro).collect(Collectors.toList());
            case ITEMMENU -> filtrado = memory.stream().filter(item->item.getItemMenu() == (ItemMenu)filtro).collect(Collectors.toList());
            case CATEGORIA -> filtrado = memory.stream().filter(item->item.getItemMenu().tieneCategoria((Categoria) filtro)).collect(Collectors.toList());
            case CATEGORIAS -> filtrado = memory.stream().filter(item->item.getItemMenu().tieneCategorias((List<Categoria>) filtro)).collect(Collectors.toList());
            case PRECIO_TOPE_ITEMPEDIDO -> filtrado = memory.stream().filter(item->item.getPrecio() <=  (float)filtro).collect(Collectors.toList());
            case PRECIO_TOPE_ITEMMENU -> filtrado = memory.stream().filter(item->item.getItemMenu().getPrecio() <=  (float)filtro).collect(Collectors.toList());
            case PRECIO_MINIMO_ITEMPEDIDO -> filtrado = memory.stream().filter(item->item.getPrecio() >=  (float)filtro).collect(Collectors.toList());
            case PRECIO_MINIMO_ITEMMENU -> filtrado = memory.stream().filter(item->item.getItemMenu().getPrecio() >=  (float)filtro).collect(Collectors.toList());
        }
        if(filtrado.isEmpty()) throw new ItemNoEncontradoException("No se encontraron items acordes al filtro");
        return filtrado;
    }
    @Override
    public List<ItemPedido> filtrarPor(tipoFiltrado tipoFiltro, Object filtro, tipoOrdenamiento tipoOrden) throws ItemNoEncontradoException{
        List<ItemPedido> filtrado = this.filtrarPor(tipoFiltro, filtro);
        return this.ordenar(tipoOrden, filtrado);
    }

    @Override
    public List<ItemPedido> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope) throws ItemNoEncontradoException{
        List<ItemPedido> filtrado = new ArrayList();
        switch(tipoFiltrado){
            case PRECIO_ITEMPEDIDO -> filtrado = memory.stream().filter(item->item.getPrecio() >= (float) piso && item.getPrecio() <= (float)tope).collect(Collectors.toList());
            case PRECIO_ITEMMENU -> filtrado = memory.stream().filter(item->item.getItemMenu().getPrecio() >= (float) piso && item.getItemMenu().getPrecio() <= (float)tope).collect(Collectors.toList());

        }
        if(filtrado.isEmpty()) throw new ItemNoEncontradoException("No se encontraron items acordes al filtro");
        return filtrado;
    }
    @Override
    public List<ItemPedido> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope, tipoOrdenamiento tipoOrden) throws ItemNoEncontradoException{
        List<ItemPedido> filtrado = this.filtrarRango(tipoFiltrado, piso, tope);
        return this.ordenar(tipoOrden, filtrado);
    }
    private List<ItemPedido> ordenar(tipoOrdenamiento orden,List<ItemPedido> items){
         List<ItemPedido> ordered = new ArrayList();
         switch(orden){
             case PEDIDO_ID -> items.stream().forEach(itp -> itp.setStrategy(new PedidoIdComp(itp)));
             case CLIENTE_ID -> items.stream().forEach(itp -> itp.setStrategy(new ClienteIdComp(itp)));
             case PRECIO_ITEMPEDIDO -> items.stream().forEach(itp -> itp.setStrategy(new ItemPedidoPriceCompSt(itp)));
             case PRECIO_PEDIDO ->items.stream().forEach(itp -> itp.setStrategy(new PedidoPriceComp(itp)));
             case PRECIO_ITEMMENU ->items.stream().forEach(itp -> itp.setStrategy(new ItemPedidoPriceCompSt(itp)));
         }
         sort(ordered);
         return ordered;
    }

}
