package com.deso.etapa_final.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED) // O SINGLE_TABLE, según el diseño deseado
@DiscriminatorColumn(name = "tipo_item", discriminatorType = DiscriminatorType.STRING)
@Entity
public abstract class ItemMenu {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column
    private String descripcion;

    @Column(nullable = false)
    private float precio;

    // @Column
    // @Setter(AccessLevel.NONE)
    @ManyToMany
    @JoinTable(
        name = "item_categoria",
        joinColumns = @JoinColumn(name = "item_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<Categoria> categorias = new HashSet<>();

    public void addCategoria(Categoria categoria) {
        this.categorias.add(categoria);
    }
    public boolean removeCategoria(Categoria categoria) {
        return this.categorias.remove(categoria);
    }
    public void clearCategoria() {
        this.categorias.clear();
    }

    public boolean hasCategoria(Categoria categoria) {
        return this.categorias.contains(categoria);
    }

    public abstract boolean esComida();

    public abstract boolean esBebida();

    public abstract boolean aptoVegano();
    
}
