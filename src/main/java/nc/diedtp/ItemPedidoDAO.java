
package nc.diedtp;

import java.util.ArrayList;


public interface ItemPedidoDAO {
    ArrayList<ItemPedido> busquedaPorRango(float piso, float tope);
    void buscarPorRestaurante(Vendedor vendedor);
    void filtrarId(int id);
    void filtrarCliente(Cliente cliente);
    void filtrarCategoria();
}
