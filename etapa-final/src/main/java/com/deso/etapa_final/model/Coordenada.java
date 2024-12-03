package com.deso.etapa_final.model;

import jakarta.persistence.*;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Coordenada {
    public static final double RADIO_TIERRA = 6371.0;

    @Column
    private double latitud;

    @Column
    private double longitud;

}
