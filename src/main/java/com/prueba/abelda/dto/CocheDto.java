package com.prueba.abelda.dto;

import lombok.Data;

@Data
public class CocheDto {

  private Long id;

  private Long idMarca;

  private String modelo;

  private String color;

  private PrecioDto precio;
}
