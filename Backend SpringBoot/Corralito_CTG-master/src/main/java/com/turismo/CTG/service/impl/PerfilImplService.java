package com.turismo.CTG.service.impl;



import com.turismo.CTG.model.dao.PerfilDao;
import com.turismo.CTG.model.dto.PerfilDto;
import com.turismo.CTG.model.entity.Perfil;
import com.turismo.CTG.service.IPerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilImplService implements IPerfilService {


    @Autowired
    private PerfilDao perfilDao;

    @Override
    public List<Perfil> listAll() {
        return (List<Perfil>) perfilDao.findAll();
    }

    @Transactional
    @Override
    public Perfil save(PerfilDto perfilDto) {

        // Verificar si el perfil ya existe en la base de datos
        if (perfilDto.getIdPerfil() != null && existsById(perfilDto.getIdPerfil())) {
            Perfil perfilExistente = findById(perfilDto.getIdPerfil());
            perfilExistente.setNombrePerfil(perfilDto.getNombrePerfil());
            return perfilDao.save(perfilExistente);
        } else {
            Perfil perfilNuevo = Perfil.builder()
                    .nombrePerfil(perfilDto.getNombrePerfil())
                    .build();
            return perfilDao.save(perfilNuevo);
        }

    }

    @Transactional(readOnly = true)
    @Override
    public Perfil findById(Integer id) {
        return perfilDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Perfil perfil) {
        perfilDao.delete(perfil);
    }

    @Override
    public boolean existsById(Integer id) {
        return perfilDao.existsById(id);
    }

    public Optional<Perfil> findByNombre(String nombrePerfil) {
        return perfilDao.findByNombrePerfil(nombrePerfil);
    }

}
