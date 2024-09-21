
package nc.diedtp;

import java.util.ArrayList;


public interface ItemPedidoDAO {
    ArrayList<ItemPedido> busquedaPorRango(float piso, float tope);
    ArrayList<ItemPedido> buscarPorVendedor(int id);
    ArrayList<ItemPedido> filtrarCliente(int id);
    void filtrarCategoria(); // no se implemento;
    ArrayList<ItemPedido> OrdenarPorCantidadASC (int idPedido);
}
