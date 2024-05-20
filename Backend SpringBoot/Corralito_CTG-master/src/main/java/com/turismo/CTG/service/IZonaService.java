package com.turismo.CTG.service;



import com.turismo.CTG.model.dto.ZonaDto;
import com.turismo.CTG.model.entity.Zona;

import java.util.List;

public interface IZonaService {

    List<Zona> listAll();

    Zona save(ZonaDto perfil);

    Zona findById(Integer id);

    void delete(Zona perfil);

    boolean existsById(Integer id);

    public default ZonaDto convertirZonaAZonaDto(Zona zona) {
        ZonaDto zonaDto = new ZonaDto();
        zonaDto.setIdZona(zona.getIdZona());
        zonaDto.setNombreZona(zona.getNombreZona());
        return zonaDto;
    }
}




