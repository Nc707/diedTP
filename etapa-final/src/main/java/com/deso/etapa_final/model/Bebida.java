package com.deso.etapa_final.model;

import jakarta.persistence.*;

import lombok.*;

@Getter
@EqualsAndHashCode(callSuper = false)
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bebida extends ItemMenu {

    @Column
    private float graduacionAlcoholica;

    @Column
    private float tamaño;

    @Column
    private float peso;

    @Override
    public boolean esComida() {
        return false;
    }

    @Override
    public boolean esBebida() {
        return true;
    }

    @Override
    public boolean aptoVegano() {
       return true;
    }
}