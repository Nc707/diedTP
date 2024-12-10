package nc.dao.memory;

import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.List;
import java.util.stream.Collectors;
import nc.dao.ItemPedidoDAO;
import static nc.dao.ItemPedidoDAO.tipoFiltrado.*;
import nc.excepciones.ItemNoEncontradoException;
import nc.modelo.Categoria;
import nc.modelo.Cliente;
import nc.modelo.ItemMenu;
import nc.modelo.ItemPedido;
import nc.modelo.Pedido;
import nc.modelo.Vendedor;
import nc.util.compareStategies.itemPedido.implementations.ClienteIdComp;
import nc.util.compareStategies.itemPedido.implementations.ItemPedidoPriceCompSt;
import nc.util.compareStategies.itemPedido.implementations.PedidoIdComp;
import nc.util.compareStategies.itemPedido.implementations.PedidoPriceComp;

public class ItemPedidoMemory implements ItemPedidoDAO {

    private static ItemPedidoMemory uniqueInstance;
    private ArrayList<ItemPedido> memory;

    public static ItemPedidoMemory getItemPedidoMemory() {
        if (uniqueInstance == null) {
            uniqueInstance = new ItemPedidoMemory();
        }
        return uniqueInstance;
    }

    private ItemPedidoMemory() {
        this.memory = new ArrayList<>();
    }

    @Override
    public List<ItemPedido> getAll() {
        return this.memory;
    }

    @Override
    public List<ItemPedido> listarPorPedido(int id_pedido) {
        return memory.stream()
                     .filter(item -> item.getPedido().getId() == id_pedido)
                     .collect(Collectors.toList());
    }

    @Override
    public void add(ItemPedido item, int id_pedido) {
        memory.add(item);
    }

    @Override
    public void update(int ID, ItemPedido item) throws ItemNoEncontradoException {
        ItemPedido itemToUpdate = this.filtrarPor(ID_ITEMPEDIDO, ID).getFirst();
        if (itemToUpdate == null) {
            throw new ItemNoEncontradoException("No se encontró el item a actualizar");
        }
        itemToUpdate.setCantidad(item.getCantidad());
        itemToUpdate.setPrecio(item.getPrecio());
        itemToUpdate.setItemMenu(item.getItemMenu());
        itemToUpdate.setPedido(item.getPedido());
    }

    @Override
    public void delete(int ID) throws ItemNoEncontradoException {
        boolean removed = memory.removeIf(item -> item.getId() == ID);
        if (!removed) {
            throw new ItemNoEncontradoException("No se encontró el item a eliminar");
        }
    }

    @Override
    public void addAll(List<ItemPedido> items, int id_pedido) {
        memory.addAll(items);
    }

    @Override
    public ItemPedido getItem(int ID) throws ItemNoEncontradoException {
        return memory.stream()
                     .filter(item -> item.getId() == ID)
                     .findFirst()
                     .orElseThrow(() -> new ItemNoEncontradoException("No se encontró el item"));
    }

