package com.deso.etapa_final.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import com.deso.etapa_final.model.Coordenada;
import com.deso.etapa_final.model.Vendedor;
import com.deso.etapa_final.repositories.VendedorRepository;

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

    private Vendedor vendedor1;
    private Vendedor vendedor2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicialización de objetos de prueba reutilizables
        vendedor1 = new Vendedor("Juan", "Calle 123", new Coordenada(10.0, 20.0));
        vendedor1.setId(1L);

        vendedor2 = new Vendedor("Pedro", "Calle 456", new Coordenada(30.0, 40.0));
        vendedor2.setId(2L);
    }

    @Test
    void testAddVendedor() {
        // Arrange
        when(vendedorRepository.save(any(Vendedor.class))).thenReturn(vendedor1);

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
        when(vendedorRepository.findById(1L)).thenReturn(Optional.of(vendedor1));

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
        when(vendedorRepository.findAll()).thenReturn(Arrays.asList(vendedor1, vendedor2));

        // Act
        Iterable<Vendedor> result = vendedorService.getVendedores();

        // Assert
        assertNotNull(result);
        assertEquals(2, ((java.util.List<?>) result).size());
        verify(vendedorRepository, times(1)).findAll();
    }

    @Test
    void testUpdateVendedor() {
        // Arrange
        when(vendedorRepository.findById(1L)).thenReturn(Optional.of(vendedor1));
        when(vendedorRepository.save(any(Vendedor.class))).thenReturn(vendedor1);

        // Act
        Vendedor result = vendedorService.updateVendedor(1L, "Juan Actualizado", "Calle Nueva", 15.0, 25.0);

        // Assert
        assertNotNull(result);
        assertEquals("Juan Actualizado", result.getNombre());
        assertEquals("Calle Nueva", result.getDireccion());
        verify(vendedorRepository, times(1)).save(any(Vendedor.class));
    }

    @Test
    void testDeleteVendedor() {
        // Arrange
        doNothing().when(vendedorRepository).deleteById(1L);

        // Act
        vendedorService.deleteVendedor(1L);

        // Assert
        verify(vendedorRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGeneralSearchByName() {
        // Arrange
        when(vendedorRepository.findByNombreContaining("Juan")).thenReturn(Arrays.asList(vendedor1));

        // Act
        Iterable<Vendedor> result = vendedorService.generalSearch("Juan", "nombre", "ASC");

        // Assert
        assertNotNull(result);
        assertEquals(1, ((java.util.List<?>) result).size());
        assertEquals("Juan", ((java.util.List<Vendedor>) result).get(0).getNombre());
        verify(vendedorRepository, times(1)).findByNombreContaining("Juan");
    }
}
