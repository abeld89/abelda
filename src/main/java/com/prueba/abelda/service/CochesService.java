package com.prueba.abelda.service;

import com.prueba.abelda.dto.CocheDto;
import com.prueba.abelda.util.CocheExcelExporter;
import java.util.List;

public interface CochesService {
  CocheExcelExporter getAll();

  CocheDto getByIdAndDate(Long id, String fecha);

  List<CocheDto> getCochesByMarcas(String marca);

  CocheDto getById(Long id);
}
