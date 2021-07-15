package com.prueba.abelda.mapper;

import com.prueba.abelda.dto.PrecioDto;
import com.prueba.abelda.model.Precio;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.util.CollectionUtils;

@Mapper
public abstract class PreciosMapper {

  public PrecioDto listToDto(List<Precio> precios) {
    if (CollectionUtils.isEmpty(precios)) {
      return null;
    }
    return toDto(precios.get(0));
  }

  public PrecioDto toDto(Precio precio) {
    PrecioDto dto = new PrecioDto();
    dto.setFechaFin(precio.getFechaFin());
    dto.setFechaInicio(precio.getFechaInicio());
    dto.setPrecio(precio.getPrecio());
    return dto;
  }

}
