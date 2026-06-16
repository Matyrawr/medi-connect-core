# Medi Connect Core

Repositorio correspondiente al núcleo funcional del sistema distribuido de gestión de citas médicas.

---

## 🧩 Descripción

Este repositorio contiene el microservicio principal encargado de la gestión de citas médicas y la configuración necesaria para desplegar el sistema completo.

Incluye:

* 📅 Microservicio de citas (`ms-citas`)
* 🐳 Configuración Docker Compose para integración del sistema

---

## 📦 Componentes incluidos

### 🔹 ms-citas

Microservicio encargado de administrar el ciclo de vida de las citas médicas.

Funciones principales:

* Crear citas médicas.
* Listar citas registradas.
* Consultar citas por ID.
* Validar disponibilidad.
* Actualizar estado de citas.
* Cancelar citas.
* Gestionar agendamientos.

---

### 🔹 docker-compose.yml

Archivo encargado de levantar toda la arquitectura distribuida del sistema.

Servicios integrados:

* 🌐 Gateway
* 👤 Pacientes
* 🩺 Doctores
* 📅 Citas

Permite ejecutar el sistema completo mediante un único comando.

---

## 🛠️ Tecnologías utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* H2 Database
* Maven
* Docker
* Docker Compose
* Swagger/OpenAPI
* JUnit 5
* Mockito

---

## 🧱 Arquitectura

Este repositorio representa el núcleo del negocio de la plataforma.

El microservicio de citas interactúa con la información administrada por:

* 👤 Pacientes
* 🩺 Doctores

para gestionar correctamente el proceso de agendamiento.

### Características

* ✔ Arquitectura basada en microservicios
* ✔ Independencia de componentes
* ✔ Escalabilidad
* ✔ Mantenimiento simplificado
* ✔ Despliegue desacoplado

---

## 💾 Persistencia

El microservicio utiliza H2 Database en modo archivo para conservar la información entre reinicios.

Esto permite:

* Mantener los datos persistentes.
* Facilitar pruebas y desarrollo.
* Reducir la complejidad de configuración.

---

## 📄 Documentación API

El servicio expone documentación interactiva mediante Swagger/OpenAPI.

Permite:

* Consultar endpoints disponibles.
* Validar operaciones REST.
* Probar funcionalidades desde el navegador.
* Facilitar el consumo de la API.

---

## 🧪 Pruebas Unitarias

Se implementaron pruebas unitarias utilizando:

* JUnit 5
* Mockito

### Pruebas implementadas

#### Gestión de citas

* Agendamiento de citas.
* Consulta de disponibilidad.
* Cancelación de citas.
* Validación de errores y excepciones.

Estas pruebas permiten validar la lógica de negocio de forma aislada y contribuyen al cumplimiento de la cobertura mínima exigida por la pauta de evaluación.

---

## 🔗 Repositorios relacionados

### 🌐 Gateway / BFF

https://github.com/Matyrawr/medi-connect-gateway

### 📚 Recursos (Pacientes y Doctores)

https://github.com/Matyrawr/medi-connect-resources

---

## 🧠 Patrones de diseño aplicados

### Service Layer Pattern

Separa la lógica de negocio del acceso a datos.

### Repository Pattern

Abstrae el acceso y manipulación de la información persistente.

### DTO Pattern

Permite intercambiar información de forma segura entre capas y servicios.

### Singleton Pattern

Gestiona eficientemente las instancias administradas por Spring.

---

## 🎯 Justificación técnica

La gestión de citas se encuentra separada del resto de los servicios debido a que representa el núcleo funcional del negocio.

Esta separación permite:

* Mantener independencia entre servicios.
* Escalar el módulo de citas de manera independiente.
* Facilitar mantenimiento y evolución del sistema.
* Permitir futuras integraciones con plataformas externas.

### Docker Compose

Docker Compose permite:

* Levantar toda la solución con un solo comando.
* Simular un entorno real de producción.
* Validar la integración entre servicios.
* Facilitar despliegues y pruebas.

---

## 🚀 Beneficios

* Arquitectura modular.
* Fácil despliegue.
* Escalabilidad independiente.
* Mejor mantenibilidad.
* Integración sencilla entre componentes.
* Preparado para entornos distribuidos.

---

## ▶️ Ejecución

### Levantar el sistema completo

```bash
docker-compose up
```

### Ejecutar el microservicio

```bash
mvn spring-boot:run
```

### Ejecutar pruebas unitarias

```bash
mvn test
```

---
