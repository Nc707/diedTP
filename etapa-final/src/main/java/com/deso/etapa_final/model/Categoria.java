package com.deso.etapa_final.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categoria {

    public enum TipoCategoria {
        PLATO,
        BEBIDA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaid;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoCategoria tipo;

}
