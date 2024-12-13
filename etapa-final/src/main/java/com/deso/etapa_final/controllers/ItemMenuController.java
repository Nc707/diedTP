package com.deso.etapa_final.controllers;

import com.deso.etapa_final.model.Bebida;
import com.deso.etapa_final.model.ItemMenu;
import com.deso.etapa_final.model.Plato;
import com.deso.etapa_final.model.Vendedor;
import com.deso.etapa_final.services.BebidaService;
import com.deso.etapa_final.services.ItemMenuService;
import com.deso.etapa_final.services.PlatoService;
import com.deso.etapa_final.services.VendedorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;



@Controller
@RequestMapping("/ItemMenu")
public class ItemMenuController {

    @Autowired
    private BebidaService bebidaService;

    @Autowired
    private PlatoService platoService;

    @Autowired
    private VendedorService vendedorService;
    
    @Autowired
    private ItemMenuService itemMenuService;
    


    @GetMapping("/getItemMenuByVendedor")
    public String mostrarMenuVendedor(@RequestParam("id") Long id, Model model) {
        List<ItemMenu> itemsMenu = itemMenuService.obtenerItemsMenuPorVendedor(vendedorService.getVendedorById(id));
        model.addAttribute("itemsMenu", itemsMenu);
        return "items-menu";
    }
    // @GetMapping("/getItemMenuByVendedor/{id}")
    // public List<ItemMenu> mostrarMenuVendedor(@PathVariable Long  id) {
    //     return itemMenuService.obtenerItemsMenuPorVendedor(vendedorService.getVendedorById(id)); 
    // }


    // Endpoint para crear una nueva bebida
    @PostMapping("/createBebida")
    public ResponseEntity<Bebida> createBebida(
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam float precio,
            @RequestParam Long id_vendedor, 
            @RequestParam float graduacionAlcoholica,
            @RequestParam float tamanio,
            @RequestParam float peso,
            @RequestParam(required = false) List<String> categorias) {
        
        Bebida bebida = bebidaService.createBebida(nombre, descripcion, precio, id_vendedor, graduacionAlcoholica, tamanio, peso, categorias);
        return ResponseEntity.ok(bebida);
    }

    // Endpoint para crear un nuevo plato
    @PostMapping("/createPlato")
    public ResponseEntity<Plato> createPlato(
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam float precio,
            @RequestParam Long id_vendedor,
            @RequestParam float peso,
            @RequestParam float tamanio,
            @RequestParam(required = false) List<String> categorias) {
        
        Plato plato = platoService.createPlato(nombre, descripcion, precio, id_vendedor, peso, tamanio, categorias);
        return ResponseEntity.ok(plato);
    }

    // Endpoint para actualizar una bebida existente
    @PostMapping("/updateBebida")
    public ResponseEntity<Bebida> updateBebida(
            @RequestParam long id,
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam float precio,
            @RequestParam float graduacionAlcoholica,
            @RequestParam float tamaño,
            @RequestParam float peso,
            @RequestParam(required = false) List<String> categorias) {
        
        Bebida bebida = bebidaService.updateBebida(id, nombre, descripcion, precio, graduacionAlcoholica, tamaño, peso, categorias);
        return ResponseEntity.ok(bebida);
    }

    // Endpoint para actualizar un plato existente
    @PostMapping("/updatePlato")
    public ResponseEntity<Plato> updatePlato(
            @RequestParam long id,
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam float precio,
            @RequestParam float peso,
            @RequestParam float tamaño,
            @RequestParam(required = false) List<String> categorias) {
        
        Plato plato = platoService.updatePlato(id, nombre, descripcion, precio, peso, tamaño, categorias);
        return ResponseEntity.ok(plato);
    }

    // Endpoint para eliminar una bebida
    @DeleteMapping("/deleteBebida")
    public void deleteBebida(@RequestParam long id) {
        bebidaService.deleteBebida(id);
    }

    // Endpoint para eliminar un plato
    @DeleteMapping("/deletePlato")
    public void deletePlato(@RequestParam long id) {
        platoService.deletePlato(id);
    }

    // Endpoint para obtener todas las bebidas
    @GetMapping("/getAllBebidas")
    public ResponseEntity<List<Bebida>> getAllBebidas() {
        List<Bebida> bebidas = bebidaService.getAllBebidas();
        return ResponseEntity.ok(bebidas);
    }

    // Endpoint para obtener todos los platos
    @GetMapping("/getAllPlatos")
    public ResponseEntity<List<Plato>> getAllPlatos() {
        List<Plato> platos = platoService.getAllPlatos();
        return ResponseEntity.ok(platos);
    }
}