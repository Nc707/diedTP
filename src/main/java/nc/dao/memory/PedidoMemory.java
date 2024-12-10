package nc.dao.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nc.dao.PedidoDAO;
import nc.modelo.Cliente;
import nc.modelo.Pedido;
import nc.modelo.Pedido.EstadoPedido;
import nc.modelo.Vendedor;

/**
 *
 * @author lucia
 */
public class PedidoMemory implements PedidoDAO {

    ArrayList<Pedido> pedidos = new ArrayList<>();
    
    private static PedidoMemory uniqueInstance;
    
    public static PedidoMemory getPedidoMemory() {
        if (uniqueInstance == null) {
            uniqueInstance = new PedidoMemory();
        }
        return uniqueInstance;
    }
    PedidoMemory(){}
    
    @Override
    public ArrayList<Pedido> listar() {
        return pedidos;
    }

    @Override
    public ArrayList<Pedido> listarPorVendedor(int idVendedor) {
        return pedidos.stream()
                .filter(pedido -> pedido.getVendedorId() == idVendedor)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<Pedido> listarPorCliente(int idCliente) {
        return pedidos.stream()
                .filter(pedido -> pedido.getClienteId() == idCliente)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<Pedido> listarPorEstadoYVendedor(int idVendedor, EstadoPedido estado) {
        return pedidos.stream()
                .filter(pedido -> pedido.getVendedorId() == idVendedor && pedido.getEstado() == estado)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<Pedido> listarPorEstadoYCliente(int idCliente, EstadoPedido estado) {
        return pedidos.stream()
                .filter(pedido -> pedido.getClienteId() == idCliente && pedido.getEstado() == estado)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public int add(Pedido pedido) {
        pedidos.add(pedido);
        return pedido.getId();
    }

    @Override
    public void actualizar(Pedido pedido) {
        pedidos.stream()
                .filter(p -> p.getId() == pedido.getId())
                .findFirst()
                .ifPresent(p -> {
                    int index = pedidos.indexOf(p);
                    pedidos.set(index, pedido);
                });
    }

    @Override
    public void eliminar(int id) {
        pedidos.removeIf(pedido -> pedido.getId() == id);
    }

    @Override
    public Pedido buscar(int id) {
        return pedidos.stream()
                .filter(pedido -> pedido.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Pedido crear(Vendedor vendedor, Cliente cliente) {
        Pedido pedido = new Pedido(vendedor, cliente);
        return pedido;
    }

    

}
