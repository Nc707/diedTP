package com.deso.etapa_final.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.deso.etapa_final.services.PedidoService;
import com.deso.etapa_final.model.interfaces.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{pedidoId}/metodoDePago")
    public String obtenerMetodoDePago(@PathVariable Long pedidoId) {
        EstrategiasDePagoInterface metodoDePago = pedidoService.obtenerMetodoDePagoDePedido(pedidoId);
        return metodoDePago.getClass().getSimpleName(); // Ejemplo de respuesta
    }
}
