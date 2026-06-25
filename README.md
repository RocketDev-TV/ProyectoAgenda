# 📅 Sistema de Agenda y Contactos (API & Web)

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.12-brightgreen?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-blue?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=for-the-badge&logo=docker)
![Azure](https://img.shields.io/badge/Azure-Cloud-0078D4?style=for-the-badge&logo=microsoftazure)

Plataforma integral basada en un modelo **Cliente-Servidor** para la gestión y agendamiento de citas, administración de contactos y manejo de perfiles. Desarrollada bajo arquitectura de capas, integrando geolocalización espacial y validación estricta de horarios.

* **Institución:** UPIICSA - Instituto Politécnico Nacional (IPN)
* **Proyecto:** Sistema de Citas (3er Parcial)
* **Materia:** Programación Web / Ingeniería en Informática

---

## 🚀 Características Clave

* **Arquitectura N-Tier:** Desacoplamiento total entre la lógica de negocio (Dominio) y la infraestructura (Base de datos, API REST).
* **Geolocalización (PostGIS):** Almacenamiento y consulta de coordenadas geográficas para ubicación de sucursales o puntos de encuentro.
* **Gestión de Horarios (Timezone Sensitive):** Sincronización estricta con zona horaria `America/Mexico_City` para evitar conflictos en citas (UTC handling).
* **Seguridad:** Encriptación de contraseñas con SHA-512 y gestión de roles (Admin, Empleado, Cliente).
* **Despliegue Cloud & Contenedores:** Optimizado para ejecución en servidores Linux (Ubuntu) en Microsoft Azure y paquetizado con Docker.

---

## 🛠️ Stack Tecnológico

* **Lenguaje:** Java 17 (JDK)
* **Framework:** Spring Boot 3.4.12
* **Base de Datos:** PostgreSQL 14 + Extensiones PostGIS
* **ORM:** Hibernate Spatial
* **Build Tool:** Maven
* **Utilerías:** Lombok, Jakarta EE

---

## ⚙️ Configuración de Base de Datos

El proyecto incluye scripts automatizados para entornos locales y de producción.

### Opción A: Entorno Local (Desarrollo)
1. Navega a la carpeta de scripts:

   ```bash
   cd base-datos/scripts/local
   ```

### Ejecuta el constructor maestro (requiere cliente psql):

   ```bash
   sudo -u postgres psql -f build.sql
   ```

### Opción B: Producción (Seed Limpio)
Para restaurar la base de datos a un estado de fábrica (5 sucursales, 10 empleados, catálogos limpios):

   ```bash
   psql -d citas -f base-datos/scripts/local/respaldo_produccion.sql
   ```

▶️ Ejecución y Despliegue
1. Despliegue con Docker (Recomendado)
Levanta la base de datos y el backend con un solo comando utilizando el docker-compose.yml incluido:

```bash 
▶docker-compose up -d --build
```

Entorno Local (IDE / Terminal)

```bash 
mvn spring-boot:run
```
Acceso: http://localhost:8080

3. Producción (Linux / Azure VM)
Para ejecución en segundo plano (Daemon) asegurando la zona horaria correcta:

```bash 
nohup java -Duser.timezone=America/Mexico_City -jar target/sistema-citas-0.0.1-SNAPSHOT.jar \
  --spring.datasource.url=jdbc:postgresql://localhost:5432/citas \
  --spring.datasource.username=postgres \
  --spring.datasource.password=TU_PASSWORD > log.txt 2>&1 &
```

🔌 Endpoints Principales
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| **POST** | `/api/citas` | Agendar nueva cita (Valida disponibilidad y horario laboral) |
| **GET** | `/api/citas` | Listar citas (Filtros por rol de usuario) |
| **POST** | `/api/sucursales` | Registrar sucursal con punto geográfico (Lat/Lon) |
| **GET** | `/api/servicios` | Catálogo de servicios y precios vigentes |
| **POST** | `/api/usuarios/login` | Autenticación de usuarios |

Hecho con ☕, código y algo de indie rock punk alternativo en la CDMX.
