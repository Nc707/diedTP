package com.deso.etapa_final.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bebida")
public class Bebida extends ItemMenu {

    @Column
    private float graduacionAlcoholica;

    @Column
    private float tamaño;

    @Column
    private float peso;

    // Getters y setters
    public float getGraduacionAlcoholica() {
        return graduacionAlcoholica;
    }

    public void setGraduacionAlcoholica(float graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
    }

    public float getTamaño() {
        return tamaño;
    }

    public void setTamaño(float tamaño) {
        this.tamaño = tamaño;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    @Override
    public boolean esComida() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'esComida'");
    }

    @Override
    public boolean esBebida() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'esBebida'");
    }

    @Override
    public boolean aptoVegano() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'aptoVegano'");
    }
}