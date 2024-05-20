package com.turismo.CTG.service.impl;



import com.turismo.CTG.model.dao.ZonaDao;
import com.turismo.CTG.model.dto.ZonaDto;
import com.turismo.CTG.model.entity.Zona;
import com.turismo.CTG.service.IZonaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ZonaImplService implements IZonaService {


    private final ZonaDao zonaDao;

    @Autowired
    public ZonaImplService(ZonaDao zonaDao) {
        this.zonaDao = zonaDao;
    }

    @Override
    public List<Zona> listAll() {
        return StreamSupport.stream(zonaDao.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Zona save(ZonaDto zonaDto) {

        if (zonaDto.getIdZona() != null && existsById(zonaDto.getIdZona())) {
            Zona zonaExistente = findById(zonaDto.getIdZona());
            zonaExistente.setNombreZona(zonaDto.getNombreZona());
            return zonaDao.save(zonaExistente);
        } else {
            Zona zonaNueva = Zona.builder()
                    .nombreZona(zonaDto.getNombreZona())
                    .build();
            return zonaDao.save(zonaNueva);
        }

    }

    @Override
    public Zona findById(Integer id) {
        return zonaDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Zona zona) {
        zonaDao.delete(zona);
    }

    @Override
    public boolean existsById(Integer id) {
        return zonaDao.existsById(id);
    }
}
