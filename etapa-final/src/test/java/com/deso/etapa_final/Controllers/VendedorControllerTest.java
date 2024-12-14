package com.deso.etapa_final.Controllers;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import com.deso.etapa_final.model.Coordenada;
import com.deso.etapa_final.model.Vendedor;
import com.deso.etapa_final.services.VendedorService;

import jakarta.persistence.EntityNotFoundException;

import com.deso.etapa_final.controllers.VendedorController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

class VendedorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VendedorService vendedorService;

    @InjectMocks
    private VendedorController vendedorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendedorController).build();
    }

    @Test
    void testAddVendedor() throws Exception {
        // Arrange
        Vendedor vendedor = new Vendedor("Juan", "Calle 123", new Coordenada(10.0, 20.0));
        when(vendedorService.addVendedor(anyString(), anyString(), anyDouble(), anyDouble())).thenReturn(vendedor);

        // Act & Assert
        mockMvc.perform(post("/vendedores/add")
                .param("nombre", "Juan")
                .param("direccion", "Calle 123")
                .param("latitud", "10.0")
                .param("longitud", "20.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/vendedores/getAll"));

        verify(vendedorService, times(1)).addVendedor("Juan", "Calle 123", 10.0, 20.0);
    }

    @Test
    void testGetVendedores() throws Exception {
        // Arrange
        Vendedor vendedor1 = new Vendedor("Juan", "Calle 123", new Coordenada(10.0, 20.0));
        Vendedor vendedor2 = new Vendedor("Pedro", "Calle 456", new Coordenada(30.0, 40.0));
        when(vendedorService.getVendedores()).thenReturn(Arrays.asList(vendedor1, vendedor2));

        // Act & Assert
        mockMvc.perform(get("/vendedores/getAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("vendedores-listado"))
                .andExpect(model().attributeExists("vendedores"));

        verify(vendedorService, times(1)).getVendedores();
    }

    @Test
    void testSearchVendedores() throws Exception {
        // Arrange
        Vendedor vendedor = new Vendedor("Juan", "Calle 123", new Coordenada(10.0, 20.0));
        when(vendedorService.generalSearch("Juan", "nombre", "ASC")).thenReturn(Arrays.asList(vendedor));

        // Act & Assert
        mockMvc.perform(get("/vendedores/search")
                .param("search", "Juan")
                .param("orderBy", "nombre")
                .param("orderDirection", "ASC"))
                .andExpect(status().isOk())
                .andExpect(view().name("vendedores-listado"))
                .andExpect(model().attributeExists("vendedores"));

        verify(vendedorService, times(1)).generalSearch("Juan", "nombre", "ASC");
    }

    @Test
    void testGetVendedorById() throws Exception {
        // Arrange
        Vendedor vendedor = new Vendedor("Juan", "Calle 123", new Coordenada(10.0, 20.0));
        when(vendedorService.getVendedorById(1L)).thenReturn(vendedor);

        // Act & Assert
        mockMvc.perform(get("/vendedores/getById").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'nombre':'Juan','direccion':'Calle 123'}"));

        verify(vendedorService, times(1)).getVendedorById(1L);
    }

    @Test
    void testMostrarFormularioAgregar() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/vendedores/agregar-vendedor"))
                .andExpect(status().isOk())
                .andExpect(view().name("agregar-vendedor"));
    }

    @Test
    void testUpdateVendedor() throws Exception {
        // Arrange
        Vendedor vendedor = new Vendedor("Juan Actualizado", "Calle Nueva", new Coordenada(15.0, 25.0));
        when(vendedorService.updateVendedor(1L, "Juan Actualizado", "Calle Nueva", 15.0, 25.0)).thenReturn(vendedor);

        // Act & Assert
        mockMvc.perform(post("/vendedores/update")
                .param("id", "1")
                .param("nombre", "Juan Actualizado")
                .param("direccion", "Calle Nueva")
                .param("latitud", "15.0")
                .param("longitud", "25.0"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'nombre':'Juan Actualizado','direccion':'Calle Nueva'}"));

        verify(vendedorService, times(1)).updateVendedor(1L, "Juan Actualizado", "Calle Nueva", 15.0, 25.0);
    }

    @Test
    void testDeleteVendedor() throws Exception {
        // Arrange
        doNothing().when(vendedorService).deleteVendedor(1L);

        // Act & Assert
        mockMvc.perform(delete("/vendedores/delete").param("id", "1"))
                .andExpect(status().isOk());

        verify(vendedorService, times(1)).deleteVendedor(1L);
    }

    @Test
void testAddVendedor_InvalidData() throws Exception {
    // Act & Assert
    mockMvc.perform(post("/vendedores/add")
            .param("nombre", "") // Nombre vacío
            .param("direccion", "Calle 123")
            .param("latitud", "10.0")
            .param("longitud", "20.0"))
            .andExpect(status().isBadRequest());

    verify(vendedorService, never()).addVendedor(anyString(), anyString(), anyDouble(), anyDouble());
}

@Test
void testGetVendedorById_NotFound() throws Exception {
    // Arrange
    when(vendedorService.getVendedorById(999L)).thenReturn(null);

    // Act & Assert
    mockMvc.perform(get("/vendedores/getById").param("id", "999"))
            .andExpect(status().isNotFound());

    verify(vendedorService, times(1)).getVendedorById(999L);
}

@Test
void testDeleteVendedor_NotFound() throws Exception {
    // Arrange
    doThrow(new EntityNotFoundException("Vendedor no encontrado")).when(vendedorService).deleteVendedor(999L);

    // Act & Assert
    mockMvc.perform(delete("/vendedores/delete").param("id", "999"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Vendedor no encontrado"));

    verify(vendedorService, times(1)).deleteVendedor(999L);
}

}
