package com.prueba.abelda.repository;

import com.prueba.abelda.model.ReportApi;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportApiRepository extends MongoRepository<ReportApi, String> {
}
