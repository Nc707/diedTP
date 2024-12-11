package com.deso.etapa_final.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deso.etapa_final.model.Vendedor;
import com.deso.etapa_final.services.VendedorService;

@Controller
@RequestMapping("/vendedores")
public class VendedorController {
    
    @Autowired
    private VendedorService vendedorService;

    @PostMapping("/add")
    public Vendedor addVendedor(
            @RequestParam String nombre,
            @RequestParam String direccion,
            @RequestParam double latitud,
            @RequestParam double longitud) {
        return vendedorService.addVendedor(nombre, direccion, latitud, longitud);
    }

    // @GetMapping("/getAll")
    // public Iterable<Vendedor> getVendedores() {
    //     return vendedorService.getVendedores();
    // }
    @GetMapping("/getAll")
    public String getVendedores(Model model) {
        Iterable<Vendedor> vendedores =  vendedorService.getVendedores();
        model.addAttribute("vendedores", vendedores );
        return "vendedores-listado";

    }

    @GetMapping("/getById")
    public Vendedor getVendedorById(@RequestParam long id) {
        return vendedorService.getVendedorById(id);
    }

    @PostMapping("/update")
    public Vendedor updateVendedor(
            @RequestParam long id,
            @RequestParam String nombre,
            @RequestParam String direccion,
            @RequestParam double latitud,
            @RequestParam double longitud) {
        return vendedorService.updateVendedor(id, nombre, direccion, latitud, longitud);
    }

    @DeleteMapping("/delete")
    public void deleteVendedor(@RequestParam long id) {
        vendedorService.deleteVendedor(id);
    }
}
