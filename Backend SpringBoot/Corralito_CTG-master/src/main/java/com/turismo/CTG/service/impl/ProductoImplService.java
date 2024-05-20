package com.turismo.CTG.service.impl;

import com.turismo.CTG.model.dao.ProductoDao;
import com.turismo.CTG.model.dto.ProductoDto;
import com.turismo.CTG.model.entity.Categoria;
import com.turismo.CTG.model.entity.Producto;
import com.turismo.CTG.model.entity.Zona;
import com.turismo.CTG.service.IProductoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoImplService implements IProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Producto> listAll() {
        return (List<Producto>) productoDao.findAll();
    }

    @Transactional
    @Override
    public Producto save(ProductoDto productoDto) {
        Producto producto = modelMapper.map(productoDto, Producto.class);
        return productoDao.save(producto);
    }

    @Transactional(readOnly = true)
    @Override
    public Producto findById(Integer id) {
        return productoDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        productoDao.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return productoDao.existsById(id);
    }
}
