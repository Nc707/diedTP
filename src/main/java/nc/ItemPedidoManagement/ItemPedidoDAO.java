package nc.ItemPedidoManagement;

import nc.diedtp.ItemPedido;
import java.util.ArrayList;
import java.util.List;
import nc.diedtp.excepciones.*;

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
        PRECIO_MINIMO_PEDIDO
        
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
    public List<ItemPedido> filtrarPor(tipoFiltrado tipoFiltro, Object filtro) throws ItemNoEncontradoException;
    public List<ItemPedido> filtrarPor(tipoFiltrado tipoFiltro, Object filtro, tipoOrdenamiento tipoOrden, boolean ascendente) throws ItemNoEncontradoException;
    public List<ItemPedido> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope)throws ItemNoEncontradoException;
    public List<ItemPedido> filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope, tipoOrdenamiento tipoOrden, boolean ascendente)throws ItemNoEncontradoException;
}
