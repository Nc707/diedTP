package com.deso.etapa_final.dto;

import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class BebidaDTO extends ItemMenuDTO {
    private float graduacionAlcoholica;
    private float tamaño;
    private float peso;
}