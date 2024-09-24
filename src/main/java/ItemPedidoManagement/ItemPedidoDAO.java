package ItemPedidoManagement;

import nc.diedtp.ItemPedido;
import java.util.ArrayList;
import nc.diedtp.excepciones.*;

public interface ItemPedidoDAO {

    enum tipoFiltrado{
        PEDIDO,
        CLIENTE,
        CATEGORIAS,
        PRECIO_TOPE_ITEMPEDIDO,
        PRECIO_TOPE_PEDIDO,
        PRECIO_TOPE_ITEMMENU,
        PRECIO_MINIMO_ITEMPEDIDO,
        PRECIO_MINIMO_PEDIDO,
        PRECIO_MINIMO_ITEMMENU,
        ITEMMENU,
        VENDEDOR
        
    }
    enum tipoFiltradoRango{
    
        PRECIO_ITEMPEDIDO,
        PRECIO_PEDIDO,
        PRECIO_ITEMMENU   
    }
    enum tipoOrdenamiento{
        PEDIDO,
        CLIENTE,
        PRECIO_ITEMPEDIDO,
        PRECIO_PEDIDO,
        PRECIO_ITEMMENU
    }
    public ItemPedido filtrarPor(tipoFiltrado tipoFiltro, Object filtro);
    public ItemPedido filtrarRango(tipoFiltradoRango tipoFiltrado, Object piso, Object tope);
}
