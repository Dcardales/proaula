package com.turismo.CTG.service;


import com.turismo.CTG.model.dto.PerfilDto;
import com.turismo.CTG.model.entity.Perfil;

import java.util.List;
import java.util.Optional;

public interface IPerfilService {

    List<Perfil> listAll();

    Perfil save(PerfilDto perfil);

    Perfil findById(Integer id);

    void delete(Perfil perfil);

    boolean existsById(Integer id);


    public default PerfilDto convertirPerfilAPerfilDto(Perfil perfil) {
        PerfilDto perfilDto = new PerfilDto();
        perfilDto.setIdPerfil(perfil.getIdPerfil());
        perfilDto.setNombrePerfil(perfil.getNombrePerfil());
        return perfilDto;
    }


    Optional<Perfil> findByNombre(String nombrePerfil);
}
