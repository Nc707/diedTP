package com.deso.etapa_final.controllers;

import com.deso.etapa_final.model.Cliente;
import com.deso.etapa_final.model.Coordenada;
import com.deso.etapa_final.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    
    @PostMapping("/add")
    public String addCliente(@RequestParam String nombre,
                            @RequestParam long cuit, 
                            @RequestParam String email, 
                            @RequestParam String direccion, 
                            @RequestParam double latitud,
                            @RequestParam double longitud 
                            ) {

        Coordenada coordenada = new Coordenada();
        coordenada.setLatitud(latitud);
        coordenada.setLongitud(longitud);
        Cliente cliente = clienteService.addCliente(cuit, email, direccion, coordenada, nombre);
        return "redirect:/clientes/getAll";
    }

    @GetMapping("/agregar-cliente")
    public String mostrarFormularioAgregar() {
        return "agregar-cliente";
    }

    @GetMapping("/getAll")
    public String getClientes(Model model) {
        Iterable<Cliente> clientes =  clienteService.getClientes();
        model.addAttribute("clientes", clientes );
        return "clientes-listado";

    }
    @GetMapping("/search")
    public String searchClientes(@RequestParam("search") String searchable, @RequestParam String orderBy, @RequestParam String orderDirection, Model model) {
        Iterable<Cliente> clientes = clienteService.generalSearch(searchable, orderBy, orderDirection);
        model.addAttribute("clientes", clientes);
        return "clientes-listado";
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
