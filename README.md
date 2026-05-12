# Medi Connect Core

Repositorio correspondiente al núcleo funcional del sistema distribuido de gestión de citas médicas.

---

## 🧩 Descripción

Este repositorio contiene el microservicio principal del sistema:

- 📅 Gestión de citas

Además incluye la configuración de Docker Compose para levantar todo el sistema de forma integrada.

---

## 📦 Componentes incluidos

### 🔹 ms-citas

Microservicio encargado de la gestión de citas médicas.

Funciones principales:
- Crear citas
- Listar citas
- Consultar por ID
- Actualizar estado de la cita
- Eliminar o modificar citas

---

### 🔹 docker-compose.yml

Archivo encargado de levantar todos los servicios del sistema:

- Gateway
- Pacientes
- Doctores
- Citas

Permite ejecutar el sistema completo con un solo comando.

---

## 🛠️ Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL / H2
- Maven
- Docker
- Docker Compose

---

## 🧱 Arquitectura

Este repositorio representa la capa central del negocio.

El microservicio de citas se comunica con:
- Pacientes
- Doctores

Para poder generar el agendamiento.

---

## 🔗 Repositorios relacionados

- 🌐 Gateway / BFF  
  https://github.com/Matyrawr/medi-connect-gateway  

- 📚 Recursos (Pacientes y Doctores)  
  https://github.com/Matyrawr/medi-connect-resources  

---

## 🧠 Patrones de diseño aplicados

- Service Layer → lógica de negocio
- Repository Pattern → acceso a datos
- DTO Pattern → transferencia de datos
- Singleton → gestión de beans en Spring

---

## 🎯 Justificación técnica

El microservicio de citas se separa del resto para:

- Mantener independencia de negocio
- Mejorar la escalabilidad
- Facilitar mantenimiento
- Permitir integración con otros sistemas

Docker Compose permite:

- Levantar todo el sistema fácilmente
- Simular un entorno real
- Probar integración entre servicios

---

## 🚀 Beneficios

- Sistema modular
- Fácil despliegue
- Escalable
- Preparado para entornos reales
