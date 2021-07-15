package com.prueba.abelda.controller;

import com.prueba.abelda.dto.CocheDto;
import com.prueba.abelda.dto.CocheRequestDto;
import com.prueba.abelda.model.Coche;
import com.prueba.abelda.service.CochesService;
import com.sun.istack.NotNull;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("coche")
public class CochesController {

  private final CochesService service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CocheDto add(@RequestBody final CocheRequestDto request) {
    return service.add(request);
  }

  @GetMapping(path = "{id}")
  public CocheDto getCoche(@PathVariable @NotNull final Long id) {
    return service.getById(id);
  }

  @GetMapping(path = "/{id}/{fecha}")
  public CocheDto getCocheByFecha(@PathVariable @NotNull final Long id, @PathVariable @NotNull String fecha) {
    return service.getByIdAndDate(id, fecha);
  }

  @GetMapping
  public List<CocheDto> getCochesByMarca(@RequestParam(value = "filter") String marca) {
    return service.getCochesByMarcas(marca);
  }

  @PostMapping({"/excel/xlsx"})
  public void exportToExcel(HttpServletResponse response){
    response.setContentType("application/octet-stream");
    String headerKey = "Content-Disposition";
    String headerValue = "attachement, filename=coches.xlsx";

    response.setHeader(headerKey, headerValue);

    List<Coche> cocheList = service.getAll();

  }
}
