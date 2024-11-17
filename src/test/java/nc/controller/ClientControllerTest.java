package nc.controller;

import nc.dao.ClienteDAO;
import nc.modelo.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClientControllerTest {

    private ClientController controller;
    private ClienteDAO mockClienteDAO;

    @BeforeEach
    public void setUp() {
        controller = new ClientController();

        // Mock manual de ClienteDAO
        mockClienteDAO = new ClienteDAO() {
            private List<Cliente> clientes = new ArrayList<>();

            @Override
            public ArrayList<Cliente> listar() {
                return new ArrayList<>(clientes);
            }

            @Override
            public void add(Cliente cliente) {
                clientes.add(cliente);
            }

            @Override
            public void actualizar(Cliente cliente) {
                for (int i = 0; i < clientes.size(); i++) {
                    if (clientes.get(i).getId() == cliente.getId()) {
                        clientes.set(i, cliente);
                        break;
                    }
                }
            }

            @Override
            public void eliminar(int id) {
                clientes.removeIf(cliente -> cliente.getId() == id);
            }

            @Override
            public Cliente buscar(int id) {
                return clientes.stream()
                        .filter(cliente -> cliente.getId() == id)
                        .findFirst()
                        .orElse(null);
            }
        };

        controller.setClienteDAO(mockClienteDAO); // Asigna el mock manual
    }

    @Test
    public void testGetCliente() {
        Cliente clienteMock = new Cliente(1, "John Doe", 123456789L, "john@example.com", "123 Main St", -34.6037, -58.3816);
        mockClienteDAO.add(clienteMock);

        List clienteInfo = controller.getCliente(1);

        assertNotNull(clienteInfo);
        assertEquals(1, clienteInfo.get(0));
        assertEquals("John Doe", clienteInfo.get(1));
        assertEquals("123 Main St", clienteInfo.get(2));
    }

    @Test
    public void testLoadData() {
        Cliente clienteMock1 = new Cliente(1, "John Doe", 123456789L, "john@example.com", "123 Main St", -34.6037, -58.3816);
        Cliente clienteMock2 = new Cliente(2, "Jane Smith", 987654321L, "jane@example.com", "456 Elm St", -34.6118, -58.4173);
        mockClienteDAO.add(clienteMock1);
        mockClienteDAO.add(clienteMock2);

        List<List> data = controller.loadData();

        assertEquals(2, data.size());
        assertEquals("John Doe", data.get(0).get(1));
        assertEquals("Jane Smith", data.get(1).get(1));
    }

    @Test
    public void testCrearCliente() {
        Cliente cliente = controller.crear("John Doe", 123456789, "john@example.com", "123 Main St", -34.6037, -58.3816);

        assertNotNull(cliente);
        assertEquals("John Doe", cliente.getNombre());
        assertEquals(1, mockClienteDAO.listar().size());
    }

    @Test
    public void testModificarCliente() {
        Cliente cliente = controller.crear("John Doe", 123456789, "john@example.com", "123 Main St", -34.6037, -58.3816);
        controller.modificarCliente(cliente.getId(), "John Updated", "New Address", "new@example.com", 987654321, -34.6118, -58.4173);

        Cliente updatedCliente = mockClienteDAO.buscar(cliente.getId());
        assertEquals("John Updated", updatedCliente.getNombre());
        assertEquals("New Address", updatedCliente.getDireccion());
        assertEquals("new@example.com", updatedCliente.getEmail());
    }

    @Test
    public void testEliminarCliente() {
        Cliente cliente = controller.crear("John Doe", 123456789, "john@example.com", "123 Main St", -34.6037, -58.3816);
        controller.setCliente(cliente.getId());

        mockClienteDAO.eliminar(cliente.getId());
        Cliente deletedCliente = mockClienteDAO.buscar(cliente.getId());
        assertNull(deletedCliente);
    }

    @Test
    public void testGetObjetCliente() {
        Cliente clienteMock = new Cliente(1, "John Doe", 123456789L, "john@example.com", "123 Main St", -34.6037, -58.3816);
        mockClienteDAO.add(clienteMock);

        Cliente cliente = controller.getObjetCliente(1);

        assertNotNull(cliente);
        assertEquals("John Doe", cliente.getNombre());
    }

    @Test
    public void testSetCliente() {
        Cliente clienteMock = new Cliente(1, "John Doe", 123456789L, "john@example.com", "123 Main St", -34.6037, -58.3816);
        mockClienteDAO.add(clienteMock);

        controller.setCliente(1);
        Cliente cliente = controller.getObjetCliente(1);

        assertNotNull(cliente);
        assertEquals(1, cliente.getId());
        assertEquals("John Doe", cliente.getNombre());
    }
}
