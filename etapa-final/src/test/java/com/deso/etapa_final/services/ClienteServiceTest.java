package com.deso.etapa_final.services;

import com.deso.etapa_final.model.Cliente;
import com.deso.etapa_final.model.Coordenada;
import com.deso.etapa_final.model.Pago;
import com.deso.etapa_final.model.Pedido;
import com.deso.etapa_final.repositories.ClienteRepository;
import com.deso.etapa_final.repositories.PagoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCliente() {
        long cuit = 123456789;
        String email = "test@example.com";
        String direccion = "Calle Falsa 123";
        Coordenada coordenadas = new Coordenada(-34.6037, -58.3816);
        String nombre = "Juan";

        Cliente cliente = new Cliente();
        cliente.setCuit(cuit);
        cliente.setEmail(email);
        cliente.setDireccion(direccion);
        cliente.setCoordenadas(coordenadas);
        cliente.setNombre(nombre);

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente result = clienteService.addCliente(cuit, email, direccion, coordenadas, nombre);

        assertEquals(cliente, result);
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    void testGetClientes() {
        List<Cliente> clientes = Arrays.asList(new Cliente(), new Cliente());
        when(clienteRepository.findAll()).thenReturn(clientes);

        Iterable<Cliente> result = clienteService.getClientes();

        assertEquals(clientes, result);
        verify(clienteRepository).findAll();
    }
    @Test
    void testGeneralSearch() {
        String searchable = "Juan";
        List<Cliente> clientesByNombre = List.of(new Cliente());
        when(clienteRepository.findByNombreContaining(searchable)).thenReturn(clientesByNombre);
        when(clienteRepository.findByDireccionContaining(searchable)).thenReturn(Collections.emptyList());
        when(clienteRepository.findByEmailContaining(searchable)).thenReturn(Collections.emptyList());

        Iterable<Cliente> result = clienteService.generalSearch(searchable, "nombre", "ASC");

        assertIterableEquals(clientesByNombre, result);
        verify(clienteRepository).findByNombreContaining(searchable);
        verify(clienteRepository).findByDireccionContaining(searchable);
        verify(clienteRepository).findByEmailContaining(searchable);
    }
    @Test
    void testGetClienteByIdFound() {
        Long id = 1L;
        Cliente cliente = new Cliente();
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        Cliente result = clienteService.getClienteById(id);

        assertEquals(cliente, result);
        verify(clienteRepository).findById(id);
    }
    @Test
    void testGetClienteByIdNotFound() {
        Long id = 1L;
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        Cliente result = clienteService.getClienteById(id);

        assertNull(result);
        verify(clienteRepository).findById(id);
    }
    @Test
    void testUpdateCliente() {
        Long id = 1L;
        long cuit = 987654321;
        String email = "updated@example.com";
        String direccion = "Nueva Direccion";
        Coordenada coordenadas = new Coordenada(-34.0, -58.0);
        String nombre = "Juan Actualizado";

        Cliente cliente = new Cliente();
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente updated = clienteService.updateCliente(id, cuit, email, direccion, coordenadas, nombre);

        assertEquals(cuit, updated.getCuit());
        assertEquals(email, updated.getEmail());
        assertEquals(direccion, updated.getDireccion());
        assertEquals(coordenadas, updated.getCoordenadas());
        assertEquals(nombre, updated.getNombre());
        verify(clienteRepository).findById(id);
        verify(clienteRepository).save(any(Cliente.class));
    }
    @Test
    void testDeleteCliente() {
        Long id = 1L;
        doNothing().when(clienteRepository).deleteById(id);

        clienteService.deleteCliente(id);

        verify(clienteRepository).deleteById(id);
    }
    @Test
    void testGenerarPago() {
        Pedido pedido = new Pedido();
        Pago pago = new Pago(pedido);

        when(pagoRepository.save(any(Pago.class))).thenReturn(pago);

        clienteService.generarPago(pedido);

        verify(pagoRepository).save(any(Pago.class));
    }
}
