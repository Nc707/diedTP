package com.deso.etapa_final.model;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.*;

@Data
@NoArgsConstructor // Constructor sin argumentos requerido por JPA
@AllArgsConstructor
@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    private Double monto;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;

    public Pago(Pedido pedido) {
        this.pedido = pedido;
        this.monto = pedido.getPrecio();
        this.fechaPago = new Date();
    }

    public String getResumen() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Cliente cliente = pedido.getCliente(); 
        Vendedor vendedor = pedido.getVendedor(); 
        return String.format("Pago ID: %d, Pedido ID: %d, Cliente: %s, Vendedor: %s, Monto: %.2f, Fecha de Pago: %s",
                id, pedido.getPedidoid(), cliente.getNombre(), vendedor.getNombre(), monto, sdf.format(fechaPago));
    }
}
