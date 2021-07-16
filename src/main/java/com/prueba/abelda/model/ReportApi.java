package com.prueba.abelda.model;

import java.time.LocalDateTime;
import javax.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ReportApi {

  @Id
  private String id;

  private String ip;

  private LocalDateTime created;

  private String service;

  public ReportApi(String ip, LocalDateTime created, String service) {
    this.ip = ip;
    this.created = created;
    this.service = service;
  }
}
