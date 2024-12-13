package com.deso.etapa_final.model.interfaces;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.deso.etapa_final.model.metodosDePago.PagoMercadoPago;
import com.deso.etapa_final.model.metodosDePago.PagoTransferencia;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type") // Campo 'type' para identificar la clase
@JsonSubTypes({
    @JsonSubTypes.Type(value = PagoMercadoPago.class, name = "PagoMercadoPago"),
    @JsonSubTypes.Type(value = PagoTransferencia.class, name = "PagoTransferencia")
})
public interface EstrategiasDePagoInterface {
    float cerrarPago(float valor);
}
