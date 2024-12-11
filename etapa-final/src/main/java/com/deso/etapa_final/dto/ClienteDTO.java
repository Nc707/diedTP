package com.deso.etapa_final.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Long cliente_id;
    private Long cuit;
    private String email;
    private String direccion;
    private CoordenadaDTO coordenadas;
    private String nombre;
}