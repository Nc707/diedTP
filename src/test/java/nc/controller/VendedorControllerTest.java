package nc.controller;

import nc.dao.VendedorDAO;
import nc.modelo.Vendedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VendedorControllerTest {

    private VendedorController controller;
    private VendedorDAO mockVendedorDAO;

    @BeforeEach
    public void setUp() {
        controller = new VendedorController();

        // Mock manual de VendedorDAO
        mockVendedorDAO = new VendedorDAO() {
            private List<Vendedor> vendedores = new ArrayList<>();

            @Override
            public ArrayList<Vendedor> listar() {
                return new ArrayList<>(vendedores);
            }

            @Override
            public void add(Vendedor vendedor) {
                vendedores.add(vendedor);
            }

            @Override
            public void actualizar(Vendedor vendedor) {
                for (int i = 0; i < vendedores.size(); i++) {
                    if (vendedores.get(i).getId() == vendedor.getId()) {
                        vendedores.set(i, vendedor);
                        break;
                    }
                }
            }

            @Override
            public void eliminar(int id) {
                vendedores.removeIf(vendedor -> vendedor.getId() == id);
            }

            @Override
            public Vendedor buscar(int id) {
                return vendedores.stream()
                        .filter(vendedor -> vendedor.getId() == id)
                        .findFirst()
                        .orElse(null);
            }

            @Override
            public Vendedor crear(String nombre, String direccion, double cx, double cy) {
                Vendedor vendedor = new Vendedor(vendedores.size() + 1, nombre, direccion, cx, cy);
                add(vendedor);
                return vendedor;
            }
        };

        controller.setVendedorDAO(mockVendedorDAO); // Asigna el mock manual
    }

    @Test
    public void testLoadData() {
        Vendedor vendedor1 = new Vendedor(1, "Vendedor Uno", "Calle Falsa 123", -34.6037, -58.3816);
        Vendedor vendedor2 = new Vendedor(2, "Vendedor Dos", "Av. Siempreviva 742", -34.6118, -58.4173);
        mockVendedorDAO.add(vendedor1);
        mockVendedorDAO.add(vendedor2);

        List<List> data = controller.loadData();

        assertEquals(2, data.size());
        assertEquals("Vendedor Uno", data.get(0).get(1));
        assertEquals("Vendedor Dos", data.get(1).get(1));
    }

    @Test
    public void testGetVendedor() {
        Vendedor vendedor = new Vendedor(1, "Vendedor Uno", "Calle Falsa 123", -34.6037, -58.3816);
        mockVendedorDAO.add(vendedor);

        List vendedorInfo = controller.getVendedor(1);

        assertNotNull(vendedorInfo);
        assertEquals(1, vendedorInfo.get(0));
        assertEquals("Vendedor Uno", vendedorInfo.get(1));
        assertEquals("Calle Falsa 123", vendedorInfo.get(2));
    }

    @Test
    public void testCrearVendedor() {
        Vendedor vendedor = controller.crear("Nuevo Vendedor", "Dirección Nueva", -34.7000, -58.5000);

        assertNotNull(vendedor);
        assertEquals("Nuevo Vendedor", vendedor.getNombre());
        assertEquals(1, mockVendedorDAO.listar().size());
    }

    @Test
    public void testModificarVendedor() {
        Vendedor vendedor = controller.crear("Vendedor Viejo", "Dirección Vieja", -34.6000, -58.4000);
        controller.modificarVendedor(vendedor.getId(), "Vendedor Actualizado", "Dirección Actualizada", -34.7000, -58.5000);

        Vendedor updatedVendedor = mockVendedorDAO.buscar(vendedor.getId());
        assertNotNull(updatedVendedor);
        assertEquals("Vendedor Actualizado", updatedVendedor.getNombre());
        assertEquals("Dirección Actualizada", updatedVendedor.getDireccion());
    }

    @Test
    public void testEliminarVendedor() {
        Vendedor vendedor = controller.crear("Vendedor para Eliminar", "Dirección", -34.6000, -58.4000);
        assertEquals(1, mockVendedorDAO.listar().size());

        mockVendedorDAO.eliminar(vendedor.getId());
        assertEquals(0, mockVendedorDAO.listar().size());
    }
}
