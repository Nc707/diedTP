package com.deso.etapa_final.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {
    private Long id;
    private int cantidad;
    private float precio;
    private ItemMenuDTO itemMenu;
}