package com.deso.etapa_final.model;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.*;

@Data
@NoArgsConstructor // Constructor sin argumentos requerido por JPA
@AllArgsConstructor // Constructor completo para pruebas o inicializaciones rápidas
@Entity
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Clave primaria con generación automática
    private Long id;

    @Column(nullable = false)
    private long fecha;

    @Column(nullable = false)
    private double monto;

    @Column(nullable = false, length = 500)
    private String resumen;

    @ManyToOne(optional = false) // Relación con la entidad Vendedor
    @JoinColumn(name = "vendedorid", nullable = false)
    private Vendedor vendedor;

    // Constructor que inicializa a partir de un Pedido
    public Pago(Pedido pedido) {
        this.fecha = new Date().getTime();
        this.monto = pedido.getPrecio();
        this.resumen = pedido.toString();
        this.vendedor = pedido.getVendedor(); // Asume que Pedido tiene un objeto Vendedor
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = format.format(new Date(this.fecha));
        return fechaFormateada + " | Vendedor: " + vendedor.getNombre() + " | Resumen: " + resumen;
    }
}
