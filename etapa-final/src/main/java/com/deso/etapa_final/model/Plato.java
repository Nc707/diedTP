package com.deso.etapa_final.model;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Plato extends ItemMenu {

    @Column
    private float calorias;

    @Column
    private boolean aptoCeliaco;

    @Column
    private boolean aptoVegano;

    @Column
    @Setter(AccessLevel.NONE)
    private float peso;

    public void setPeso(float peso) {
        this.peso = peso * 0.10f;
    }

    @Override
    public boolean esComida() {
        return true;
    }

    @Override
    public boolean esBebida() {
        return false;
    }

    @Override
    public boolean aptoVegano() {
        return this.aptoVegano;
    }


}