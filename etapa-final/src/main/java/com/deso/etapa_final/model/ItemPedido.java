package com.deso.etapa_final.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
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
    @JoinColumn(name = "itemid")
    private ItemMenu itemMenu;


    @ManyToOne
    @JoinColumn(name = "pedidoid")
    private Pedido pedido;

    public ItemPedido(ItemMenu itemMenu, int cantidad){
        this.itemMenu = itemMenu;
        this.setCantidad(cantidad);
    }
    public void setPedido(Pedido pedido){
        this.pedido = pedido;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
        this.precio = cantidad * itemMenu.getPrecio();
    }
}
