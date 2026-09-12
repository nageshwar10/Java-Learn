
# Student CRUD API

Spring Boot REST API for creating, reading, updating, and deleting students.

## Technology Stack

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- PostgreSQL
- Lombok
- ModelMapper
- Maven
- Docker Compose

## Maven Dependencies

| Dependency | Purpose |
|---|---|
| `spring-boot-starter-webmvc` | Creates REST APIs and handles HTTP requests |
| `spring-boot-starter-validation` | Validates request data using annotations such as `@NotBlank` and `@Email` |
| `springdoc-openapi-starter-webmvc-ui` | Generates Swagger/OpenAPI documentation |
| `spring-boot-starter-data-jpa` | Provides repositories and database persistence |
| `postgresql` | PostgreSQL JDBC database driver |
| `lombok` | Generates getters, setters, constructors, and required-argument constructors |
| `spring-boot-starter-webmvc-test` | Testing support for MVC controllers |
| `spring-boot-starter-data-jpa-test` | Testing support for JPA repositories |
| `modelmapper` | Converts entities to DTOs and DTOs to entities |

## Application Architecture

```text
HTTP Request
    ↓
StudentController
    ↓
StudentService
    ↓
StudentServiceImpl
    ↓
StudentRepository
    ↓
PostgreSQL
```

- The controller receives HTTP requests.
- The service contains business logic.
- The repository communicates with the database.
- The entity represents the database table.
- DTOs represent request and response data.

## Database Setup

Start PostgreSQL with Docker:

```bash
docker compose up -d
```

The database configuration is:

```text
Database: learn
Username: learn_user
Password: learn_password
Host: localhost
Port: 5432
```

Hibernate creates or updates the `student` table automatically because:

```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: update
```

For production, use migrations such as Flyway or Liquibase instead of `ddl-auto: update`.

## Run the Application

From the project directory:

```bash
./mvnw spring-boot:run
```

On macOS, if the Maven wrapper is not executable:

```bash
chmod +x mvnw
./mvnw spring-boot:run
```

The API runs at:

```text
http://localhost:8080/api
```

Run tests:

```bash
./mvnw test
```

Build the project:

```bash
./mvnw clean package
```

## API Endpoints

### Get all students

```http
GET http://localhost:8080/api/students
```

Example response:

```json
[
  {
    "id": 1,
    "name": "John Doe",
    "age": 21,
    "email": "john@example.com"
  }
]
```

### Get one student

```http
GET http://localhost:8080/api/students/1
```

### Create a student

```http
POST http://localhost:8080/api/students
Content-Type: application/json
```

Request body:

```json
{
  "name": "John Doe",
  "age": 21,
  "email": "john@example.com"
}
```

Returns HTTP `201 Created`.

### Replace a student

```http
PUT http://localhost:8080/api/students/put/1
Content-Type: application/json
```

Request body:

```json
{
  "name": "John Smith",
  "age": 22,
  "email": "john.smith@example.com"
}
```

All editable fields should be supplied.

### Partially update a student

```http
PATCH http://localhost:8080/api/students/patch/1
Content-Type: application/json
```

Request body:

```json
{
  "age": 23
}
```

Supported fields:

```json
{
  "name": "Updated Name",
  "age": 25,
  "email": "updated@example.com"
}
```

### Delete a student

```http
DELETE http://localhost:8080/api/students/del/1
```

Returns HTTP `204 No Content`.

## Validation Rules

- Name is required.
- Name must contain between 2 and 50 characters.
- Age is required.
- Age must be zero or greater.
- Email is required.
- Email must use a valid email format.

Example invalid request:

```json
{
  "name": "",
  "age": -2,
  "email": "invalid-email"
}
```

## Swagger UI

After starting the application, open:

```text
http://localhost:8080/api/swagger-ui/index.html
```

OpenAPI JSON:

```text
http://localhost:8080/api/v3/api-docs
```

## SQL Alternative

If PostgreSQL tables must be created manually:

```sql
CREATE TABLE student (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INTEGER NOT NULL CHECK (age >= 0),
    email VARCHAR(255) NOT NULL
);
```

However, this project currently allows Hibernate to create the table automatically.

## Important Notes

- `IllegalArgumentException` currently handles missing students. A production application should use a global exception handler and return consistent `404` responses.
- Add a unique constraint to `email` if duplicate email addresses are not allowed.
- Avoid logging SQL and database passwords in production.