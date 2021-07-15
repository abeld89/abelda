package com.prueba.abelda.service.impl;

import com.prueba.abelda.dto.CocheDto;
import com.prueba.abelda.dto.CocheRequestDto;
import com.prueba.abelda.mapper.CochesMapper;
import com.prueba.abelda.model.Coche;
import com.prueba.abelda.repository.CochesRepository;
import com.prueba.abelda.repository.MarcasRepository;
import com.prueba.abelda.service.CochesService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CochesServiceImpl implements CochesService {

  private final CochesMapper mapper;

  private final CochesRepository cochesRepository;

  private final MarcasRepository marcasRepository;

  @Override
  public CocheDto add(CocheRequestDto request) {
    final Coche coche = cochesRepository.save(mapper.toEntity(request));
    return mapper.toDto(coche);
  }

  @Override
  public List<Coche> getAll() {
    List<Coche> cochesList = new ArrayList<>();
    cochesRepository.findAll()
        .iterator()
        .forEachRemaining(cochesList::add);
    return cochesList;
  }

  @Override
  public CocheDto getByIdAndDate(Long id, String fecha) {
    return cochesRepository.findByIdAndDate(id, getFecha(fecha))
        .map(mapper::toDto)
        .orElseThrow();
  }

  @Override
  public List<CocheDto> getCochesByMarcas(String marca) {
    return mapper.toDtoList(marcasRepository.findByMarca(marca).getCoches());
  }

  @Override
  public CocheDto getById(Long id) {
    return cochesRepository.findById(id)
        .map(mapper::toDto)
        .orElseThrow();
  }

  private LocalDate getFecha(String fecha){
    LocalDate ld = LocalDate.now();
    if (StringUtils.isNotEmpty(fecha)){
      ld = LocalDate.parse(fecha);
    }
    return ld;
  }

}
