package com.deso.etapa_final.model.metodosDePago;

import com.deso.etapa_final.model.interfaces.EstrategiasDePagoInterface;

import lombok.Getter;

@Getter
public class PagoMercadoPago implements EstrategiasDePagoInterface {
    final float RECARGO = 0.02f;

    private String alias;

    public PagoMercadoPago(String alias) {
        this.alias = alias;
    }
    @Override
    public float cerrarPago(float valor) {
        return valor*(1.0f+RECARGO);
    }
}