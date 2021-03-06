CREATE TABLE MARCAS (
  ID BIGINT NOT NULL AUTO_INCREMENT,
  MARCA VARCHAR (255) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE COCHES (
  ID BIGINT NOT NULL AUTO_INCREMENT,
  MODELO VARCHAR(255) NOT NULL,
  COLOR VARCHAR(255) NOT NULL,
  ID_MARCA BIGINT NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE PRECIOS (
  ID BIGINT(255) NOT NULL AUTO_INCREMENT,
  FECHA_INICIO DATE,
  FECHA_FIN DATE,
  PRECIO BIGINT,
  ID_COCHE BIGINT NOT NULL,
  PRIMARY KEY (ID)
);

ALTER TABLE COCHES ADD CONSTRAINT FK_COCHES_MARCAS FOREIGN KEY (ID_MARCA) REFERENCES MARCAS (ID);
ALTER TABLE PRECIOS ADD CONSTRAINT FK_PRECIOS_COCHES FOREIGN KEY (ID_COCHE) REFERENCES COCHES (ID);