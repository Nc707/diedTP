package com.deso.etapa_final.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendedorDTO {
    private Long vendedor_id;
    private String nombre;
    private String direccion;
    private CoordenadaDTO coordenadas;
}