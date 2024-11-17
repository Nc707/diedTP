package nc.controller;

import nc.controller.PedidoController;
import nc.dao.PedidoDAO;
import nc.modelo.Cliente;
import nc.modelo.Pedido;
import nc.modelo.Pedido.EstadoPedido;
import nc.modelo.Vendedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoControllerTest {

    private PedidoController controller;
    private PedidoDAO mockPedidoDAO;

    @BeforeEach
    public void setUp() {
        // Mock manual de PedidoDAO
        mockPedidoDAO = new PedidoDAO() {
            private List<Pedido> pedidos = new ArrayList<>();

            @Override
            public ArrayList<Pedido> listar() {
                return new ArrayList<>(pedidos);
            }

            @Override
            public ArrayList<Pedido> listarPorVendedor(int idVendedor) {
                ArrayList<Pedido> result = new ArrayList<>();
                for (Pedido p : pedidos) {
                    if (p.getVendedor().getId() == idVendedor) {
                        result.add(p);
                    }
                }
                return result;
            }

            @Override
            public ArrayList<Pedido> listarPorCliente(int idCliente) {
                ArrayList<Pedido> result = new ArrayList<>();
                for (Pedido p : pedidos) {
                    if (p.getCliente().getId() == idCliente) {
                        result.add(p);
                    }
                }
                return result;
            }

            @Override
            public ArrayList<Pedido> listarPorEstadoYVendedor(int idVendedor, EstadoPedido estado) {
                ArrayList<Pedido> result = new ArrayList<>();
                for (Pedido p : pedidos) {
                    if (p.getVendedor().getId() == idVendedor && p.getEstado() == estado) {
                        result.add(p);
                    }
                }
                return result;
            }

            @Override
            public ArrayList<Pedido> listarPorEstadoYCliente(int idCliente, EstadoPedido estado) {
                ArrayList<Pedido> result = new ArrayList<>();
                for (Pedido p : pedidos) {
                    if (p.getCliente().getId() == idCliente && p.getEstado() == estado) {
                        result.add(p);
                    }
                }
                return result;
            }

            @Override
            public void add(Pedido pedido) {
                pedidos.add(pedido);
            }

            @Override
            public void actualizar(Pedido pedido) {
                for (int i = 0; i < pedidos.size(); i++) {
                    if (pedidos.get(i).getId() == pedido.getId()) {
                        pedidos.set(i, pedido);
                        break;
                    }
                }
            }

            @Override
            public void eliminar(int id) {
                pedidos.removeIf(p -> p.getId() == id);
            }

            @Override
            public Pedido buscar(int id) {
                return pedidos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
            }

            @Override
            public Pedido crear(Vendedor vendedor, Cliente cliente) {
                Pedido pedido = new Pedido(pedidos.size() + 1, vendedor.getId(), cliente.getId(), 0.0f, EstadoPedido.EN_CARRITO);
                add(pedido);
                return pedido;
            }
        };

        // Asignar el DAO mock al controlador
        controller = new PedidoController(1, true);
        controller.setPedidos(mockPedidoDAO);
    }

    @Test
    public void testLoadData() {
        Cliente cliente = new Cliente(1, "Cliente Uno", 123456789L, "cliente1@example.com", "Calle 1", -34.6037, -58.3816);
        Vendedor vendedor = new Vendedor(1, "Vendedor Uno", "Direccion 1", -34.6037, -58.3816);
        Pedido pedido1 = mockPedidoDAO.crear(vendedor, cliente);
        Pedido pedido2 = mockPedidoDAO.crear(vendedor, cliente);

        List<List> data = controller.loadData();

        assertEquals(2, data.size());
        assertEquals(1, data.get(0).get(0));
        assertEquals("Cliente Uno", data.get(0).get(1));
    }

    @Test
    public void testCrearPedido() {
        Cliente cliente = new Cliente(1, "Cliente Uno", 123456789L, "cliente1@example.com", "Calle 1", -34.6037, -58.3816);
        Vendedor vendedor = new Vendedor(1, "Vendedor Uno", "Direccion 1", -34.6037, -58.3816);

        Pedido nuevoPedido = mockPedidoDAO.crear(vendedor, cliente);

        assertNotNull(nuevoPedido);
        assertEquals(1, mockPedidoDAO.listar().size());
        assertEquals(EstadoPedido.EN_CARRITO, nuevoPedido.getEstado());
    }

    @Test
    public void testEliminarPedido() {
        Cliente cliente = new Cliente(1, "Cliente Uno", 123456789L, "cliente1@example.com", "Calle 1", -34.6037, -58.3816);
        Vendedor vendedor = new Vendedor(1, "Vendedor Uno", "Direccion 1", -34.6037, -58.3816);
        Pedido pedido = mockPedidoDAO.crear(vendedor, cliente);

        assertEquals(1, mockPedidoDAO.listar().size());

        mockPedidoDAO.eliminar(pedido.getId());

        assertEquals(0, mockPedidoDAO.listar().size());
    }

   /* @Test
    public void testModificarPedido() {
        Cliente cliente = new Cliente(1, "Cliente Uno", 123456789L, "cliente1@example.com", "Calle 1", -34.6037, -58.3816);
        Vendedor vendedor = new Vendedor(1, "Vendedor Uno", "Direccion 1", -34.6037, -58.3816);
        Pedido pedido = mockPedidoDAO.crear(vendedor, cliente);
        pedido.setEstado(EstadoPedido.RECIBIDO);

        mockPedidoDAO.actualizar(pedido);

        Pedido updatedPedido = mockPedidoDAO.buscar(pedido.getId());
        assertNotNull(updatedPedido);
        assertEquals(EstadoPedido.RECIBIDO, updatedPedido.getEstado());
    }*/
}
