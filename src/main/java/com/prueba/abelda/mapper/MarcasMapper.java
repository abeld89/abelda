package com.prueba.abelda.mapper;

import com.prueba.abelda.dto.MarcaDto;
import com.prueba.abelda.model.Marca;
import org.mapstruct.Mapper;

@Mapper
public interface MarcasMapper {
  MarcaDto toDto(Marca marca);
}
