package com.prueba.abelda.repository;

import com.prueba.abelda.model.Marca;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcasRepository extends CrudRepository<Marca, String> {

  @Query(value = "SELECT * FROM MARCAS m WHERE marca = :marca", nativeQuery = true)
  Marca findByMarca(@Param("marca") String marca);
}
