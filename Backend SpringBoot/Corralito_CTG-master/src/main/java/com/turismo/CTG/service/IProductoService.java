package com.turismo.CTG.service;


import com.turismo.CTG.model.dto.ProductoDto;
import com.turismo.CTG.model.entity.Producto;

import java.util.List;

public interface IProductoService {

    List<Producto> listAll();

    Producto save(ProductoDto producto); // Cambio en el parámetro

    Producto findById(Integer id);

    void delete(Integer id); // Cambio en el parámetro

    boolean existsById(Integer id);
}
