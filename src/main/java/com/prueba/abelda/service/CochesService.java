package com.prueba.abelda.service;

import com.prueba.abelda.dto.CocheDto;
import com.prueba.abelda.dto.CocheRequestDto;
import com.prueba.abelda.model.Coche;
import java.util.List;

public interface CochesService {

  CocheDto add(CocheRequestDto request);

  List<Coche> getAll();

  CocheDto getByIdAndDate(Long id, String fecha);

  List<CocheDto> getCochesByMarcas(String marca);

  CocheDto getById(Long id);
}
