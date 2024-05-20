package com.turismo.CTG.model.dto;


import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZonaDto implements Serializable {

    private Integer idZona;
    private String nombreZona;

    public ZonaDto(String nombreZona) {
        this.nombreZona = nombreZona;
    }

}
