package com.deso.etapa_final.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long pedido_id;
    private String estado;
    private VendedorDTO vendedor;
    private ClienteDTO cliente;
    private String descripcion;
    private Double precio;
    private int cantidad;
    private List<ItemPedidoDTO> items;
}