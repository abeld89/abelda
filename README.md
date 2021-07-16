# CONCESIONARIO COCHES

## Introduction
Mini proyecto para la consulta de coches en una base de datos H2 en memoria, donde se almacena en una base de datos no relacional (mongodb) las llamadas al API.

### Communication & Dependencies
Este microservicio usa el protocolo REST para exponer un servicio que se comunica con los repositorios pertinentes en H2 y mongodb.

## Endpoints & Services
El API Rest permite interactuar con la base de datos precargada para mostrar los datos.

* __GET__ `/coches/{id}`: Te devuelve la información del coche por su identificador y con el precio de la fecha actual.
* __GET__ `/coches/{id}/{fecha}`: Te devuelve la información del coche con el precio que corresponda con la fecha indicada.
* __GET__ `/coches?filter={marca}`: Obtienes todos los coches con la marca indicada.
* __POST__ `/coches`: Exporta toda la información de la tabla coches en formato xlsx.

## Test
Para que este servicio funciona correctamente es necesario arrancar

`docker-compose -f env/docker-compose.yml up`

## Additional
Se han implementado todos los requisitos del documento técnico a excepción de todos los test y los códigos HTTP de errores por falta de tiempo.
Solo hay dos test creados y son unitarios, para el resto tendría que configurar la base de datos H2 para que arranque en modo test y simular unos test de integración.

## Puntos a mejorar inmediatamente
- No se está controlando los posibles errores en la creación del excel y tampoco en el resto de servicios, solamente lanza una excepción no controlada.
- Añadir más test unitarios.
- Añadir test de integración levantando una base de datos H2 en memoria.
