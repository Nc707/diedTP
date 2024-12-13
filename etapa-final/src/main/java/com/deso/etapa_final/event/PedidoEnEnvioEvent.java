package com.deso.etapa_final.event;

import org.springframework.context.ApplicationEvent;

import com.deso.etapa_final.model.Pedido;

public class PedidoEnEnvioEvent extends ApplicationEvent {
    
    private final Pedido pedido;

    public PedidoEnEnvioEvent(Object source, Pedido pedido) { 
        super(source);
        this.pedido = pedido; 
    }
    public Pedido getPedido() { 
        return pedido; 
    } 
}