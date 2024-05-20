package com.turismo.CTG.controller;


import com.turismo.CTG.model.dto.PerfilDto;
import com.turismo.CTG.model.dto.UsuarioDto;
import com.turismo.CTG.model.entity.Perfil;
import com.turismo.CTG.model.entity.Usuario;
import com.turismo.CTG.model.playload.MensajeResponse;
import com.turismo.CTG.service.IPerfilService;
import com.turismo.CTG.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IPerfilService perfilService;

    @GetMapping("/usuarios")
    public ResponseEntity<?> showAll() {
        List<Usuario> userList = usuarioService.listAll();
        if (userList.isEmpty()) {
            return ResponseEntity.ok(MensajeResponse.builder()
                    .mensaje("No hay registros")
                    .object(null)
                    .build());
        }

        return ResponseEntity.ok(MensajeResponse.builder()
                .mensaje("")
                .object(userList)
                .build());
    }


    @PostMapping("/usuarios")
    public ResponseEntity<?> create(@RequestBody UsuarioDto usuarioDto) {
        Usuario usuarioSave = null;
        try {

            // Verificar si el perfil ya existe
            if (usuarioDto.getPerfil() != null && usuarioDto.getPerfil().getIdPerfil() == null) {
                PerfilDto perfilDto = usuarioDto.getPerfil();
                Optional<Perfil> perfilExistente = perfilService.findByNombre(perfilDto.getNombrePerfil());
                if (perfilExistente.isPresent()) {
                    // Si el perfil existe, se asigna al usuario
                    perfilDto.setIdPerfil(perfilExistente.get().getIdPerfil());
                } else {
                    // Esto solo por si el perfil no existe, cosa que no serviria porque no se puede elegir
                    Perfil perfil = perfilService.save(perfilDto);
                    perfilDto.setIdPerfil(perfil.getIdPerfil());
                }
            }

                usuarioSave = usuarioService.save(usuarioDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(usuarioSave)
                    .build());
        } catch (DataAccessException exDt) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build());
        }
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> update(@RequestBody UsuarioDto usuarioDto, @PathVariable Integer id) {
        Usuario usuarioUpdate = null;
        try {
            Optional<Usuario> usuarioOptional = usuarioService.findById(id);
            if (usuarioOptional.isPresent()) {
                usuarioDto.setIdUsuario(id);
                usuarioUpdate = usuarioService.save(usuarioDto);
                return ResponseEntity.status(HttpStatus.CREATED).body(MensajeResponse.builder()
                        .mensaje("Actualizado correctamente")
                        .object(usuarioUpdate)
                        .build());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MensajeResponse.builder()
                        .mensaje("El registro que intenta actualizar no se encuentra en la base de datos.")
                        .object(null)
                        .build());
            }
        } catch (DataAccessException exDt) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build());
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Optional<Usuario> usuarioOptional = usuarioService.findById(id);
            if (usuarioOptional.isPresent()) {
                Usuario usuarioDelete = usuarioOptional.get();
                usuarioService.delete(usuarioDelete);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        MensajeResponse.builder()
                                .mensaje("El registro que intenta eliminar no se encuentra en la base de datos.")
                                .object(null)
                                .build());
            }
        } catch (DataAccessException exDt) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build());
        }
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        return usuarioOptional.map(usuario -> ResponseEntity.ok(MensajeResponse.builder()
                .mensaje("")
                .object(usuario)
                .build())).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                MensajeResponse.builder()
                        .mensaje("El registro que intenta buscar no existe")
                        .object(null)
                        .build()));
    }

}
