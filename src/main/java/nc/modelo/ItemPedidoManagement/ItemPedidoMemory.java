package nc.modelo.ItemPedidoManagement;


import nc.modelo.excepciones.ItemNoEncontradoException;
import nc.modelo.ItemPedidoManagement.compareStrategies.ClienteIdComp;
import nc.modelo.ItemPedidoManagement.compareStrategies.ItemPedidoPriceCompSt;
import nc.modelo.ItemPedidoManagement.compareStrategies.PedidoIdComp;
import nc.modelo.ItemPedidoManagement.compareStrategies.PedidoPriceComp;
import static nc.modelo.ItemPedidoManagement.ItemPedidoDAO.tipoFiltrado.PEDIDO;
import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.List;
 import java.util.stream.Collectors;
import nc.modelo.Categoria;
import nc.modelo.Cliente;
import nc.modelo.ItemMenu;
import nc.modelo.ItemPedido;
import nc.modelo.Pedido;
import nc.modelo.Vendedor;

public final class ItemPedidoMemory implements ItemPedidoDAO {
    private static ItemPedidoMemory uniqueInstance;
    private ArrayList<ItemPedido> memory;
    
    private ItemPedidoMemory() {
        this.memory = new ArrayList<>();
    }
    public static ItemPedidoMemory getItemPedidoMemory(){
    if(uniqueInstance == null){
        uniqueInstance = new ItemPedidoMemory();
    }
        return uniqueInstance;
    }
    public List<ItemPedido> getAll(){
        return this.memory;
    }
    public void addPedido(List<ItemPedido> items){
        memory.addAll(items);
        
    }
    public void removePedido(Pedido p) throws ItemNoEncontradoException{
        List<ItemPedido> elementsToRemove = this.filtrarPor(PEDIDO, p);
        memory.removeAll(elementsToRemove);
    }
    public List<Pedido> getPedidos(Vendedor vend) throws ItemNoEncontradoException{
        return memory.stream().map(item->item.getPedido()).distinct().filter(pedido->pedido.getVendedor() == vend)
                .collect(Collectors.toList());
    }
    public List<Pedido> getPedidos(int ID, Boolean isVendor) throws ItemNoEncontradoException{
        if(isVendor){
            return memory.stream().map(item->item.getPedido()).distinct().filter(pedido->pedido.getVendedor().getId() == ID)
                    .collect(Collectors.toList());
        }else{
            return memory.stream().map(item->item.getPedido()).distinct().filter(pedido->pedido.getCliente().getId() == ID)
                    .collect(Collectors.toList());
        }
    }
    public List<Pedido> getPedidos(){
        return memory.stream().map(item->item.getPedido()).distinct().collect(Collectors.toList());
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
            case PRECIO_TOPE_PEDIDO -> filtrado = memory.stream().filter(item->item.getPedido().getPrecio() <=  (float)filtro).collect(Collectors.toList());
            case PRECIO_MINIMO_ITEMPEDIDO -> filtrado = memory.stream().filter(item->item.getPrecio() >=  (float)filtro).collect(Collectors.toList());
            case PRECIO_MINIMO_ITEMMENU -> filtrado = memory.stream().filter(item->item.getItemMenu().getPrecio() >=  (float)filtro).collect(Collectors.toList());
            case PRECIO_MINIMO_PEDIDO -> filtrado = memory.stream().filter(item->item.getPedido().getPrecio() <=  (float)filtro).collect(Collectors.toList());
            case ID_PEDIDO -> memory.stream().filter(item->item.getPedido().getId() == (Integer)filtro).collect(Collectors.toList());
            case ID_ITEMPEDIDO -> memory.stream().filter(item->item.getId() == (Integer)filtro).collect(Collectors.toList());
        }
        if(filtrado.isEmpty()) throw new ItemNoEncontradoException("No se encontraron items acordes al filtro");
        return filtrado;
    }
    @Override
    public List<ItemPedido> filtrarPor(tipoFiltrado tipoFiltro, Object filtro, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException{
        List<ItemPedido> filtrado = this.filtrarPor(tipoFiltro, filtro);
        return this.ordenar(tipoOrden, filtrado, ascendente);
    }

    @Override
    public List<ItemPedido> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope) throws ItemNoEncontradoException{
        List<ItemPedido> filtrado = new ArrayList();
        switch(tipoFiltrado){
            case PRECIO_ITEMPEDIDO -> filtrado = memory.stream().filter(item->item.getPrecio() >= (float) piso && item.getPrecio() <= (float)tope).collect(Collectors.toList());
            case PRECIO_ITEMMENU -> filtrado = memory.stream().filter(item->item.getItemMenu().getPrecio() >= (float) piso && item.getItemMenu().getPrecio() <= (float)tope).collect(Collectors.toList());
            case PRECIO_PEDIDO -> filtrado = memory.stream().filter(item->item.getPedido().getPrecio() >= (float) piso && item.getPedido().getPrecio() <= (float)tope).collect(Collectors.toList());

        }
        if(filtrado.isEmpty()) throw new ItemNoEncontradoException("No se encontraron items acordes al filtro");
        return filtrado;
    }
    @Override
    public List<ItemPedido> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException{
        List<ItemPedido> filtrado = this.filtrarRango(tipoFiltrado, piso, tope);
        return (this.ordenar(tipoOrden, filtrado, ascendente));
    }
    private List<ItemPedido> ordenar(tipoOrdenamiento orden,List<ItemPedido> items, boolean ascendente){
         List<ItemPedido> ordered = items;
         switch(orden){
             case PEDIDO_ID -> ordered.stream().forEach(itp -> itp.setStrategy(new PedidoIdComp(itp, ascendente)));
             case CLIENTE_ID -> ordered.stream().forEach(itp -> itp.setStrategy(new ClienteIdComp(itp, ascendente)));
             case PRECIO_ITEMPEDIDO -> ordered.stream().forEach(itp -> itp.setStrategy(new ItemPedidoPriceCompSt(itp, ascendente)));
             case PRECIO_PEDIDO ->ordered.stream().forEach(itp -> itp.setStrategy(new PedidoPriceComp(itp, ascendente)));
             case PRECIO_ITEMMENU ->ordered.stream().forEach(itp -> itp.setStrategy(new ItemPedidoPriceCompSt(itp, ascendente)));
         }
         sort(ordered);
         return ordered;
    }
}

   