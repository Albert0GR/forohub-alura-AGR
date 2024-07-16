# Foro Hub 👩‍💻

Foro Hub es 

<p align="center">
    <img src=""/>
</p>


## Características ⚙️

- 2
- Creación, edición y eliminación de tópicos.
- Respuesta a tópicos existentes.
- Listado de usuarios y tópicos.
- Autenticación mediante JWT.

## Tecnologías utilizadas ⚒️

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- JPA (Java Persistence API)
- MySQL (para desarrollo y pruebas)
- Postman (para pruebas de API)
- Swagger (para documentación de API)

## Estructura del proyecto 🖥️

- **Entities**: Clases de entidad que representan las tablas de la base de datos.
- **Dto**: Clases de Data Transfer Object utilizadas para transferir datos entre el cliente y el servidor.
- **Repository**: Interfaces que extienden JpaRepository para realizar operaciones CRUD en las entidades.
- **Service**: Clases de servicio que contienen la lógica de negocio.
- **Controller**: Clases de controlador que manejan las solicitudes HTTP.
- **Security**: Clases relacionadas con la configuración de seguridad y la autenticación.

## Instalación 🚧

Clona este repositorio:
```

```

Navega al directorio del proyecto:
```
1
```
- Abre el proyecto en tu IDE favorito (por ejemplo, IntelliJ IDEA o Eclipse).
- Configuración
- Base de datos: MySQL 📈

<p align="center">
    <img src=""/>
</p>


### Swagger 📊
Swagger está configurado para generar documentación de la API automáticamente. 
Puedes acceder a la interfaz de Swagger en la siguiente URL cuando el servidor esté en funcionamiento:
```
http://localhost:8080/swagger-ui/index.html
```
IMG

Ejecución
Para ejecutar la aplicación, utiliza el siguiente comando en la raíz del proyecto:
```
mvn spring-boot:run
```
La aplicación estará disponible en `http://localhost:8080`.

Endpoints principales:
- `/login`: Endpoint para autenticación de usuarios. Envía una solicitud POST con un JSON que contiene `username` y `password`.
- `/usuarios`: Endpoint para listar usuarios. Requiere autenticación mediante un token JWT.
- `/topicos`: Endpoint para manejar la creación, actualización y eliminación de tópicos.
- `/cursos`: Endpoint para manejar la creación, actualización y eliminación de cursos.

### Ejemplos de solicitudes 📑

- Autenticación 🔐

Solicitud:
```
POST http://localhost:8080/login
```
Body:
```
{
	"nombre":"String",
	"contrasena":"String"
}
```
Respuesta:
```
{
    "token": "jwt_token"
}
```
- Crear un usuario
  Es necesario tener un autor/usuario creado previo a registrar un nuevo topico
Solicitud:
```
POST http://localhost:8080/usuarios
```
Headers:
```
Authorization: Bearer jwt_token_generado
Content-Type: application/json
```
Body:
```
{
  "nombre": "USER1",
  "correoElectronico": "user@example.com",
  "contrasena": "password"
}

```

- Crear un curso
  Es necesario tener un curso creado previo a registrar un nuevo topico
Solicitud:
```
POST http://localhost:8080/curso
```
Headers:
```
Authorization: Bearer jwt_token_generado
Content-Type: application/json
```
Body:
```
{
  "nombre": "Boot spring 3",
  "categoria": "Back End"
}

```

- Crear un tópico 📝
  Una vez creados autor y curso ya se pueden agregar topicos proporcionando el idAutor y el idCurso

Solicitud:
```
GET http://localhost:8080/topicos
```
Headers:
```
Authorization: Bearer jwt_token_generado
Content-Type: application/json
```
Body:
```
{
  "titulo": "Nuevo Topico",
  "mensaje": "Estew es el mensaje del nuevo topico",
  "status": "Abierto",
  "autor": {
    "id": 1
  },
  "curso": {
    "id": 1
  }
}

```
## Documentacion swangger ⌨️

![image](https://github.com/user-attachments/assets/f86b32e9-6916-47cf-9a78-7628d058e2e7)



## Licencia 🚀
