# 🧪 La Perfu - API REST de Perfumería Polimórfica

Este es el proyecto final de **La Perfu**, una API REST desarrollada con **Spring Boot** para la gestión de inventario de una perfumería. Aplica conceptos avanzados de Programación Orientada a Objetos (POO) como herencia, interfaces, validaciones personalizadas y **deserialización polimórfica** con Jackson.

---

## 🚀 Tecnologías Utilizadas

*   **Java 25** (JDK Experimental)
*   **Spring Boot 4.1.1** (Starter Web)
*   **Maven** como gestor de dependencias
*   **Jackson** para el procesamiento y mapeo de JSON polimórfico
*   **REST Client** (Extensión de VS Code) para pruebas de endpoints

---

## 🛠️ Arquitectura del Sistema

El proyecto está estructurado bajo un modelo de capas limpio para separar responsabilidades:

1.  **Modelos (`model`)**: Clase abstracta base `Producto` y sus subclases concretas:
    *   `Crema` (Atributo único: `tipoPiel`)
    *   `Perfume` (Atributo único: `genero`)
    *   `Shampoo` (Atributo único: `tipoCabello`)
2.  **Controlador (`controller`)**: `ProductoController` expone los endpoints HTTP siguiendo el estándar REST.
3.  **Servicio (`service`)**: `ProductoService` contiene la lógica de negocio y mantiene la colección en memoria con IDs autoincrementables eficientes de tipo `Long`.
4.  **Validaciones (`util`)**: `Validador` asegura la integridad de los datos antes de operar.

---

## 📡 Endpoints de la API (CRUD)

Todos los endpoints usan el prefijo base: `http://localhost:8080/producto`

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| **GET** | `/producto` | Lista todos los productos en el catálogo (visualizable en navegador). |
| **POST** | `/producto` | Registra un nuevo producto (resuelve dinámicamente la subclase). |
| **PUT** | `/producto/{id}` | Actualiza datos comunes y específicos de un producto por su ID. |
| **DELETE** | `/producto/{id}` | Elimina permanentemente un producto del catálogo por su ID. |

---

## 📦 Ejemplos de Estructura JSON

La API utiliza la propiedad `"categoria"` como **discriminador polimórfico** para saber qué subclase instanciar.

### Crear / Modificar una Crema
```json
{
  "categoria": "crema",
  "nombre": "Crema Hidratante Facial",
  "precio": 4500.0,
  "stock": 15,
  "tipoPiel": "Mixta"
}
```

### Crear / Modificar un Perfume
```json
{
  "categoria": "perfume",
  "nombre": "Acqua di Gio",
  "precio": 85000.0,
  "stock": 8,
  "genero": "Masculino"
}
```

---

## 🏃 Cómo Ejecutar el Proyecto

1.  Clona el repositorio o ubícate en la carpeta raíz del proyecto.
2.  Limpia y compila el bytecode ejecutando el Maven Wrapper en tu terminal:
    ```bash
    ./mvnw clean compile
    ```
3.  Inicia la aplicación de Spring Boot:
    ```bash
    ./mvnw spring-boot:run
    ```
4.  Abre tu navegador en `http://localhost:8080/producto` para ver el catálogo en tiempo real.

---

## 📝 Notas de Desarrollo (Solución de Errores)
*   **Lombok vs Java 25**: Se removió el uso de anotaciones de Lombok (`@Data`, `@NoArgsConstructor`) debido a incompatibilidades de generación de bytecode con las versiones experimentales del JDK, reemplazándose por getters, setters y constructores nativos explícitos para garantizar un `BUILD SUCCESS` estable.
