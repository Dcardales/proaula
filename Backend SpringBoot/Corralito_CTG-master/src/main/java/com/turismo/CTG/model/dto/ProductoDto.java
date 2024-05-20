package com.turismo.CTG.model.dto;


import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDto implements Serializable {

    private Integer idProducto;
    private String nombreProducto;
    private double precioMin;
    private double precioMax;
    private CategoriaDto categoria;
    private ZonaDto zona;
}
