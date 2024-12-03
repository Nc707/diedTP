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
public class Coordenada {
    public static final double RADIO_TIERRA = 6371.0;

    @Column
    private double latitud;

    @Column
    private double longitud;

    public double getLatitud() {
        return latitud;
    }
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    public double getLongitud() {
        return longitud;
    }
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    @Override
    public String toString() {
        return "Coordenada [latitud=" + latitud + ", longitud=" + longitud + "]";
    }
}
