package com.deso.etapa_final.model;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "vendedorid", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Vendedor vendedor;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "clienteid", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ItemPedido> items;

    @Column
    private String descripcion;

    @Column(nullable = false)
    private Double precio = 0.0d;

    @Column(nullable = false)
    private int cantidad;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "metodopagoid")
    private EstrategiasDePago metodoDePago; 

    public String getTipoMetodoDePago() {
        if (metodoDePago != null) {
            return metodoDePago.getClass().getSimpleName();
        }
        return "No seteado";
    }
}

