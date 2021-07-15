package com.prueba.abelda.repository;

import com.prueba.abelda.model.Coche;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CochesRepository extends CrudRepository<Coche, Long> {

  @Query("FROM Coche c, Precio p, Marca m "
      + "WHERE c.id = :id "
      + "AND p.coche.id = :id "
      + "AND m.id = c.marca.id "
      + "AND (p.fechaInicio < :fecha AND p.fechaFin > :fecha)")
  Optional<Coche> findByIdAndDate(@Param("id") Long id, @Param("fecha") LocalDate fecha);
//
//  @Query("SELECT m.coches FROM Marca m"
//      + "WHERE m = :marca")
//  List<Coche> findByMarca(@Param("marca") String marca);
}
