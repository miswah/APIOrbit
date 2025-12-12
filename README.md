
# APIOrbit  
Internal API Ecosystem & Smart Integration Platform

APIOrbit is a Spring Boot–based platform designed to manage, document, version, and mock internal APIs within an organization. It provides a centralized system for API lifecycle management, version control, dependency tracking, audit logging, and mock response simulation. This enables teams to standardize API development, streamline integration work, and improve backend reliability.

---

## Features

### 1. API Registry and Management
- Create, update, delete, and activate/deactivate internal API definitions.
- Store request and response JSON schemas.
- Enforce schema validation using custom validators.
- Maintain consistent URL and method structures across the system.

### 2. Version Control for APIs
- Automatic versioning for every change.
- View version history and change logs.
- Maintain backward compatibility for integrations.

### 3. Mock API System
- Create mock responses for any API.
- Enable or disable mock endpoints.
- Validate mock request/response formats.
- Useful for testing, frontend development, and integration simulations.

### 4. Tag Management and Categorization
- Create reusable tags.
- Assign tags to APIs for easier search and organization.
- Filter APIs using tags, methods, and statuses.

### 5. Audit Logging with Custom AOP
- Track all changes made to APIs and mock definitions.
- Log actions, modules, targets, and trace IDs.
- Activity logs are automatically generated through the `@ActivityLog` annotation.

### 6. Request Trace ID Propagation
- Every request generates a unique trace ID.
- Trace ID is logged in the audit table and application logs.
- Enables fast debugging and log correlation.

### 7. Custom Validation Framework
- JSON schema validation using `@ValidJson`.
- URL path validation using `@ValidUrlPath`.
- Ensures reliability and prevents malformed API entries.

### 8. Centralized Logging System
- Structured logs stored in `/logs`.
- Rolling file appenders for long-term log storage.
- Custom logger with class, method, line number, and trace ID.

### 9. Role-Based Authentication and Authorization
- Admin and standard user profiles.
- Restricted API operations based on roles.
- Secure access using JWT authentication (optional for expansion).

---

## Architecture Overview

APIOrbit follows a modular architecture divided into:

- Controller Layer  
- Service Layer  
- Repository Layer  
- AOP Modules (Logging, Activity)  
- Validator Modules  
- Security Layer  
- Audit and Trace Modules  

The system is designed with scalability and maintainability in mind, ensuring extensibility for future enhancements.

---

## Technologies Used

- Java  
- Spring Boot  
- Spring Data JPA  
- Spring Security  
- Hibernate  
- MySQL or PostgreSQL  
- SLF4J + Logback  
- Lombok  
- JWT (optional enhancements)  
- Maven  

---

## Database Schema

Key entities include:

- `users`
- `api_definition`
- `api_version`
- `mock_api`
- `tag`
- `api_tag_mapping`
- `audit_log`

Refer to ERD and SQL schema in the project documentation for full details.

---

## Installation and Setup

### Prerequisites
- Java 17+
- Maven 3+
- MySQL or PostgreSQL

### Steps
1. Clone the repository:
```

git clone [https://github.com/miswah/APIOrbit](https://github.com/miswah/APIOrbit)
cd APIOrbit

```
2. Configure database in `application.properties` or `application.yml`.
3. Install dependencies:
```

mvn clean install

```
4. Run the application:
```

mvn spring-boot:run

```
5. The backend will start on:
```

[http://localhost:8080](http://localhost:8080)

```

---

## Project Structure

```

src/
├─ main/
│   ├─ java/com.apiorbit/
│   │    ├─ controller/
│   │    ├─ service/
│   │    ├─ repository/
│   │    ├─ model/
│   │    ├─ security/
│   │    ├─ validators/
│   │    ├─ aop/
│   │    └─ utils/
│   └─ resources/
│        ├─ application.properties
│        └─ logback-spring.xml
└─ test/

```

---

## API Documentation

API endpoints include:

- API Definitions Management  
- API Version Management  
- Mock API Endpoints  
- Tag Management  
- Audit Log Retrieval  
- Authentication (if enabled)  

A full Postman collection or OpenAPI spec can be added later.

---

## Logging and Audit Mechanisms

- Custom `@Loggable` annotation for method-level logging.
- Custom `@ActivityLog` annotation for audit entries.
- Trace ID added to every request and log entry.
- Logs stored in a rolling pattern under `/logs`.

---

## Future Enhancements

- Graph-based visualization for API dependencies.
- OpenAPI/Swagger documentation generator.
- API testing suite with integrated test runner.
- User dashboard with analytics and usage insights.
- API export/import capability.
- Service-to-service mock routing for microservices testing.

---

## Contribution Guidelines

Contributions are welcome.  
Feel free to open issues, submit feature requests, or create pull requests.

---

## License

This project is open for academic and experimental use.  
You may fork and extend it as needed.
