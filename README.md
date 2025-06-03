# SaborifyAPI


### CLONAR REPOSITORIO

```bash
git clone https://github.com/HugoPzo/SaborifyAPI.git
```

### CAMBIAR AL REPOSITORIO

```bash
cd SaborifyAPI
```

### EJECUTAR CON

```bash
docker-compose build
docker-compose up
```

### URL

http://localhost:8080/api/recipes

---

````markdown
**SaborifyAPI** es una API RESTful desarrollada con Spring Boot que permite la gestión de recetas de cocina. Utiliza una arquitectura basada en capas (Controladores, Servicios, Repositorios y Entidades) y está preparada para ejecutarse en contenedores Docker junto con una base de datos MySQL.

---

## 🧩 Arquitectura

El proyecto está estructurado en las siguientes capas:

- **Entity**: Define la clase `Recipe`, que representa una receta de cocina.
- **Repository**: Acceso a datos mediante JPA.
- **Service**: Lógica de negocio, implementada en una interfaz (`RecipeService`) y su implementación.
- **Controller**: Define los endpoints REST para interactuar con las recetas.

---

## 📦 Endpoints principales

- `GET /api/recipes`: Obtiene todas las recetas.
- `GET /api/recipes/{id}`: Obtiene una receta por su ID.
- `POST /api/recipes`: Crea una nueva receta (requiere validación).
- `PUT /api/recipes/{id}`: Actualiza completamente una receta existente.
- `PATCH /api/recipes/{id}`: Actualiza parcialmente una receta.
- `DELETE /api/recipes/{id}`: Elimina una receta por su ID.

---

## ⚙️ Tecnologías utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL 8**
- **Docker / Docker Compose**
- **Maven**

---

## 🐳 Cómo ejecutar con Docker

1. **Clona este repositorio**

```bash
git clone https://github.com/tu-usuario/saborifyapi.git
cd saborifyapi
````

2. **Construye la imagen y ejecuta los contenedores**

```bash
docker-compose build
docker-compose up
```

3. La API estará disponible en: [http://localhost:8080/api/recipes](http://localhost:8080/api/recipes)

La base de datos MySQL estará expuesta en el puerto `3310` de tu máquina local.

---

## 🛠️ Configuración de entorno

Los parámetros de conexión a la base de datos se inyectan como variables de entorno en el contenedor `springboot-app`:

```yaml
SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/saborify
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=12345
```

Y el archivo `application.properties` de Spring está preparado para leer estas variables.

---

## 🧪 Validación y manejo de errores

La API incluye validaciones con `jakarta.validation` y maneja errores de validación con un `@ExceptionHandler`, que devuelve un `BAD_REQUEST` con los campos y mensajes de error detallados.

---

## 📁 Estructura del proyecto

```
src/main/java/mx/unam/aragon/saborifyapi/
├── controllers/
│   └── RecipeController.java
├── entities/
│   └── Recipe.java
├── repositories/
│   └── RecipeRepository.java
├── services/
│   ├── interfaces/
│   │   └── RecipeService.java
│   └── impl/
│       └── RecipeServiceImpl.java
```

---

## 📌 Notas

* Las operaciones `PUT` y `PATCH` son diferenciadas según si se requiere una actualización total o parcial.
* La API retorna errores 404 si no se encuentra la receta solicitada.
* El contenedor de la app espera a que MySQL esté listo antes de iniciar gracias a `depends_on` y `healthcheck`.

---

## 🧾 Licencia

Este proyecto es de uso libre para fines académicos y de desarrollo. Puedes adaptarlo a tus necesidades.

---

## 👨‍💻 Autor

Proyecto desarrollado por Hugo Perez Ortiz - FES Aragón - UNAM
