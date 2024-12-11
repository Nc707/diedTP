package com.deso.etapa_final.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deso.etapa_final.model.Pedido;
import com.deso.etapa_final.model.interfaces.EstrategiasDePagoInterface;
import com.deso.etapa_final.repositories.PedidoRepository;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido obtenerPedidoPorId(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    public EstrategiasDePagoInterface obtenerMetodoDePagoDePedido(Long pedidoId) {
        Pedido pedido = obtenerPedidoPorId(pedidoId);
        return pedido.getMetodoDePago(); // La deserialización ocurre automáticamente
    }
}

