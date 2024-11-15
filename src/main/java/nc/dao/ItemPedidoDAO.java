package nc.dao;

import nc.excepciones.ItemNoEncontradoException;
import nc.modelo.ItemPedido;
import java.util.List;

public interface ItemPedidoDAO {

    enum tipoFiltrado{
        PEDIDO,
        VENDEDOR,
        CLIENTE,
        ITEMMENU,
        CATEGORIA,
        CATEGORIAS,
        PRECIO_TOPE_ITEMPEDIDO,
        PRECIO_TOPE_ITEMMENU,
        PRECIO_TOPE_PEDIDO,
        PRECIO_MINIMO_ITEMPEDIDO,
        PRECIO_MINIMO_ITEMMENU,
        PRECIO_MINIMO_PEDIDO,
        ID_PEDIDO,
        ID_ITEMPEDIDO
    }
    enum tipoFiltradoRango{
    
        PRECIO_ITEMPEDIDO,
        PRECIO_PEDIDO,
        PRECIO_ITEMMENU   
    }
    enum tipoOrdenamiento{
        PEDIDO_ID,
        CLIENTE_ID,
        PRECIO_ITEMPEDIDO,
        PRECIO_PEDIDO,
        PRECIO_ITEMMENU
    }
    public List<ItemPedido> getAll();
    public List<ItemPedido> listarPorPedido(int id_pedido);
    public void add(ItemPedido item);
    public void update(int ID, ItemPedido item) throws ItemNoEncontradoException;
    public void delete(int ID) throws ItemNoEncontradoException;
    public void addAll(List<ItemPedido> items);
    public ItemPedido getItem(int ID) throws ItemNoEncontradoException;
    public List<ItemPedido> filtrarPor(tipoFiltrado tipoFiltro, Object filtro) throws ItemNoEncontradoException;
    public List<ItemPedido> filtrarPor(tipoFiltrado tipoFiltro, Object filtro, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException;
    public List<ItemPedido> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope)throws ItemNoEncontradoException;
    public List<ItemPedido> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope, tipoOrdenamiento tipoOrden, boolean ascendente)throws ItemNoEncontradoException;
}
