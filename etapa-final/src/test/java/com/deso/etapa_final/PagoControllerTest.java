package com.deso.etapa_final;

import com.deso.etapa_final.controllers.PagoController;
import com.deso.etapa_final.model.Pago;
import com.deso.etapa_final.model.Pedido;
import com.deso.etapa_final.model.Cliente;
import com.deso.etapa_final.model.Vendedor;
import com.deso.etapa_final.services.PagoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PagoControllerTest {

    @Mock
    private PagoService pagoService;

    @InjectMocks
    private PagoController pagoController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pagoController).build();
    }
@Test
void testPagoResumen() {
    Cliente cliente = new Cliente();
    cliente.setNombre("Juan Perez");

    Vendedor vendedor = new Vendedor();
    vendedor.setNombre("Maria Lopez");

    Pedido pedido = new Pedido();
    pedido.setPedidoid(1L);
    pedido.setCliente(cliente);
    pedido.setVendedor(vendedor);
    pedido.setPrecio(500.0);

    Pago pago = new Pago(pedido);
    pago.setId(1L);
    pago.setFechaPago(new Date());

    String resumen = pago.getResumen();
    System.out.println(resumen);

    assertNotNull(resumen);
    assertTrue(resumen.contains("Juan Perez"));
    assertTrue(resumen.contains("Maria Lopez"));
}
    @Test
    void testGetAllPagos() throws Exception {
        // Mock de datos de prueba
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan Perez");

        Vendedor vendedor = new Vendedor();
        vendedor.setNombre("Maria Lopez");

        Pedido pedido = new Pedido();
        pedido.setPedidoid(1L);
        pedido.setCliente(cliente);
        pedido.setVendedor(vendedor);
        pedido.setPrecio(500.0);

        Pago pago1 = new Pago(pedido);
        pago1.setId(1L);
        pago1.setFechaPago(new Date());

        Pago pago2 = new Pago(pedido);
        pago2.setId(2L);
        pago2.setFechaPago(new Date());

        when(pagoService.getAllPagos()).thenReturn(Arrays.asList(pago1, pago2));

        // Ejecución del endpoint y validación
        mockMvc.perform(get("/pagos/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(pago1.getResumen()))
                .andExpect(jsonPath("$[1]").value(pago2.getResumen()));

        verify(pagoService, times(1)).getAllPagos();
    }
}
