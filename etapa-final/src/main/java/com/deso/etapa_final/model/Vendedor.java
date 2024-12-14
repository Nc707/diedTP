package com.deso.etapa_final.model;

import jakarta.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vendedor{

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendedorid;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String direccion;

    @Column
    @Embedded
    private Coordenada coordenadas;

    public Vendedor(String nombre, String direccion, Coordenada coordenadas) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.coordenadas = coordenadas;
    }

}