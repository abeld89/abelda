package com.prueba.abelda.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Valid
public class CocheRequestDto {

  @NotEmpty
  private Long id;

  private String fecha;
}
