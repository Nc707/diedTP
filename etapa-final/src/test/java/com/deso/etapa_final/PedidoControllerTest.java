package com.deso.etapa_final;

import com.deso.etapa_final.controllers.PedidoController;
import com.deso.etapa_final.model.Pedido;
import com.deso.etapa_final.services.CarritoService;
import com.deso.etapa_final.services.PedidoService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @Mock
    private CarritoService carritoService;

    @InjectMocks
    private PedidoController pedidoController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();
    }

    @Test
    void testGetAllPedidos() throws Exception {
        Pedido pedido1 = new Pedido();
        pedido1.setPedidoid(1L);
        pedido1.setDescripcion("Pedido 1");

        Pedido pedido2 = new Pedido();
        pedido2.setPedidoid(2L);
        pedido2.setDescripcion("Pedido 2");

        when(pedidoService.getAllPedidos()).thenReturn(Arrays.asList(pedido1, pedido2));

        mockMvc.perform(get("/pedidos/getAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("pedidos-listado"))
                .andExpect(model().attributeExists("pedidos"));
        
        verify(pedidoService, times(1)).getAllPedidos();
    }

    @Test
    void testGetPedidoById() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setPedidoid(1L);
        pedido.setDescripcion("Pedido de prueba");

        when(pedidoService.obtenerPedidoPorId(1L)).thenReturn(pedido);

        mockMvc.perform(get("/pedidos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pedidoid").value(1))
                .andExpect(jsonPath("$.descripcion").value("Pedido de prueba"));

        verify(pedidoService, times(1)).obtenerPedidoPorId(1L);
    }

    @Test
    void testCrearCarrito() throws Exception {
        when(carritoService.crearCarrito(1L, 2L)).thenReturn(123L);

        mockMvc.perform(post("/pedidos/carrito/crearCarrito/1/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("123"));

        verify(carritoService, times(1)).crearCarrito(1L, 2L);
    }

    @Test
    void testAgregarItem() throws Exception {
        doNothing().when(carritoService).agregarItem(1L, 101L, 5);

        mockMvc.perform(post("/pedidos/carrito/1/agregarItem/101/5"))
                .andExpect(status().isOk());

        verify(carritoService, times(1)).agregarItem(1L, 101L, 5);
    }

    @Test
    void testEliminarItem() throws Exception {
        doNothing().when(carritoService).eliminarItem(1L, 202L);

        mockMvc.perform(delete("/pedidos/carrito/1/eliminarItem/202"))
                .andExpect(status().isOk());

        verify(carritoService, times(1)).eliminarItem(1L, 202L);
    }

    @Test
    void testAvanzarEstadoPedido() throws Exception {
        doNothing().when(pedidoService).avanzarEstadoPedido(1L);

        mockMvc.perform(post("/pedidos/avanzarEstado").param("pedidoId", "1"))
                .andExpect(status().isOk());

        verify(pedidoService, times(1)).avanzarEstadoPedido(1L);
    }
}
