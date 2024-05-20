package com.turismo.CTG.controller;


import com.turismo.CTG.model.dto.PerfilDto;
import com.turismo.CTG.model.entity.Perfil;
import com.turismo.CTG.model.entity.Usuario;
import com.turismo.CTG.model.playload.MensajeResponse;
import com.turismo.CTG.service.IPerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
  public class PerfilController {

      @Autowired
      private IPerfilService perfilService;

      @GetMapping("/perfiles")
      public ResponseEntity<MensajeResponse> showAll() {
          List<Perfil> perfilList = perfilService.listAll();
          if (perfilList.isEmpty()) {
              return new ResponseEntity<>(
                      MensajeResponse.builder()
                              .mensaje("No hay registros")
                              .object(null)
                              .build(),
                      HttpStatus.OK);
          }
          return new ResponseEntity<>(
                  MensajeResponse.builder()
                          .mensaje("")
                          .object(perfilList)
                          .build(),
                  HttpStatus.OK);
      }

      @PostMapping("/perfiles")
      public ResponseEntity<MensajeResponse> create(@RequestBody PerfilDto perfilDto) {
          Perfil perfilSave = null;
          try {
              perfilSave = perfilService.save(perfilDto);
              return new ResponseEntity<>(MensajeResponse.builder()
                      .mensaje("Guardado correctamente")
                      .object(PerfilDto.builder()
                              .idPerfil(perfilSave.getIdPerfil())
                              .nombrePerfil(perfilSave.getNombrePerfil())
                              .build())
                      .build(),
                      HttpStatus.CREATED);
          } catch (DataAccessException exDt) {
              return new ResponseEntity<>(
                      MensajeResponse.builder()
                              .mensaje(exDt.getMessage())
                              .object(null)
                              .build(),
                      HttpStatus.METHOD_NOT_ALLOWED);
          }
      }

      @PutMapping("/perfiles/{id}")
      public ResponseEntity<MensajeResponse> update(@RequestBody PerfilDto perfilDto, @PathVariable Integer id) {
          Perfil perfilUpdate = null;
          try {
              if (perfilService.existsById(id)) {
                  perfilDto.setIdPerfil(id);
                  perfilUpdate = perfilService.save(perfilDto);
                  return new ResponseEntity<>(MensajeResponse.builder()
                          .mensaje("Actualizado correctamente")
                          .object(PerfilDto.builder()
                                  .idPerfil(perfilUpdate.getIdPerfil())
                                  .nombrePerfil(perfilUpdate.getNombrePerfil())
                                  .build())
                          .build(),
                          HttpStatus.CREATED);
              } else {
                  return new ResponseEntity<>(
                          MensajeResponse.builder()
                                  .mensaje("El registro que intenta actualizar no se encuentra en la base de datos.")
                                  .object(null)
                                  .build(),
                          HttpStatus.NOT_FOUND);
              }
          } catch (DataAccessException exDt) {
              return new ResponseEntity<>(
                      MensajeResponse.builder()
                              .mensaje(exDt.getMessage())
                              .object(null)
                              .build(),
                      HttpStatus.METHOD_NOT_ALLOWED);
          }
      }

      @DeleteMapping("/perfiles/{id}")
      public ResponseEntity<MensajeResponse> delete(@PathVariable Integer id) {
          try {
              Perfil perfilDelete = perfilService.findById(id);
              perfilService.delete(perfilDelete);
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } catch (DataAccessException exDt) {
              return new ResponseEntity<>(
                      MensajeResponse.builder()
                              .mensaje(exDt.getMessage())
                              .object(null)
                              .build(),
                      HttpStatus.METHOD_NOT_ALLOWED);
          }
      }

      @GetMapping("/perfiles/{id}")
      public ResponseEntity<MensajeResponse> showById(@PathVariable Integer id) {
          Perfil perfil = perfilService.findById(id);

          if (perfil == null) {
              return new ResponseEntity<>(
                      MensajeResponse.builder()
                              .mensaje("El registro que intenta buscar no existe.")
                              .object(null)
                              .build(),
                      HttpStatus.NOT_FOUND);
          }

          return new ResponseEntity<>(
                  MensajeResponse.builder()
                          .mensaje("")
                          .object(PerfilDto.builder()
                                  .idPerfil(perfil.getIdPerfil())
                                  .nombrePerfil(perfil.getNombrePerfil())
                                  .build())
                          .build(),
                  HttpStatus.OK);
      }
  }
