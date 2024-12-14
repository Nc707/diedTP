package com.deso.etapa_final.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import com.deso.etapa_final.model.Coordenada;
import com.deso.etapa_final.model.Vendedor;
import com.deso.etapa_final.repositories.VendedorRepository;
import com.deso.etapa_final.services.VendedorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class VendedorServiceTest {

    @Mock
    private VendedorRepository vendedorRepository;

    @InjectMocks
    private VendedorService vendedorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddVendedor() {
        // Arrange
        Coordenada coordenada = new Coordenada(10.0, 20.0);
        Vendedor vendedor = new Vendedor("Juan", "Calle 123", coordenada);
        when(vendedorRepository.save(any(Vendedor.class))).thenReturn(vendedor);

        // Act
        Vendedor result = vendedorService.addVendedor("Juan", "Calle 123", 10.0, 20.0);

        // Assert
        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        assertEquals("Calle 123", result.getDireccion());
        verify(vendedorRepository, times(1)).save(any(Vendedor.class));
    }

    @Test
    void testGetVendedorById() {
        // Arrange
        Coordenada coordenada = new Coordenada(10.0, 20.0);
        Vendedor vendedor = new Vendedor("Juan", "Calle 123", coordenada);
        when(vendedorRepository.findById(1L)).thenReturn(Optional.of(vendedor));

        // Act
        Vendedor result = vendedorService.getVendedorById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(vendedorRepository, times(1)).findById(1L);
    }

    @Test
    void testGetVendedores() {
        // Arrange
        Coordenada coordenada1 = new Coordenada(10.0, 20.0);
        Coordenada coordenada2 = new Coordenada(30.0, 40.0);
        Vendedor vendedor1 = new Vendedor("Juan", "Calle 123", coordenada1);
        Vendedor vendedor2 = new Vendedor("Pedro", "Calle 456", coordenada2);
        when(vendedorRepository.findAll()).thenReturn(Arrays.asList(vendedor1, vendedor2));

        // Act
        Iterable<Vendedor> result = vendedorService.getVendedores();

        // Assert
        assertNotNull(result);
        assertEquals(2, ((java.util.List<?>) result).size());
        verify(vendedorRepository, times(1)).findAll();
    }

    @Test
    void testDeleteVendedor() {
        // Arrange
        long id = 1L;
        doNothing().when(vendedorRepository).deleteById(id);

        // Act
        vendedorService.deleteVendedor(id);

        // Assert
        verify(vendedorRepository, times(1)).deleteById(id);
    }
}