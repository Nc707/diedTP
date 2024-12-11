package com.deso.etapa_final.dto;

import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PlatoDTO extends ItemMenuDTO {
    private float calorias;
    private boolean aptoCeliaco;
    private boolean aptoVegano;
    private float peso;
}