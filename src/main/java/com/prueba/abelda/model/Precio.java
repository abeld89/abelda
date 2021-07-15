package com.prueba.abelda.model;

import com.sun.istack.NotNull;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Precios")
public class Precio {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private LocalDate fechaInicio;

  @NotNull
  private LocalDate fechaFin;

  @NotNull
  private Long precio;

  @JoinColumn(name = "id_coche", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private Coche coche;

}
