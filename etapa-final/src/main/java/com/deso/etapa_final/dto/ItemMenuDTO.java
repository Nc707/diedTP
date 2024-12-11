package com.deso.etapa_final.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ItemMenuDTO {
    private Long item_id;
    private String nombre;
    private String descripcion;
    private float precio;
    private CategoriaDTO categoria;
}