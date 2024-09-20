
package nc.diedtp;


public interface ItemPedidoDAO {
    void buscqedaPorRango(int x, int y);
    void buscarPorRestaurante(Vendedor vendedor);
    void filtrarId(int id);
    void filtrarCliente(Cliente cliente);
    void filtrarCategoria();
}
