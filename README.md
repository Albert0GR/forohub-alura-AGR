# Foro Hub 👩‍💻
![foro](https://github.com/user-attachments/assets/14f02f77-8b9c-404e-bec8-6055f6890c82)

Foro Hub es una plataforma de foros en línea que permite a los usuarios crear, editar y responder a tópicos. Proporciona autenticación segura mediante JWT y una interfaz de usuario intuitiva.

<p align="center">
    <img src=""/>
</p>


## Características ⚙️

- Creación, edición y eliminación de tópicos.
- Respuesta a tópicos existentes.
- Listado de usuarios y tópicos.
- Autenticación mediante JWT.
- Pruebas de API con Postman o Insomnia

## Tecnologías utilizadas ⚒️

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- JPA (Java Persistence API)
- MySQL (para desarrollo y pruebas)
- Postman (para pruebas de API)
- Swagger (para documentación de API)

  <p align="center">
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java"/>
    <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot"/>
    <img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white" alt="Spring Security"/>
    <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white" alt="JWT"/>
    <img src="https://img.shields.io/badge/JPA-007396?style=for-the-badge&logo=java&logoColor=white" alt="JPA"/>
    <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/>
    <img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white" alt="Postman"/>
    <img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black" alt="Swagger"/>
</p>

## Estructura del proyecto 🖥️

- **Entities**: Clases de entidad que representan las tablas de la base de datos.
- **Dto**: Clases de Data Transfer Object utilizadas para transferir datos entre el cliente y el servidor.
- **Repository**: Interfaces que extienden JpaRepository para realizar operaciones CRUD en las entidades.
- **Service**: Clases de servicio que contienen la lógica de negocio.
- **Controller**: Clases de controlador que manejan las solicitudes HTTP.
- **Security**: Clases relacionadas con la configuración de seguridad y la autenticación.

## Instalación 🚧

### Clona este repositorio:
```
https://github.com/Albert0GR/forohub-alura-AGR.git
```

### Navega al directorio del proyecto:
```
cd forohub-alura-AGR
```
Abre el proyecto en tu IDE favorito (por ejemplo, IntelliJ IDEA o Eclipse) y configura la base de datos MySQL. Asegúrate de tener configurado el archivo application.properties con los detalles de tu base de datos.
```
spring.application.name=forohub
spring.datasource.url=jdbc:mysql://${DB_HOST}/db_foro_hub
spring.datasource.username=${DB_USERNAMEMYSQL}
spring.datasource.password=${DB_PASSWORD}


spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.error.include-stacktrace=never

api.security.secret=${API_SECRET}

```
### Configuración
- Base de datos: MySQL 📈

<p align="center">
    <img src="https://github.com/user-attachments/assets/06f55f2f-61ff-490b-a4c0-af3a82288b4b"/>
</p>


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

### Ejemplos de solicitudes 📋

- Autenticación 🔐

Solicitud:
```
POST http://localhost:8080/login
```
Body:
``` json
{
	"nombre":"String",
	"contrasena":"String"
}
```
Respuesta:
``` json
{
    "token": "jwt_token"
}
```
- Crear un usuario 👤

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
``` json
{
  "nombre": "USER1",
  "correoElectronico": "user@example.com",
  "contrasena": "password"
}

```

Respuesta:
``` json
{
	"id": 20,
	"nombre": "USER1",
	"correoElectronico": "user@example.com",
	"contrasena": "password",
	"perfiles": null,
	"enabled": true
}
```

- Crear un curso 📖
  
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
``` json
{
  "nombre": "Boot spring 3",
  "categoria": "Back End"
}

```
Respuesta:
``` json
{
	"id": 10,
	"nombre": "boot spring 3",
	"categoria": "Back End"
}
```

- Crear un tópico 📝
  
  Una vez creados autor y curso ya se pueden agregar topicos proporcionando el idAutor y el idCurso

Solicitud:
```
POST http://localhost:8080/topicos
```
Headers:
```
Authorization: Bearer jwt_token_generado
Content-Type: application/json
```
Body:
``` json
{
  "titulo": "Nuevo Topico1",
  "mensaje": "Estew es el mensaje del nuevo topico1",
  "status": "Abierto",
  "autor": {
    "id": 1
  },
  "curso": {
    "id": 1
  }
}

```

Respuesta:
``` json
{
	"id": 72,
	"titulo": "Nuevo Topico1",
	"mensaje": "Estew es el mensaje del nuevo topico1",
	"fechaCreacion": "2024-07-15T09:42:19.461864",
	"status": "Abierto",
	"autorNombre": "John Doe",
	"cursoNombre": "Java Programming"
}

```

- Listar tópicos 📝

Solicitud:
```
GET http://localhost:8080/topicos
```
Headers:
```
Authorization: Bearer jwt_token_generado
Content-Type: application/json
```

Respuesta:
``` json
{
	"totalPages": 7,
	"totalElements": 62,
	"size": 10,
	"content": [
		{
			"id": 5,
			"titulo": "nuevo",
			"mensaje": "nuevo mensaje actualizafo domingo.",
			"fechaCreacion": "2024-07-11T15:19:51",
			"status": "Abierto",
			"autorNombre": "John Doe",
			"cursoNombre": "Java Programming"
		},
		{
			"id": 6,
			"titulo": "nuevo",
			"mensaje": "nuevo mensaje.",
			"fechaCreacion": "2024-07-11T15:22:43",
			"status": "Abierto",
			"autorNombre": "John Doe",
			"cursoNombre": "Java Programming"
		},
		{
			"id": 7,
			"titulo": "nuevo",
			"mensaje": "nuevo mensaje actualizafo domingo.",
			"fechaCreacion": "2024-07-11T15:22:51",
			"status": "Abierto",
			"autorNombre": "John Doe",
			"cursoNombre": "Java Programming"
		},
		{
			"id": 9,
			"titulo": "Nuevo Tópico 78",
			"mensaje": "Este es el mensaje del nuevo tópico.",
			"fechaCreacion": "2024-07-11T15:26:53",
			"status": "Abierto",
			"autorNombre": "John Doe",
			"cursoNombre": "Java Programming"
		},
		{
			"id": 11,
			"titulo": "Nuevo Tópico 788",
			"mensaje": "Este es el mensaje del nuevo tópico.",
			"fechaCreacion": "2024-07-11T15:27:42",
			"status": "Abierto",
			"autorNombre": "John Doe",
			"cursoNombre": "Java Programming"
		},
		{
			"id": 12,
			"titulo": "Nuevo Tópico 788",
			"mensaje": "Este es el mensaje del nuevo tópico.",
			"fechaCreacion": "2024-07-11T17:20:03",
			"status": "Abierto",
			"autorNombre": "John Doe",
			"cursoNombre": "Java Programming"
		},
		{
			"id": 13,
			"titulo": "Nuevo Tópico 788",
			"mensaje": "Este es el mensaje del nuevo tópico.",
			"fechaCreacion": "2024-07-11T17:20:04",
			"status": "Abierto",
			"autorNombre": "John Doe",
			"cursoNombre": "Java Programming"
		},
		{
			"id": 14,
			"titulo": "Nuevo Tópico 788",
			"mensaje": "Este es el mensaje del nuevo tópico.",
			"fechaCreacion": "2024-07-11T17:20:06",
			"status": "Abierto",
			"autorNombre": "John Doe",
			"cursoNombre": "Java Programming"
		},
		{
			"id": 17,
			"titulo": "Nuevo Tópico",
			"mensaje": "Este es el mensaje del nuevo tópico.",
			"fechaCreacion": "2024-07-11T20:23:45",
			"status": "Abierto",
			"autorNombre": "John Doe",
			"cursoNombre": "Java Programming"
		},
		{
			"id": 18,
			"titulo": "Nuevo Tópico",
			"mensaje": "Este es el mensaje del nuevo tópico.",
			"fechaCreacion": "2024-07-11T20:23:58",
			"status": "Abierto",
			"autorNombre": "John Doe",
			"cursoNombre": "Java Programming"
		}
	],
	"number": 0,
	"sort": {
		"empty": false,
		"unsorted": false,
		"sorted": true
	},
	"numberOfElements": 10,
	"pageable": {
		"pageNumber": 0,
		"pageSize": 10,
		"sort": {
			"empty": false,
			"unsorted": false,
			"sorted": true
		},
		"offset": 0,
		"paged": true,
		"unpaged": false
	},
	"first": true,
	"last": false,
	"empty": false
}

```
  
### Swagger 📊
Swagger está configurado para generar documentación de la API automáticamente. Puedes acceder a la interfaz de Swagger en la siguiente URL cuando el servidor esté en funcionamiento:

```
http://localhost:8080/swagger-ui/index.html
```

## Documentacion swangger ⌨️

![image](https://github.com/user-attachments/assets/f86b32e9-6916-47cf-9a78-7628d058e2e7)


## Video de YouTube 

[![Video de YouTube](https://github.com/user-attachments/assets/00b0fc2e-1464-43b5-8199-77d3fb035d41)](https://www.youtube.com/watch?v=ZEMRTdg2U3g)


## Licencia 🚀
