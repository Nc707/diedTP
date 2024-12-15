package com.deso.etapa_final.services;

import com.deso.etapa_final.model.Cliente;
import com.deso.etapa_final.model.Coordenada;
import com.deso.etapa_final.model.Vendedor;
import com.deso.etapa_final.repositories.VendedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VendedorServiceTest {

    @Mock
    private VendedorRepository vendedorRepository;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private VendedorService vendedorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGeneralSearchByNombre() {
        String searchable = "Juan";
        List<Vendedor> vendedores = List.of(new Vendedor("Juan", "Direccion 1", new Coordenada(10.0, 20.0)));
        when(vendedorRepository.findByNombreContaining(searchable)).thenReturn(vendedores);
        when(vendedorRepository.findByDireccionContaining(searchable)).thenReturn(Collections.emptyList());
        Iterable<Vendedor> result = vendedorService.generalSearch(searchable, "id", "ASC");
        assertIterableEquals(vendedores, result);
        verify(vendedorRepository).findByNombreContaining(searchable);
        verify(vendedorRepository).findByDireccionContaining(searchable);
    }
    @Test
    void testAddVendedor() {
        String nombre = "Juan";
        String direccion = "Direccion 1";
        double latitud = 10.0;
        double longitud = 20.0;
        Vendedor vendedor = new Vendedor(nombre, direccion, new Coordenada(latitud, longitud));
        when(vendedorRepository.save(any(Vendedor.class))).thenReturn(vendedor);
        Vendedor result = vendedorService.addVendedor(nombre, direccion, latitud, longitud);

        assertEquals(vendedor, result);
        verify(vendedorRepository).save(any(Vendedor.class));
    }

    @Test
    void testGetVendedorByIdFound() {
        long id = 1L;
        Vendedor vendedor = new Vendedor("Juan", "Direccion", new Coordenada(10.0, 20.0));
        when(vendedorRepository.findById(id)).thenReturn(Optional.of(vendedor));
        Vendedor result = vendedorService.getVendedorById(id);
        assertEquals(vendedor, result);
        verify(vendedorRepository).findById(id);
    }

    @Test
    void testGetVendedorByIdNotFound() {
        long id = 1L;
        when(vendedorRepository.findById(id)).thenReturn(Optional.empty());
        Vendedor result = vendedorService.getVendedorById(id);
        assertNull(result);
        verify(vendedorRepository).findById(id);
    }

    @Test
    void testUpdateVendedor() {
        long id = 1L;
        String nombre = "Juan";
        String direccion = "Direccion Actualizada";
        double latitud = 15.0;
        double longitud = 25.0;
        Vendedor vendedor = new Vendedor("Juan", "Direccion", new Coordenada(10.0, 20.0));
        when(vendedorRepository.findById(id)).thenReturn(Optional.of(vendedor));
        when(vendedorRepository.save(any(Vendedor.class))).thenReturn(vendedor);
        Vendedor updated = vendedorService.updateVendedor(id, nombre, direccion, latitud, longitud);
        
        assertEquals(direccion, updated.getDireccion());
        assertEquals(latitud, updated.getCoordenadas().getLatitud());
        assertEquals(longitud, updated.getCoordenadas().getLongitud());
        verify(vendedorRepository).findById(id);
        verify(vendedorRepository).save(any(Vendedor.class));
    }

    @Test
    void testDeleteVendedor() {
        long id = 1L;
        when(vendedorRepository.existsById(id)).thenReturn(true);
        doNothing().when(vendedorRepository).deleteById(id);
        vendedorService.deleteVendedor(id);
        verify(vendedorRepository).existsById(id);
        verify(vendedorRepository).deleteById(id);
    }

}
