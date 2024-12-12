package com.deso.etapa_final.controllers;

import com.deso.etapa_final.model.Cliente;
import com.deso.etapa_final.model.Coordenada;
import com.deso.etapa_final.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Cliente> addCliente(@RequestParam long cuit, @RequestParam String email, @RequestParam String direccion, @RequestParam long latitud,@RequestParam long longitud, @RequestParam String nombre) {
        Coordenada coordenada = new Coordenada();
        coordenada.setLatitud(latitud);
        coordenada.setLongitud(longitud);
        Cliente cliente = clienteService.addCliente(cuit, email, direccion, coordenada, nombre);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Iterable<Cliente>> getClientes() {
        Iterable<Cliente> clientes = clienteService.getClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestParam long cuit, @RequestParam String email, @RequestParam String direccion, @RequestParam Coordenada coordenadas, @RequestParam String nombre) {
        Cliente cliente = clienteService.updateCliente(id, cuit, email, direccion, coordenadas, nombre);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.ok().build();
    }
}
