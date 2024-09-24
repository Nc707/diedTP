package ItemPedidoManagement;

import nc.diedtp.ItemPedido;
import java.util.ArrayList;
import nc.diedtp.excepciones.*;

public interface ItemPedidoDAO {

    ArrayList<ItemPedido> busquedaPorPrecio(float piso, float tope) throws ItemNoEncontradoException;

    ArrayList<ItemPedido> busquedaPorVendedor(int id) throws ItemNoEncontradoException;

    ArrayList<ItemPedido> busquedaPorCliente(int id) throws ItemNoEncontradoException;

    void filtrarCategoria(); // no se implemento;

    ArrayList<ItemPedido> OrdenarPorCantidadASC(int idPedido) throws PedidoNoEncontradoException;
}
