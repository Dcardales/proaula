package com.turismo.CTG.service;


import com.turismo.CTG.model.dto.PerfilDto;
import com.turismo.CTG.model.dto.UsuarioDto;
import com.turismo.CTG.model.entity.Perfil;
import com.turismo.CTG.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> listAll();

    Usuario save(UsuarioDto usuario);

    Optional<Usuario> findById(Integer id);

    void delete(Usuario usuario);

    boolean existsById(Integer id);

    Usuario findByNombreUsuario(String nombreUsuario);

    default PerfilDto convertirPerfilAPerfilDto(Perfil perfil){
        PerfilDto perfilDto = new PerfilDto();
        perfilDto.setIdPerfil(perfil.getIdPerfil());
        perfilDto.setNombrePerfil(perfil.getNombrePerfil());
        return perfilDto;
    }
}
