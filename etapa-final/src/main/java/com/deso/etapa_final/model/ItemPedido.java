package com.deso.etapa_final.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
public class ItemPedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private int cantidad;

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private float precio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private ItemMenu itemMenu;


    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
        this.precio = cantidad * itemMenu.getPrecio();
    }
}
