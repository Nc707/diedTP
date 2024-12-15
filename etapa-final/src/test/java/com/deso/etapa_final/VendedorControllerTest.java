package com.deso.etapa_final;

import com.deso.etapa_final.controllers.VendedorController;
import com.deso.etapa_final.model.Coordenada;
import com.deso.etapa_final.model.Vendedor;
import com.deso.etapa_final.services.VendedorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.http.ResponseEntity;

class VendedorControllerTest {

    @Mock
    private VendedorService vendedorService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private VendedorController vendedorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddVendedor() {
        String nombre = "John Doe";
        String direccion = "Calle Falsa 123";
        double latitud = -34.603722;
        double longitud = -58.381592;

        Vendedor mockVendedor = new Vendedor(nombre, direccion, null);

        when(vendedorService.addVendedor(nombre, direccion, latitud, longitud)).thenReturn(mockVendedor);

        String result = vendedorController.addVendedor(nombre, direccion, latitud, longitud, redirectAttributes);

        assertEquals("redirect:/vendedores/getAll", result);
        verify(vendedorService, times(1)).addVendedor(nombre, direccion, latitud, longitud);
    }

    @Test
    void testGetVendedores() {
        Iterable<Vendedor> vendedores = Arrays.asList(
                new Vendedor("John Doe", "Calle 123", null),
                new Vendedor("Jane Doe", "Calle 456", null)
        );

        when(vendedorService.getVendedores()).thenReturn(vendedores);

        String result = vendedorController.getVendedores(model);

        assertEquals("vendedores-listado", result);
        verify(model, times(1)).addAttribute("vendedores", vendedores);
        verify(vendedorService, times(1)).getVendedores();
    }

    @Test
    void testGetVendedorById() {
        long id = 1L;
        Vendedor vendedor = new Vendedor("John Doe", "Calle 123", null);

        when(vendedorService.getVendedorById(id)).thenReturn(vendedor);

        Vendedor result = vendedorController.getVendedorById(id);

        assertEquals(vendedor, result);
        verify(vendedorService, times(1)).getVendedorById(id);
    }

    @Test
    void testUpdateVendedor() {
        long id = 1L;
        String nombre = "John Doe";
        String direccion = "Calle Falsa 123";
        double latitud = -34.603722;
        double longitud = -58.381592;

        Vendedor mockUpdatedVendedor = new Vendedor(nombre, direccion, new Coordenada(latitud, longitud));
        mockUpdatedVendedor.setVendedorid(id);

        when(vendedorService.updateVendedor(id, nombre, direccion, latitud, longitud)).thenReturn(mockUpdatedVendedor);

        ResponseEntity<Vendedor> result = vendedorController.updateVendedor(id, nombre, direccion, latitud, longitud);

        ResponseEntity<Vendedor> expectedResponse = ResponseEntity.ok(mockUpdatedVendedor);
        assertEquals(expectedResponse, result);
        verify(vendedorService, times(1)).updateVendedor(id, nombre, direccion, latitud, longitud);
    }


    @Test
    void testDistancia() {
        long vendedorId = 1L;
        long clienteId = 2L;
        double mockDistancia = 10.5;

        when(vendedorService.distancia(vendedorId, clienteId)).thenReturn(mockDistancia);

        String result = vendedorController.distancia(vendedorId, clienteId);

        assertEquals("La distancia entre el vendedor y el cliente es: 10.5", result);
        verify(vendedorService, times(1)).distancia(vendedorId, clienteId);
    }
}
