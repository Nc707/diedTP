package com.deso.etapa_final.controllers;
import com.deso.etapa_final.model.Bebida;
import com.deso.etapa_final.model.Plato;
import com.deso.etapa_final.services.BebidaService;
import com.deso.etapa_final.services.PlatoService;
import com.deso.etapa_final.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
    

    @GetMapping("/getItemMenuByVendedor")
    public String mostrarMenuVendedor(@RequestParam Long id, Model model) {

        Iterable<Bebida> bebidas = bebidaService.getBebidasByVendedorId(id);
        Iterable<Plato> platos = platoService.getPlatosByVendedorId(id);
        model.addAttribute("bebidas", bebidas);
        model.addAttribute("platos", platos);
        model.addAttribute("vendedor", vendedorService.getVendedorById(id));
        return "items-menu-vendedor";
    }

    @GetMapping("/getAll")
    public String getAllItemsMenu(Model model) {
        Iterable<Bebida> bebidas = bebidaService.getAllBebidas();
        Iterable<Plato> platos = platoService.getAllPlatos();
        model.addAttribute("bebidas", bebidas);
        model.addAttribute("platos", platos);
        return "items-menu";
    }

    @GetMapping("/search")
    public String searchItemsMenu(
            @RequestParam(value = "searchPlato", defaultValue = "") String searchPlato,
            @RequestParam(value = "searchBebida", defaultValue = "") String searchBebida,
            @RequestParam(value = "orderByPlato", defaultValue = "id") String orderByPlato,
            @RequestParam(value = "orderByBebida", defaultValue = "id") String orderByBebida,
            @RequestParam(value = "orderDirectionPlato", defaultValue = "ASC") String orderDirectionPlato,
            @RequestParam(value = "orderDirectionBebida", defaultValue = "ASC") String orderDirectionBebida,
            @RequestParam(value = "vendedorId", required = false) Long vendedorId,
            Model model) {
        Iterable<Bebida> bebidas = bebidaService.generalSearch(searchBebida, orderByBebida, orderDirectionBebida);
        Iterable<Plato> platos = platoService.generalSearch(searchPlato, orderByPlato, orderDirectionPlato);
        model.addAttribute("bebidas", bebidas);
        model.addAttribute("platos", platos);
        return "items-menu";
    }
    @GetMapping("/searchByVendedor")
    public String searchItemsMenubyVendedor(
            @RequestParam(value = "searchPlato", defaultValue = "") String searchPlato,
            @RequestParam(value = "searchBebida", defaultValue = "") String searchBebida,
            @RequestParam(value = "orderByPlato", defaultValue = "id") String orderByPlato,
            @RequestParam(value = "orderByBebida", defaultValue = "id") String orderByBebida,
            @RequestParam(value = "orderDirectionPlato", defaultValue = "ASC") String orderDirectionPlato,
            @RequestParam(value = "orderDirectionBebida", defaultValue = "ASC") String orderDirectionBebida,
            @RequestParam(value = "vendedorId") Long vendedorId,
            Model model) {
        Iterable<Bebida> bebidas = bebidaService.generalSearchByVendedor(searchBebida, orderByBebida, orderDirectionBebida, vendedorId);
        Iterable<Plato> platos = platoService.generalSearchByVendedor(searchPlato, orderByPlato, orderDirectionPlato, vendedorId);
        model.addAttribute("bebidas", bebidas);
        model.addAttribute("platos", platos);
        model.addAttribute("vendedor", vendedorService.getVendedorById(vendedorId));
        return "items-menu-vendedor";
    }
    @GetMapping("/crearBebidaRedirect")
    public String crearBebidaRedirect(@RequestParam Long vendedorid, Model model) {
        model.addAttribute("vendedorid", vendedorid);
        return "crear-bebida";
    }

    @PostMapping("/createBebida")
    public String createBebida(
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam float precio,
            @RequestParam Long id_vendedor,
            @RequestParam float graduacionAlcoholica,
            @RequestParam float tamanio,
            @RequestParam float peso,
            @RequestParam(required = false) List<String> categorias,
            RedirectAttributes redirectAttributes) {
        
        bebidaService.createBebida(nombre, descripcion, precio, id_vendedor, graduacionAlcoholica, tamanio, peso, categorias);
        return "redirect:/ItemMenu/getItemMenuByVendedor?id=" + id_vendedor;
    }


    @GetMapping("/crearPlatoRedirect")
    public String crearPlatoRedirect(@RequestParam Long vendedorid, Model model) {
        model.addAttribute("vendedorid", vendedorid);
        return "crear-plato";
    }
    @PostMapping("/createPlato")
    public String createPlato(
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam float precio,
            @RequestParam Long id_vendedor,
            @RequestParam(required = false) List<String> categorias,
            @RequestParam float calorias,
            @RequestParam(defaultValue = "false") Boolean aptoCeliaco,
            @RequestParam(defaultValue = "false") Boolean aptoVegano,
            @RequestParam float peso,
            RedirectAttributes redirectAttributes) {
        
        platoService.createPlato(nombre, descripcion, precio, id_vendedor, categorias, calorias, aptoCeliaco, aptoVegano,peso);
        return "redirect:/ItemMenu/getItemMenuByVendedor?id=" + id_vendedor;
    }


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

    @DeleteMapping("/deleteBebida")
    public void deleteBebida(@RequestParam long id) {
        bebidaService.deleteBebida(id);
    }

    @DeleteMapping("/deletePlato")
    public void deletePlato(@RequestParam long id) {
        platoService.deletePlato(id);
    }

    @GetMapping("/getAllBebidas")
    public ResponseEntity<List<Bebida>> getAllBebidas() {
        List<Bebida> bebidas = bebidaService.getAllBebidas();
        return ResponseEntity.ok(bebidas);
    }

    @GetMapping("/getAllPlatos")
    public ResponseEntity<List<Plato>> getAllPlatos() {
        List<Plato> platos = platoService.getAllPlatos();
        return ResponseEntity.ok(platos);
    }
}