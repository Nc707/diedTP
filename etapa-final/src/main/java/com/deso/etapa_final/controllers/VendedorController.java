package com.deso.etapa_final.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deso.etapa_final.model.Coordenada;
import com.deso.etapa_final.model.Vendedor;
import com.deso.etapa_final.repositories.VendedorRepository;

@RestController
public class VendedorController {
    @Autowired
    private VendedorRepository vendedorRepository;
    
    @GetMapping("/hola")
    public String holaMundo() {
        return "Hola Mundo";
    }

    @PostMapping("/addVendedor")
    public Vendedor addVendedor(
            @RequestParam String nombre,
            @RequestParam String direccion,
            @RequestParam double latitud,
            @RequestParam double longitud) {

        Coordenada coordenadas = new Coordenada(latitud, longitud);
        Vendedor vendedor = new Vendedor(nombre, direccion, coordenadas);
        return vendedorRepository.save(vendedor);
    }

    @GetMapping("/getVendedores")
    public Set<Vendedor> getVendedores() {
        return vendedorRepository.findAll();
    }
}
