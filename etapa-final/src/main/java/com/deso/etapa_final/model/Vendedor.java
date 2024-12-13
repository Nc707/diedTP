package com.deso.etapa_final.model;

import jakarta.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vendedor{

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendedorid;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String direccion;

    @Column
    @Embedded
    private Coordenada coordenadas;

    public Vendedor(String nombre, String direccion, Coordenada coordenadas) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.coordenadas = coordenadas;
    }

    public Long getId() {
        return vendedorid;
    }

    public void setId(Long id) {
        this.vendedorid = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Coordenada getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Coordenada coordenadas) {
        this.coordenadas = coordenadas;
    }
    
    @Override
    public String toString() {
        return "Vendedor{" +
                "id=" + vendedorid +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", coordenadas=" + coordenadas +
                '}';
    }


}