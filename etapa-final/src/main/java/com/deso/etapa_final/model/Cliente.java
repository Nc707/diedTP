package com.deso.etapa_final.model;

import com.deso.etapa_final.model.interfaces.Observable;
import com.deso.etapa_final.model.interfaces.Observer;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente implements Observer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private long cuit;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private Coordenada coordenadas;

    @Column(nullable = false)
    private String nombre;

    public void update(Observable observable) {
        Pedido pedido = (Pedido) observable;
        Pago pago = generarPago(pedido);

    }
    private Pago generarPago(Pedido pedido){
        return new Pago(pedido);
    }
}