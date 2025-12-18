# API de Gestión de Productos – Spring Boot

Esta es una API REST desarrollada con Spring Boot para gestionar productos. 
Permite crear, listar, actualizar y eliminar productos utilizando un modelo basado en DTOs y servicios.

Tecnologías:
- Java 17 (o la versión que uses)
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL / H2 / PostgreSQL (lo que uses)
- Maven o Gradle

Arquitectura: 
El proyecto sigue una arquitectura en capas:
- Controller: expone los endpoints REST.
- Service: contiene la lógica de negocio.
- Repository: maneja la persistencia con JPA.
- DTOs: objetos de transferencia de datos.
- Entities: modelos persistentes.

### Productos
- GET /api/productos
- GET /api/productos/{id}
- POST /api/productos
- PUT /api/productos/{id}
- DELETE /api/productos/{id}
