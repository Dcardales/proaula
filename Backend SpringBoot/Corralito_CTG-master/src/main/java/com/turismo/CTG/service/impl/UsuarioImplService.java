package com.turismo.CTG.service.impl;


import com.turismo.CTG.model.dao.UsuarioDao;
import com.turismo.CTG.model.dto.UsuarioDto;
import com.turismo.CTG.model.entity.Usuario;
import com.turismo.CTG.service.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioImplService implements IUsuarioService {

    //Funciones
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public List<Usuario> listAll() {
        return (List<Usuario>) usuarioDao.findAll();
    }

    @Transactional
    @Override
    public Usuario save(UsuarioDto usuarioDto) {
        Usuario usuario = modelMapper.map(usuarioDto, Usuario.class);

        usuario.setEstado(usuarioDto.getEstado());
        usuario.setTipoIdentificacion(usuarioDto.getTipoIdentificacion());

        return usuarioDao.save(usuario);

    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Usuario> findById(Integer id) {
        return usuarioDao.findById(id);
    }

    @Transactional
    @Override
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Override
    public boolean existsById(Integer id) {
        return usuarioDao.existsById(id);
    }

    @Override
    public Usuario findByNombreUsuario(String nombreUsuario) {
        return usuarioDao.findByNombreUsuario(nombreUsuario);
    }

}
