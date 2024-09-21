package nc.diedtp;

import java.util.ArrayList;
import nc.diedtp.excepciones.*;

public interface ItemPedidoDAO {

    ArrayList<ItemPedido> busquedaPorRango(float piso, float tope) throws ItemNoEncontradoException;

    ArrayList<ItemPedido> buscarPorVendedor(int id) throws ItemNoEncontradoException;

    ArrayList<ItemPedido> filtrarCliente(int id) throws ItemNoEncontradoException;

    void filtrarCategoria(); // no se implemento;

    ArrayList<ItemPedido> OrdenarPorCantidadASC(int idPedido) throws PedidoNoEncontradoException;
}
