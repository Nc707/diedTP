package com.deso.etapa_final.model;
import java.util.List;

import com.deso.etapa_final.model.metodosDePago.EstrategiasDePago;

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
    private Long pedidoid;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendedorid", nullable = true)
    private Vendedor vendedor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clienteid", nullable = true)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> items;

    @Column
    private String descripcion;

    @Column(nullable = false)
    private Double precio = 0.0d;

    @Column(nullable = false)
    private int cantidad;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "metodopagoid")
    private EstrategiasDePago metodoDePago; 

    public String getTipoMetodoDePago() {
        if (metodoDePago != null) {
            return metodoDePago.getClass().getSimpleName();
        }
        return "No seteado";
    }
}

