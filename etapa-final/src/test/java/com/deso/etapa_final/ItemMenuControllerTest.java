package com.deso.etapa_final;

import com.deso.etapa_final.controllers.ItemMenuController;
import com.deso.etapa_final.model.Bebida;
import com.deso.etapa_final.model.Plato;
import com.deso.etapa_final.services.BebidaService;
import com.deso.etapa_final.services.PlatoService;
import com.deso.etapa_final.services.VendedorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ItemMenuControllerTest {

    @Mock
    private BebidaService bebidaService;

    @Mock
    private PlatoService platoService;

    @Mock
    private VendedorService vendedorService;

    @Mock
    private Model model;

    @InjectMocks
    private ItemMenuController itemMenuController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMostrarMenuVendedor() {
        Long vendedorId = 1L;
        List<Bebida> bebidas = new ArrayList<>();
        List<Plato> platos = new ArrayList<>();

        when(bebidaService.getBebidasByVendedorId(vendedorId)).thenReturn(bebidas);
        when(platoService.getPlatosByVendedorId(vendedorId)).thenReturn(platos);
        when(vendedorService.getVendedorById(vendedorId)).thenReturn(null);

        String viewName = itemMenuController.mostrarMenuVendedor(vendedorId, model);

        verify(model).addAttribute("bebidas", bebidas);
        verify(model).addAttribute("platos", platos);
        verify(model).addAttribute("vendedor", null);

        assertEquals("items-menu-vendedor", viewName);
    }

    @Test
    void testGetAllItemsMenu() {
        List<Bebida> bebidas = new ArrayList<>();
        List<Plato> platos = new ArrayList<>();

        when(bebidaService.getAllBebidas()).thenReturn(bebidas);
        when(platoService.getAllPlatos()).thenReturn(platos);

        String viewName = itemMenuController.getAllItemsMenu(model);

        verify(model).addAttribute("bebidas", bebidas);
        verify(model).addAttribute("platos", platos);

        assertEquals("items-menu", viewName);
    }

    @Test
    void testSearchItemsMenu() {
        String searchPlato = "pollo";
        String searchBebida = "cola";
        String orderByPlato = "nombre";
        String orderByBebida = "precio";
        String orderDirectionPlato = "ASC";
        String orderDirectionBebida = "DESC";

        List<Bebida> bebidas = new ArrayList<>();
        List<Plato> platos = new ArrayList<>();

        when(bebidaService.generalSearch(searchBebida, orderByBebida, orderDirectionBebida)).thenReturn(bebidas);
        when(platoService.generalSearch(searchPlato, orderByPlato, orderDirectionPlato)).thenReturn(platos);

        String viewName = itemMenuController.searchItemsMenu(
                searchPlato, searchBebida, orderByPlato, orderByBebida,
                orderDirectionPlato, orderDirectionBebida, null, model);

        verify(model).addAttribute("bebidas", bebidas);
        verify(model).addAttribute("platos", platos);

        assertEquals("items-menu", viewName);
    }

    @Test
    void testCreateBebida() {
        String nombre = "Coca-Cola";
        String descripcion = "Bebida gaseosa";
        float precio = 2.5f;
        Long vendedorId = 1L;
        float graduacionAlcoholica = 0f;
        float tamanio = 500f;
        float peso = 0.5f;
        List<String> categorias = new ArrayList<>();

        Bebida bebida = new Bebida();
        when(bebidaService.createBebida(
                nombre, descripcion, precio, vendedorId,
                graduacionAlcoholica, tamanio, peso, categorias)).thenReturn(bebida);

        String redirectUrl = itemMenuController.createBebida(
                nombre, descripcion, precio, vendedorId,
                graduacionAlcoholica, tamanio, peso, categorias, null);

        assertEquals("redirect:/ItemMenu/getItemMenuByVendedor?id=" + vendedorId, redirectUrl);
    }

    @Test
    void testDeletePlato() {
        Long platoId = 1L;
        doNothing().when(platoService).deletePlato(platoId);

        itemMenuController.deletePlato(platoId);

        verify(platoService).deletePlato(platoId);
    }
}
