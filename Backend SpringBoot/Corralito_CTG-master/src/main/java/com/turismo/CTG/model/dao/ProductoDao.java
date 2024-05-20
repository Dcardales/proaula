package com.turismo.CTG.model.dao;


import com.turismo.CTG.model.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoDao extends CrudRepository<Producto, Integer> {
}