    @Override
    public List<ItemPedido> filtrarPor(tipoFiltrado tipoFiltro, Object filtro) throws ItemNoEncontradoException {
        List<ItemPedido> filtrado = new ArrayList();
        switch (tipoFiltro) {
            case PEDIDO ->
                filtrado = memory.stream().filter(item -> item.getPedido() == (Pedido) filtro).collect(Collectors.toList());
            case VENDEDOR ->
                filtrado = memory.stream().filter(item -> item.getVendedor() == (Vendedor) filtro).collect(Collectors.toList());
            case CLIENTE ->
                filtrado = memory.stream().filter(item -> item.getPedido().getCliente() == (Cliente) filtro).collect(Collectors.toList());
            case ITEMMENU ->
                filtrado = memory.stream().filter(item -> item.getItemMenu() == (ItemMenu) filtro).collect(Collectors.toList());
            case CATEGORIA ->
                filtrado = memory.stream().filter(item -> item.getItemMenu().tieneCategoria((Categoria) filtro)).collect(Collectors.toList());
            case CATEGORIAS ->
                filtrado = memory.stream().filter(item -> item.getItemMenu().tieneCategorias((List<Categoria>) filtro)).collect(Collectors.toList());
            case PRECIO_TOPE_ITEMPEDIDO ->
                filtrado = memory.stream().filter(item -> item.getPrecio() <= (float) filtro).collect(Collectors.toList());
            case PRECIO_TOPE_ITEMMENU ->
                filtrado = memory.stream().filter(item -> item.getItemMenu().getPrecio() <= (float) filtro).collect(Collectors.toList());
            case PRECIO_TOPE_PEDIDO ->
                filtrado = memory.stream().filter(item -> item.getPedido().getPrecio() <= (float) filtro).collect(Collectors.toList());
            case PRECIO_MINIMO_ITEMPEDIDO ->
                filtrado = memory.stream().filter(item -> item.getPrecio() >= (float) filtro).collect(Collectors.toList());
            case PRECIO_MINIMO_ITEMMENU ->
                filtrado = memory.stream().filter(item -> item.getItemMenu().getPrecio() >= (float) filtro).collect(Collectors.toList());
            case PRECIO_MINIMO_PEDIDO ->
                filtrado = memory.stream().filter(item -> item.getPedido().getPrecio() <= (float) filtro).collect(Collectors.toList());
            case ID_PEDIDO ->
                filtrado = memory.stream().filter(item -> item.getPedido().getId() == (int) filtro).collect(Collectors.toList());
            case ID_ITEMPEDIDO ->
                filtrado = memory.stream().filter(item -> item.getId() == (int) filtro).collect(Collectors.toList());
        }
        if (filtrado.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron items acordes al filtro");
        }
        return filtrado;
    }

    @Override
    public List<ItemPedido> filtrarPor(tipoFiltrado tipoFiltro, Object filtro, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException {
        List<ItemPedido> filtrado = this.filtrarPor(tipoFiltro, filtro);
        return this.ordenar(tipoOrden, filtrado, ascendente);
    }

    @Override
    public List<ItemPedido> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope) throws ItemNoEncontradoException {
        List<ItemPedido> filtrado = new ArrayList();
        switch (tipoFiltrado) {
            case PRECIO_ITEMPEDIDO ->
                filtrado = memory.stream().filter(item -> item.getPrecio() >= (float) piso && item.getPrecio() <= (float) tope).collect(Collectors.toList());
            case PRECIO_ITEMMENU ->
                filtrado = memory.stream().filter(item -> item.getItemMenu().getPrecio() >= (float) piso && item.getItemMenu().getPrecio() <= (float) tope).collect(Collectors.toList());
            case PRECIO_PEDIDO ->
                filtrado = memory.stream().filter(item -> item.getPedido().getPrecio() >= (float) piso && item.getPedido().getPrecio() <= (float) tope).collect(Collectors.toList());

        }
        if (filtrado.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron items acordes al filtro");
        }
        return filtrado;
    }

    @Override
    public List<ItemPedido> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException {
        List<ItemPedido> filtrado = this.filtrarRango(tipoFiltrado, piso, tope);
        return (this.ordenar(tipoOrden, filtrado, ascendente));
    }

    private List<ItemPedido> ordenar(tipoOrdenamiento orden, List<ItemPedido> items, boolean ascendente) {
        List<ItemPedido> ordered = items;
        switch (orden) {
            case PEDIDO_ID ->
                ordered.stream().forEach(itp -> itp.setStrategy(new PedidoIdComp(itp, ascendente)));
            case CLIENTE_ID ->
                ordered.stream().forEach(itp -> itp.setStrategy(new ClienteIdComp(itp, ascendente)));
            case PRECIO_ITEMPEDIDO ->
                ordered.stream().forEach(itp -> itp.setStrategy(new ItemPedidoPriceCompSt(itp, ascendente)));
            case PRECIO_PEDIDO ->
                ordered.stream().forEach(itp -> itp.setStrategy(new PedidoPriceComp(itp, ascendente)));
            case PRECIO_ITEMMENU ->
                ordered.stream().forEach(itp -> itp.setStrategy(new ItemPedidoPriceCompSt(itp, ascendente)));
        }
        sort(ordered);
        return ordered;
    }

   

    

   
    
}
