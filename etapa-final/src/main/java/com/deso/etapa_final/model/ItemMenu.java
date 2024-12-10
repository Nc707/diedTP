package com.deso.etapa_final.model;

import java.util.HashSet;

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

    @Column
    @Setter(AccessLevel.NONE)
    private HashSet<Categoria> categoria = new HashSet<>();

    public void addCategoria(Categoria categoria) {
        this.categoria.add(categoria);
    }
    public boolean removeCategoria(Categoria categoria) {
        return this.categoria.remove(categoria);
    }
    public void clearCategoria() {
        this.categoria.clear();
    }

    public boolean hasCategoria(Categoria categoria) {
        return this.categoria.contains(categoria);
    }

    public abstract boolean esComida();

    public abstract boolean esBebida();

    public abstract boolean aptoVegano();
    
}
