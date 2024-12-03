package com.deso.etapa_final.controllers;

import com.deso.etapa_final.model.Bebida;
import com.deso.etapa_final.repositories.BebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bebidas")
public class BebidaController {

    @Autowired
    private BebidaRepository bebidaRepository;

    // Endpoint para crear una nueva bebida
    @PostMapping("/create")
    public ResponseEntity<Bebida> createBebida(
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam float precio,
            @RequestParam float graduacionAlcoholica,
            @RequestParam float tamaño,
            @RequestParam float peso) {
        
        Bebida bebida = new Bebida();
        bebida.setNombre(nombre);
        bebida.setDescripcion(descripcion);
        bebida.setPrecio(precio);
        bebida.setGraduacionAlcoholica(graduacionAlcoholica);
        bebida.setTamaño(tamaño);
        bebida.setPeso(peso);
        
        Bebida savedBebida = bebidaRepository.save(bebida);
        return ResponseEntity.ok(savedBebida);
    }

    // Endpoint para obtener todas las bebidas
    @GetMapping("/getAll")
    public ResponseEntity<List<Bebida>> getAllBebidas() {
        List<Bebida> bebidas = (List<Bebida>) bebidaRepository.findAll();
        return ResponseEntity.ok(bebidas);
    }
}