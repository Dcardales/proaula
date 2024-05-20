package com.turismo.CTG.service.impl;


import com.turismo.CTG.model.dao.CategoriaDao;
import com.turismo.CTG.model.dto.CategoriaDto;
import com.turismo.CTG.model.entity.Categoria;
import com.turismo.CTG.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoriaImplService implements ICategoriaService {

    private final CategoriaDao categoriaDao;

    @Autowired
    public CategoriaImplService(CategoriaDao categoriaDao) {
        this.categoriaDao = categoriaDao;
    }

    @Override
    public List<Categoria> listAll() {
        Iterable<Categoria> iterable = categoriaDao.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Categoria save(CategoriaDto categoriaDto) {

        if (categoriaDto.getIdCategoia() != null && existsById(categoriaDto.getIdCategoia())) {
            Categoria categoriaExistente = findById(categoriaDto.getIdCategoia());
            categoriaExistente.setNombreCategoria(categoriaDto.getNombreCategoria());
            return categoriaDao.save(categoriaExistente);
        } else {
            Categoria categoriaNueva = Categoria.builder()
                    .nombreCategoria(categoriaDto.getNombreCategoria())
                    .build();
            return categoriaDao.save(categoriaNueva);
        }

    }

    @Override
    public Categoria findById(Integer id) {
        Optional<Categoria> optionalCategoria = categoriaDao.findById(id);
        return optionalCategoria.orElse(null);
    }

    @Override
    public void delete(Categoria categoria) {
        categoriaDao.delete(categoria);
    }

    @Override
    public boolean existsById(Integer id) {
        return categoriaDao.existsById(id);
    }
}
