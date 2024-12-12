package com.deso.etapa_final.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.deso.etapa_final.model.Pedido;
import com.deso.etapa_final.services.ClienteService;

@Component
public class ClienteObserver implements ApplicationListener<PedidoEnEnvioEvent> {

    @Autowired
    private ClienteService clienteService;

    @Override
    public void onApplicationEvent(PedidoEnEnvioEvent event) {
        Pedido pedido = event.getPedido();
        if (Pedido.EstadoPedido.EN_ENVIO.equals(pedido.getEstado())) {
            clienteService.generarPago(pedido);
        }
    }
}
