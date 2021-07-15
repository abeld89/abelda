package com.prueba.abelda.mapper;

import com.prueba.abelda.dto.CocheDto;
import com.prueba.abelda.dto.CocheRequestDto;
import com.prueba.abelda.model.Coche;
import java.util.List;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(uses = {PreciosMapper.class, MarcasMapper.class}, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValueMappingStrategy =
    NullValueMappingStrategy.RETURN_NULL)
public interface CochesMapper {

  @Mapping(target = "idMarca", source = "marca.id")
  @Mapping(target = "precio", source = "precios")
  CocheDto toDto(Coche coche);

  List<CocheDto> toDtoList(List<Coche> coches);

  Coche toEntity(CocheRequestDto cocheRequest);

}
