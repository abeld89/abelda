package com.prueba.abelda.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Coches")
public class Coche {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String modelo;

  private String color;

  @JoinColumn(name = "id_marca", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private Marca marca;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "coche")
  private List<Precio> precios = new ArrayList<>();

}
