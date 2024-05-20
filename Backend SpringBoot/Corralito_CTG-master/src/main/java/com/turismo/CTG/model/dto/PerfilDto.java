package com.turismo.CTG.model.dto;


import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDto implements Serializable {

    private Integer idPerfil;
    private String nombrePerfil;

    //PERMITE QUE SE INGRESE UNA CADENA
    public PerfilDto(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

}
