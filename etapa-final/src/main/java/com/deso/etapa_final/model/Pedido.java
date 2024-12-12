package com.deso.etapa_final.model;

import java.util.HashSet;

import java.util.List;


import com.deso.etapa_final.model.interfaces.EstrategiasDePagoInterface;


import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Pedido  {

    public enum EstadoPedido {
        EN_CARRITO,
        RECIBIDO,
        EN_ENVIO,
        ENTREGADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedido_id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> items;

    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private int cantidad;

    // Columnas para almacenar la estrategia de pago
    @Column(name = "tipo_metodo_de_pago")
    private String tipoMetodoDePago;

    @Column(name = "datos_metodo_de_pago")
    private String datosMetodoDePago;

    @Transient
    private EstrategiasDePagoInterface metodoDePago; // No persistente, se carga/deserializa manualmente



    // Métodos para manejar serialización y deserialización
    @PrePersist
    private void serializarMetodoDePago() {
        if (metodoDePago != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                tipoMetodoDePago = metodoDePago.getClass().getSimpleName();
                datosMetodoDePago = objectMapper.writeValueAsString(metodoDePago);
            } catch (Exception e) {
                throw new RuntimeException("Error al serializar metodoDePago", e);
            }
        }
    }

    @PostLoad
    private void deserializarMetodoDePago() {
        if (tipoMetodoDePago != null && datosMetodoDePago != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Class<?> clazz = Class.forName("com.deso.etapa_final.model.estrategias." + tipoMetodoDePago);
                metodoDePago = (EstrategiasDePagoInterface) objectMapper.readValue(datosMetodoDePago, clazz);
            } catch (Exception e) {
                throw new RuntimeException("Error al deserializar metodoDePago", e);
            }
        }
    }
}
