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

    @Column(nullable = false)
    private ItemMenu itemMenu;

    @Column(nullable = false)
    private Pedido pedido;

    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
        this.precio = cantidad * itemMenu.getPrecio();
    }
}
