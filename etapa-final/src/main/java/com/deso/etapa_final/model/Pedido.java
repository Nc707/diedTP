package com.deso.etapa_final.model;

import jakarta.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pedido {

    public enum EstadoPedido {
        EN_CARRITO,
        RECIBIDO,
        EN_ENVIO,
        ENTREGADO
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private EstadoPedido estado;


    @Column(nullable = false)
    private Vendedor vendedor;

    @Column(nullable = false)
    private Cliente cliente;

    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private int cantidad;

}