package com.deso.etapa_final.controllers;

import com.deso.etapa_final.exception.AlreadyExistentCarritoException;
import com.deso.etapa_final.exception.NonExistentCarritoException;
import com.deso.etapa_final.exception.NonExistentException;
import com.deso.etapa_final.exception.NonSettedMetodoPagoException;
import com.deso.etapa_final.model.Pedido;
import com.deso.etapa_final.services.CarritoService;
import com.deso.etapa_final.services.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private PedidoService pedidoService;

    // @GetMapping("/getAll")
    // public ResponseEntity<Iterable<Pedido>> getPedidos() {
    //     Iterable<Pedido> pedidos = pedidoService.getAllPedidos();
    //     return new ResponseEntity<>(pedidos, HttpStatus.OK);
    // }
    @GetMapping("getAll")
    public String getPedidos(Model model) {
        Iterable<Pedido> pedidos =  pedidoService.getAllPedidos();
        model.addAttribute("pedidos", pedidos );
        return "pedidos-listado";

    }
    @GetMapping("/search")
    public String searchPedidos(@RequestParam String searchable, @RequestParam String orderBy, @RequestParam String orderDirection, Model model) {
        Iterable<Pedido> pedidos = pedidoService.generalSearch(searchable, orderBy, orderDirection);
        model.addAttribute("pedidos", pedidos);
        return "pedidos-listado";

    }
  
    @GetMapping("/carrito/{clienteId}/{vendedorId}")
    public ResponseEntity<Long> obtenerCarrito(@PathVariable Long clienteId) throws NonExistentCarritoException {
        Long carritoId = carritoService.obtenerCarrito(clienteId);
        return new ResponseEntity<>(carritoId, HttpStatus.OK);
        
       
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.obtenerPedidoPorId(pedidoId);
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @PostMapping("/carrito/crearCarrito/{clienteId}/{vendedorId}")
    public ResponseEntity<Long> crearCarrito(@PathVariable Long clienteId, @PathVariable Long vendedorId) throws AlreadyExistentCarritoException {
        Long carritoId = carritoService.crearCarrito(clienteId, vendedorId);
        return new ResponseEntity<>(carritoId, HttpStatus.OK);
    }

    @PostMapping("/carrito/{clienteId}/agregarItem/{itemMenuId}/{cantidad}")
    public ResponseEntity<Void> agregarItem(@PathVariable Long clienteId, @PathVariable Long itemMenuId, @PathVariable int cantidad) throws NonExistentCarritoException, NonExistentException {
        carritoService.agregarItem(clienteId, itemMenuId, cantidad);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/carrito/{clienteId}/eliminarItem/{itemPedidoId}")
    public ResponseEntity<Void> eliminarItem(@PathVariable Long clienteId, @PathVariable Long itemPedidoId) throws NonExistentCarritoException, NonExistentException {
        carritoService.eliminarItem(clienteId, itemPedidoId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/carrito/{clienteId}/modificarCantidad/{itemPedidoId}/{cantidad}")
    public ResponseEntity<Void> modificarCantidad(@PathVariable Long clienteId, @PathVariable Long itemPedidoId, @PathVariable int cantidad) throws NonExistentCarritoException, NonExistentException {
        carritoService.modificarCantidad(clienteId, itemPedidoId, cantidad);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/carrito/{clienteId}/cancelar")
    public ResponseEntity<Void> cancelarPedido(@PathVariable Long clienteId) throws NonExistentCarritoException {
        carritoService.cancelarPedido(clienteId);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/carrito/{clienteId}/setMercadoPago")
    public ResponseEntity<Void> setMercadoPago(@PathVariable Long clienteId, @RequestParam String alias) throws NonExistentCarritoException {
        carritoService.setMercadoPago(clienteId, alias);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/carrito/{clienteId}/setTransferencia")
    public ResponseEntity<Void> setTransferencia(@PathVariable Long clienteId, @RequestParam String cbu, @RequestParam long cuit) throws NonExistentCarritoException {
        carritoService.setTransferencia(clienteId, cbu, cuit);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/carrito/{clienteId}/cerrar")
    public ResponseEntity<Void> cerrarPedido(@PathVariable Long clienteId) throws NonSettedMetodoPagoException, NonExistentCarritoException {
        carritoService.cerrarPedido(clienteId);
        return ResponseEntity.ok().build();
    }


}
