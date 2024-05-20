package com.turismo.CTG.model.dao;


import com.turismo.CTG.model.entity.Perfil;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PerfilDao extends CrudRepository<Perfil, Integer> {
  Optional<Perfil> findByNombrePerfil(String nombrePerfil);
}
