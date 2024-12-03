package com.deso.etapa_final.model;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class ItemMenu {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column
    private String descripcion;

    @Column(nullable = false)
    private float precio;

    public abstract boolean esComida();

    public abstract boolean esBebida();

    public abstract boolean aptoVegano();
    
}
