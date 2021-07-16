package com.prueba.abelda.controller;

import com.prueba.abelda.dto.CocheDto;
import com.prueba.abelda.model.ReportApi;
import com.prueba.abelda.model.ServiceEnum;
import com.prueba.abelda.repository.ReportApiRepository;
import com.prueba.abelda.service.CochesService;
import com.sun.istack.NotNull;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.mapstruct.Context;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("coches")
public class CochesController {

  private final ReportApiRepository apiRepository;

  private final CochesService service;

  @GetMapping(path = "{id}")
  public CocheDto getCoche(@Context HttpServletRequest request, @PathVariable @NotNull final Long id) {
    apiRepository.insert(new ReportApi(remoteIp(request), LocalDateTime.now(), ServiceEnum.GET_BY_ID.toString()));
    return service.getById(id);
  }

  @GetMapping(path = "/{id}/{fecha}")
  public CocheDto getCocheByFecha(@Context HttpServletRequest request, @PathVariable @NotNull final Long id, @PathVariable @NotNull String fecha) {
    apiRepository.insert(new ReportApi(remoteIp(request), LocalDateTime.now(), ServiceEnum.GET_BY_ID_AND_DATE.toString()));
    return service.getByIdAndDate(id, fecha);
  }

  @GetMapping
  public List<CocheDto> getCochesByMarca(@Context HttpServletRequest request, @RequestParam(value = "filter") String marca) {
    apiRepository.insert(new ReportApi(remoteIp(request), LocalDateTime.now(), ServiceEnum.GET_BY_MARCA.toString()));
    return service.getCochesByMarcas(marca);
  }

  @PostMapping({"/excel/xlsx"})
  public void exportToExcel(@Context HttpServletRequest request, HttpServletResponse response) throws IOException {
    apiRepository.insert(new ReportApi(remoteIp(request), LocalDateTime.now(), ServiceEnum.EXPORT_EXCEL.toString()));
    response.setContentType("application/octet-stream");
    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=coches.xlsx";
    response.setHeader(headerKey, headerValue);
    service.getAll().export(response);
  }

  private String remoteIp(HttpServletRequest request){
    String remoteAddr = "";

    if (request != null) {
      remoteAddr = request.getHeader("X-FORWARDED-FOR");
      if (remoteAddr == null || "".equals(remoteAddr)) {
        remoteAddr = request.getRemoteAddr();
      }
    }

    return remoteAddr;
  }

}
