# Agenda Web — Gestión de Contactos

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-brightgreen?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=for-the-badge&logo=docker)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-SSR-005F0F?style=for-the-badge&logo=thymeleaf)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-7952B3?style=for-the-badge&logo=bootstrap)

Plataforma web basada en el patrón **MVC** para la gestión de una agenda personal. Permite a los usuarios registrarse, iniciar sesión y administrar contactos con múltiples medios de comunicación (correos, teléfonos, redes sociales) en una interfaz responsiva y moderna.

> **Institución:** UPIICSA — Instituto Politécnico Nacional · Programación Web / Ingeniería en Informática

---

## Características

- **Arquitectura orientada a dominios** — código organizado en módulos de negocio independientes (`contacts`, `controlacceso`, `medio`, `tipo`), cada uno con sus propias capas de controlador, lógica de negocio y persistencia.
- **Renderizado del lado del servidor (SSR)** — vistas dinámicas generadas con Spring MVC + Thymeleaf; sin SPA ni API REST expuesta.
- **Autenticación por sesiones** — control de acceso y protección de rutas mediante filtro HTTP nativo (`AuthenticationFilter`) y cookies `JSESSIONID`.
- **UI/UX responsiva** — interfaz construida con Bootstrap 5.3, Font Icons y SweetAlert2 para notificaciones no intrusivas.
- **Integridad referencial** — esquema relacional normalizado con llaves foráneas y restricciones `UNIQUE` que evitan duplicidad de cuentas y contactos.
- **Hot reload en desarrollo** — Docker Compose Watch sincroniza plantillas Thymeleaf y reconstruye el contenedor al detectar cambios en Java o `pom.xml`.

---

## Stack tecnológico

| Capa | Tecnología |
|---|---|
| Backend | Java 21, Spring Boot 3.2.5 |
| Frontend | HTML5, Thymeleaf, Bootstrap 5.3, SweetAlert2 |
| Base de datos | PostgreSQL 16 |
| ORM | Spring Data JPA / Hibernate |
| Build | Maven |
| Infraestructura | Docker & Docker Compose |

---

## Estructura del proyecto

```
ejemplo-04/
├── database/
│   ├── create.sql          # DDL — creación de tablas y restricciones
│   └── poblado.sql         # Seed — catálogo inicial de tipos de medio
├── src/main/java/.../
│   ├── contacts/           # Módulo de gestión de contactos
│   ├── controlacceso/      # Módulo de autenticación y usuarios
│   ├── medio/              # Módulo de medios de contacto
│   ├── tipo/               # Módulo de tipos de medio (catálogo)
│   └── security/           # Filtro de autenticación HTTP
├── Dockerfile
└── docker-compose.yml
```

---

## Despliegue rápido con Docker

El proyecto está dockerizado para un entorno "plug & play". No necesitas instalar PostgreSQL ni configurar variables de entorno localmente.

**1. Clonar el repositorio**

```bash
git clone <url-del-repo>
cd ProyectoAgenda/ejemplo-04
```

**2. Construir y levantar los contenedores**

```bash
docker compose up --build
```

Al primer arranque, PostgreSQL ejecuta automáticamente:
- `01-create.sql` — crea las tablas y relaciones
- `02-seed.sql` — puebla el catálogo de tipos de medio

**3. Acceder a la aplicación**

Una vez que Spring Boot inicie correctamente:

```
http://localhost:8080
```

---

## Comandos útiles

```bash
# Levantar en segundo plano
docker compose up -d --build

# Ver logs en tiempo real
docker compose logs -f app

# Detener y eliminar contenedores (conserva los datos)
docker compose down

# Detener y eliminar contenedores + volúmenes (reset completo de BD)
docker compose down -v
```

---

## Variables de entorno

Las siguientes variables son inyectadas automáticamente por `docker-compose.yml`. Para un entorno externo ajústalas según corresponda:

| Variable | Valor por defecto |
|---|---|
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://db:5432/agenda-web` |
| `SPRING_DATASOURCE_USERNAME` | `postgres` |
| `SPRING_DATASOURCE_PASSWORD` | `postgres` |

> **Nota de seguridad:** Cambia las credenciales antes de cualquier despliegue en entornos accesibles públicamente.

---

## Requisitos previos

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) ≥ 24 (incluye Docker Compose v2)
- Puerto **8080** y **5433** disponibles en tu máquina

---

## Capturas de pantalla

> *Próximamente — agrega tus capturas en `/docs/screenshots/` y referéncialas aquí.*

---

*Hecho con ☕, código y algo de indie rock punk alternativo en la CDMX.*
