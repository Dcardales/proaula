package com.turismo.CTG.model.dto;


import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDto implements Serializable {

    private Integer idCategoia;
    private String nombreCategoria;

    public CategoriaDto(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}

