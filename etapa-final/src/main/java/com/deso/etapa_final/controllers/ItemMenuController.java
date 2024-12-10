package com.deso.etapa_final.controllers;

import com.deso.etapa_final.model.Bebida;
import com.deso.etapa_final.model.Plato;
import com.deso.etapa_final.services.BebidaService;
import com.deso.etapa_final.services.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ItemMenu")
public class ItemMenuController {

    @Autowired
    private BebidaService bebidaService;

    @Autowired
    private PlatoService platoService;

    // Endpoint para crear una nueva bebida
    @PostMapping("/createBebida")
    public ResponseEntity<Bebida> createBebida(
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam float precio,
            @RequestParam float graduacionAlcoholica,
            @RequestParam float tamaño,
            @RequestParam float peso,
            @RequestParam(required = false) List<String> categorias) {
        
        Bebida bebida = bebidaService.createBebida(nombre, descripcion, precio, graduacionAlcoholica, tamaño, peso, categorias);
        return ResponseEntity.ok(bebida);
    }

    // Endpoint para crear un nuevo plato
    @PostMapping("/createPlato")
    public ResponseEntity<Plato> createPlato(
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam float precio,
            @RequestParam float peso,
            @RequestParam float tamaño,
            @RequestParam(required = false) List<String> categorias) {
        
        Plato plato = platoService.createPlato(nombre, descripcion, precio, peso, tamaño, categorias);
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