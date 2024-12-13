package com.deso.etapa_final.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteid;

    @Column(nullable = false)
    private Long cuit;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String direccion;

    @Embedded
    @Column(nullable = false)
    private Coordenada coordenadas;

    @Column(nullable = false)
    private String nombre;
}