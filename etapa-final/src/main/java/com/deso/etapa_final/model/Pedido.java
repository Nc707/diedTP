package com.deso.etapa_final.model;


import java.util.List;

import com.deso.etapa_final.model.interfaces.EstrategiasDePagoInterface;

import com.deso.etapa_final.model.metodosDePago.EstrategiasDePago;

import com.fasterxml.jackson.databind.ObjectMapper;

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

    @ManyToOne
    @JoinColumn(name = "vendedorid", nullable = false)
    private Vendedor vendedor;

    @ManyToOne
    @JoinColumn(name = "clienteid", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> items;

    @Column
    private String descripcion;

    @Column(nullable = false)
    private Double precio = 0.0d;

    @Column(nullable = false)
    private int cantidad;

    // Columnas para almacenar la estrategia de pago
    //@Column(name = "tipo_metodo_de_pago")
    //private String tipoMetodoDePago;

    //@Column(name = "datos_metodo_de_pago")
    //private String datosMetodoDePago;


    //@Transient
    @ManyToOne
    @JoinColumn(name = "metodopagoid")
    private EstrategiasDePago metodoDePago; // No persistente, se carga/deserializa manualmente

    public String getTipoMetodoDePago() {
        if (metodoDePago != null) {
            return metodoDePago.getClass().getSimpleName();
        }
        return "No seteado";
    }


    // // Métodos para manejar serialización y deserialización
    // @PrePersist
    // private void serializarMetodoDePago() {
    //     if (metodoDePago != null) {
    //         try {
    //             ObjectMapper objectMapper = new ObjectMapper();
    //             tipoMetodoDePago = metodoDePago.getClass().getSimpleName();
    //             datosMetodoDePago = objectMapper.writeValueAsString(metodoDePago);
    //         } catch (Exception e) {
    //             throw new RuntimeException("Error al serializar metodoDePago", e);
    //         }
    //     }
    // }

    // @PostLoad
    // private void deserializarMetodoDePago() {
    //     if (tipoMetodoDePago != null && datosMetodoDePago != null) {
    //         try {
    //             ObjectMapper objectMapper = new ObjectMapper();
    //             Class<?> clazz = Class.forName("com.deso.etapa_final.model.estrategias." + tipoMetodoDePago);
    //             metodoDePago = (EstrategiasDePagoInterface) objectMapper.readValue(datosMetodoDePago, clazz);
    //         } catch (Exception e) {
    //             throw new RuntimeException("Error al deserializar metodoDePago", e);
    //         }
    //     }
    // }
}

