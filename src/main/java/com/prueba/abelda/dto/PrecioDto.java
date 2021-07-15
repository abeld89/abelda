package com.prueba.abelda.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PrecioDto {

  private LocalDate fechaInicio;

  private LocalDate fechaFin;

  private Long precio;

}
