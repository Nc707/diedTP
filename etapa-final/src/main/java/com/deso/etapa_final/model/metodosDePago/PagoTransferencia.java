package com.deso.etapa_final.model.metodosDePago;

import com.deso.etapa_final.model.interfaces.EstrategiasDePagoInterface;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // Constructor sin argumentos para Jackson
@JsonTypeName("PagoTransferencia") // Nombre para identificar esta implementación
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
        return valor * (1.0f + RECARGO);
    }
}
