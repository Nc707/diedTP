package com.deso.etapa_final;
import com.deso.etapa_final.controllers.ClienteController;
import com.deso.etapa_final.model.Cliente;
import com.deso.etapa_final.model.Coordenada;
import com.deso.etapa_final.services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @Mock
    private Model model;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCliente() {
        String nombre = "Juan";
        long cuit = 123456789;
        String email = "juan@example.com";
        String direccion = "Calle Falsa 123";
        double latitud = -34.6037;
        double longitud = -58.3816;

        Coordenada coordenada = new Coordenada(latitud, longitud);
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setCuit(cuit);
        cliente.setEmail(email);
        cliente.setDireccion(direccion);
        cliente.setCoordenadas(coordenada);

        when(clienteService.addCliente(cuit, email, direccion, coordenada, nombre)).thenReturn(cliente);

        String viewName = clienteController.addCliente(nombre, cuit, email, direccion, latitud, longitud);

        assertEquals("redirect:/clientes/getAll", viewName);
    }

    @Test
    void testGetClientes() {
        List<Cliente> clientes = new ArrayList<>();
        when(clienteService.getClientes()).thenReturn(clientes);

        String viewName = clienteController.getClientes(model);

        verify(model).addAttribute("clientes", clientes);
        assertEquals("clientes-listado", viewName);
    }

    @Test
    void testGetClienteByIdFound() {
        Long id = 1L;
        Cliente cliente = new Cliente();
        when(clienteService.getClienteById(id)).thenReturn(cliente);

        ResponseEntity<Cliente> response = clienteController.getClienteById(id);

        assertEquals(ResponseEntity.ok(cliente), response);
    }

    @Test
    void testGetClienteByIdNotFound() {
        Long id = 1L;
        when(clienteService.getClienteById(id)).thenReturn(null);

        ResponseEntity<Cliente> response = clienteController.getClienteById(id);

        assertEquals(ResponseEntity.notFound().build(), response);
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
        cliente.setNombre(nombre);

        when(clienteService.updateCliente(id, cuit, email, direccion, coordenadas, nombre)).thenReturn(cliente);

        ResponseEntity<Cliente> response = clienteController.updateCliente(id, cuit, email, direccion, coordenadas, nombre);

        assertEquals(ResponseEntity.ok(cliente), response);
    }

}
