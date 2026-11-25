# ms-anti-fraude — Microservicio de Anti-Fraude

Microservicio correspondiente al **caso NovaBank** (Banca digital / fintech) de la **Evaluación Parcial N°2**.

| | |
|---|---|
| **Asignatura** | JVY0101 — Java: Diseño y Construcción de Soluciones Nativas en Nube |
| **Stack** | Spring Boot 3.5.16 · Java 17 · Maven Wrapper · Spring Data JPA · H2 Database |
| **Arquitectura** | Capas (Controller, Service, Repository, Model) |
| **Puerto** | 8085 |

## Responsabilidad (SRP)

Evalúa el riesgo de fraude de cada transacción del cliente aplicando umbrales de monto: montos superiores a $5.000.000 se clasifican como **ALTO** y se rechazan, montos sobre $1.000.000 como **MEDIO** (aprobados con alerta) y el resto como **BAJO** (aprobados). Registra el nivel de riesgo, la aprobación, el motivo y la fecha de evaluación de cada operación. Su base de datos es una **H2 en memoria** (`jdbc:h2:mem:novabank_antifraud_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`), cumpliendo aislamiento de datos por dominio.

## Consola y Persistencia

Al ejecutar el servicio en el puerto `8085`, se puede acceder a la consola de persistencia H2:

- **H2 Console**: `http://localhost:8085/h2-console`
- **JDBC URL**: `jdbc:h2:mem:novabank_antifraud_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`
- **Usuario**: `sa`
- **Contraseña**: *(en blanco)*

## Endpoints Principales (`/api/anti-fraude`)

| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/api/anti-fraude/evaluar` | Evalúa una transacción y determina su nivel de riesgo, aprobación y motivo según el monto (valida campos obligatorios); la persiste y responde 201. |
| GET | `/api/anti-fraude` | Lista todas las evaluaciones de riesgo registradas. |
| GET | `/api/anti-fraude/{id}` | Obtiene una evaluación por su id; responde 404 si no existe. |
| GET | `/api/anti-fraude/transaccion/{transaccionId}` | Obtiene la evaluación asociada a una transacción; responde 404 si no existe. |
| GET | `/api/anti-fraude/cliente/{rut}` | Lista todas las evaluaciones de un cliente según su RUT. |

## Instrucciones de Clonación, Compilación y Ejecución Local

1. **Clonar el repositorio:**
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd ms-anti-fraude
   ```

2. **Compilar y empaquetar con Maven Wrapper:**
   ```bash
   .\mvnw.cmd clean package -DskipTests
   ```

3. **Ejecutar el ejecutable .jar generado:**
   ```bash
   java -jar target/ms-anti-fraude-1.0.0.jar
   ```

## Cómo ejecutar las pruebas

```bash
.\mvnw.cmd test
```

## Ejecución EP3

Desde la carpeta de este servicio, ejecuta `docker compose up --build --wait`. El puerto local es `8085` y la salud se consulta en `http://localhost:8085/actuator/health`. Para compilar y ejecutar las pruebas con JDK 17 y Maven, usa `mvn -B -ntp clean verify`.

La imagen se construye en dos etapas y ejecuta la aplicación con UID 10001. El perfil local usa H2 y el perfil `cloud` usa PostgreSQL con credenciales montadas desde Docker Secrets.
