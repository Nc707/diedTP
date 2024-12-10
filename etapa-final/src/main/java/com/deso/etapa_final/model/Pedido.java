package com.deso.etapa_final.model;

import java.util.HashSet;

import com.deso.etapa_final.model.interfaces.EstrategiasDePagoInterface;
import com.deso.etapa_final.model.interfaces.Observable;
import com.deso.etapa_final.model.interfaces.Observer;

import jakarta.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pedido implements Observable{

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

    @Column
    private EstrategiasDePagoInterface metodoDePago;

    @Column
    @Setter(AccessLevel.NONE)
    private HashSet<Observer> observers = new HashSet<>();

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

}