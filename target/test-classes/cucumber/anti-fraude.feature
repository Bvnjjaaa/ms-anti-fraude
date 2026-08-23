# language: es
Característica: Servicio Anti-Fraude (microservicio anti-fraude del caso caso04)
  Los escenarios validan el contrato REST del microservicio alineado a sus endpoints.

  Escenario: el listado del recurso responde 200
    Dado el servicio "Anti-Fraude" está disponible
    Cuando consulto el listado de "alertaFraudes"
    Entonces el listado responde con código 200

  Escenario: ciclo de vida completo del recurso
    Dado un nuevo "alertaFraude" con nombre "hola-cucumber"
    Cuando consulto el "alertaFraude" recién creado
    Entonces el recurso tiene nombre "hola-cucumber" y código 200
    Cuando actualizo el "alertaFraude" con nombre "cucumber-actualizado"
    Entonces el recurso queda con nombre "cucumber-actualizado" y código 200
    Cuando elimino el "alertaFraude"
    Entonces la eliminación responde con código 204
    Y al consultar el "alertaFraude" eliminado responde 404
