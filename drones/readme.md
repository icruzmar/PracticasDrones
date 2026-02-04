#  Simulador de Drones

Este proyecto es una API REST desarrollada con **Spring Boot** para gestionar y simular el movimiento de una flota de drones sobre un tablero o matriz. El sistema permite procesar rutas, validar movimientos para evitar colisiones y mantener un registro persistente de cada dron.

##  Patrones de Diseño Utilizados

* **Arquitectura en Capas (Layered Architecture):** Separación clara entre `domain` (reglas de negocio), `application` (lógica de simulación/casos de uso) e `infrastructure` (controladores API y persistencia).
* **Data Transfer Object (DTO):** Se utilizan objetos específicos para recibir peticiones y enviar respuestas, evitando exponer las entidades de la base de datos directamente al cliente.
* **Inyección de Dependencias (DI):** Gestión de componentes mediante el contenedor de Spring para reducir el acoplamiento y facilitar el testing.
* **Patrón Facade:** El controlador actúa como un punto de entrada simplificado hacia la lógica compleja de la simulación.
* **Proxy:** Utilizado por Spring para la gestión de transacciones y seguridad.

---

## Cómo ponerlo en marcha

### 1. Requisitos previos
* **Java 21** o superior.
* **Maven 3.6** o superior.

### 2. Instalación y Compilación
Copia y pega los siguientes comandos en tu terminal:

```bash
# Clonar el repositorio
git clone [https://github.com/icruzmar/PracticasDrones.git](https://github.com/icruzmar/PracticasDrones.git)

# Entrar en la carpeta del proyecto
cd PracticasDrones

# Compilar y descargar dependencias
mvn clean install
```

---

### 3. Ejecutar los tests y ver la cobertura

Para lanzar los tests unitarios y de integración, ejecuta:

```bash
mvn test
```

Para generar el informe de cobertura de código con Jacoco:

```bash
mvn verify
```

El informe HTML de cobertura se generará en:

```
target/site/jacoco/index.html
```

Ábrelo en tu navegador para ver el detalle de la cobertura de código.

