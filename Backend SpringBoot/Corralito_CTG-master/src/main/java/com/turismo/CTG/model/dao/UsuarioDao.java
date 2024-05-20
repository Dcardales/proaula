package com.turismo.CTG.model.dao;


import com.turismo.CTG.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDao extends CrudRepository<Usuario, Integer> {
  Usuario findByNombreUsuario(String nombreUsuario);
}
