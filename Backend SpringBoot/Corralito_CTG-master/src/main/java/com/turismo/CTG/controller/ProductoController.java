package com.turismo.CTG.controller;

import com.turismo.CTG.model.dto.ProductoDto;
import com.turismo.CTG.model.entity.Producto;
import com.turismo.CTG.model.playload.MensajeResponse;
import com.turismo.CTG.service.IProductoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

  @Autowired
  private IProductoService productoService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping
  public ResponseEntity<MensajeResponse> showAll() {
    List<Producto> productList = productoService.listAll();
    if (productList.isEmpty()) {
      return new ResponseEntity<>(
        MensajeResponse.builder()
          .mensaje("No hay registros")
          .object(null)
          .build(),
        HttpStatus.OK);
    }
    List<ProductoDto> productoDtoList = productList.stream()
      .map(producto -> modelMapper.map(producto, ProductoDto.class))
      .collect(Collectors.toList());

    return new ResponseEntity<>(
      MensajeResponse.builder()
        .mensaje("")
        .object(productoDtoList)
        .build(),
      HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<MensajeResponse> create(@RequestBody ProductoDto productoDto) {
    try {
      Producto producto = productoService.save(productoDto);
      ProductoDto productoDtoResponse = modelMapper.map(producto, ProductoDto.class);
      return ResponseEntity.status(HttpStatus.CREATED)
        .body(MensajeResponse.builder()
          .mensaje("Guardado correctamente")
          .object(productoDtoResponse)
          .build());
    } catch (DataAccessException exDt) {
      return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
        .body(MensajeResponse.builder()
          .mensaje(exDt.getMessage())
          .object(null)
          .build());
    }
  }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeResponse> update(@RequestBody ProductoDto productoDto, @PathVariable Integer id) {
        try {
            if (productoService.existsById(id)) {
                productoDto.setIdProducto(id);
                Producto productoUpdate = productoService.save(productoDto);
                ProductoDto productoDtoResponse = modelMapper.map(productoUpdate, ProductoDto.class);
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(MensajeResponse.builder()
                                .mensaje("Actualizado correctamente")
                                .object(productoDtoResponse)
                                .build());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(MensajeResponse.builder()
                                .mensaje("El registro que intenta actualizar no se encuentra en la base de datos.")
                                .object(null)
                                .build());
            }
        } catch (DataAccessException exDt) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body(MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeResponse> delete(@PathVariable Integer id) {
        try {
            Producto productoDelete = productoService.findById(id);
            if (productoDelete == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(MensajeResponse.builder()
                                .mensaje("El registro que intenta eliminar no existe.")
                                .object(null)
                                .build());
            }
            productoService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (DataAccessException exDt) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body(MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensajeResponse> showById(@PathVariable Integer id) {
        Producto producto = productoService.findById(id);

        if (producto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(MensajeResponse.builder()
                            .mensaje("El registro que intenta buscar no existe.")
                            .object(null)
                            .build());
        }

        ProductoDto productoDtoResponse = modelMapper.map(producto, ProductoDto.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(MensajeResponse.builder()
                        .mensaje("")
                        .object(productoDtoResponse)
                        .build());
    }
}
