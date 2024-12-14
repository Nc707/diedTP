package com.deso.etapa_final.model.metodosDePago;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED) 
@DiscriminatorColumn(name = "tipo_pago", discriminatorType = DiscriminatorType.STRING)
@Entity
public abstract class EstrategiasDePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long metodopagoid;

    public abstract float cerrarPago(float valor);

    public abstract String getNombre();
}
