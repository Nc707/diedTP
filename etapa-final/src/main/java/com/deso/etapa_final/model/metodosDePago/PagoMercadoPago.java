package com.deso.etapa_final.model.metodosDePago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@DiscriminatorValue("MERCADOPAGO")
public class PagoMercadoPago extends EstrategiasDePago {

    private static final float RECARGO = 0.02f;

    @Column(nullable = false)
    private String alias;

    @Override
    public float cerrarPago(float valor) {
        return valor * (1.0f + RECARGO);
    }
}
