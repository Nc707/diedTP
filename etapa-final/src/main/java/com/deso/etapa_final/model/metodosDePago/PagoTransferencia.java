package com.deso.etapa_final.model.metodosDePago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


import com.deso.etapa_final.model.interfaces.EstrategiasDePagoInterface;
import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.*;
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("TRANSFERENCIA")
public class PagoTransferencia extends EstrategiasDePago {

    private static final float RECARGO = 0.04f;

    @Column(nullable = false)
    private String cbu;

    @Column(nullable = false)
    private Long cuit;

    @Override
    public float cerrarPago(float valor) {
        return valor * (1.0f + RECARGO);
    }

    @Override
    public String getNombre() {
        return "Transferencia";
    }

}
