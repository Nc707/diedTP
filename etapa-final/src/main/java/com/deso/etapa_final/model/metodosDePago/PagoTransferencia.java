package com.deso.etapa_final.model.metodosDePago;

import com.deso.etapa_final.model.interfaces.EstrategiasDePagoInterface;

import lombok.Getter;

@Getter
public class PagoTransferencia implements EstrategiasDePagoInterface {
    final float RECARGO = 0.04f;

    private String cbu;

    private Long cuit;

    public PagoTransferencia(String cbu, Long cuit) {
        this.cbu = cbu;
        this.cuit = cuit;
    }

    @Override
    public float cerrarPago(float valor) {
        return valor*(1.0f+RECARGO);
    }
}