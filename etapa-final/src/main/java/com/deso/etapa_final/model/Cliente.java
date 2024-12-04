package com.deso.etapa_final.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private long cuit;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private Coordenada coordenadas;

    @Column(nullable = false)
    private String nombre;

}