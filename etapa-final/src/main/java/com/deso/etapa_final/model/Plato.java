package com.deso.etapa_final.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
    private float peso;

    public float getCalorias() {
        return calorias;
    }

    public void setCalorias(float calorias) {
        this.calorias = calorias;
    }

    public boolean isAptoCeliaco() {
        return aptoCeliaco;
    }

    public void setAptoCeliaco(boolean aptoCeliaco) {
        this.aptoCeliaco = aptoCeliaco;
    }

    public boolean isAptoVegano() {
        return aptoVegano;
    }

    public void setAptoVegano(boolean aptoVegano) {
        this.aptoVegano = aptoVegano;
    }

    public float getPeso() {
        return peso;
    }

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