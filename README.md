# 🚀 APIHub – Internal API Ecosystem & Smart Integration Platform

APIHub is a developer-centric Spring Boot application designed to manage, track, mock, and test internal APIs within an organization. It helps teams avoid redundancy, promotes API reuse, provides dependency mapping, and acts as a smart collaboration platform for developers.

---

## Tech Stack

- **Backend**: Java, Spring Boot
- **Database**: MySQL
- **Security**: Spring Security, JWT
- **Testing**: Postman, JUnit
- **Logging**: SLF4J, Logback
- *(Frontend UI will be added later)*

---

## 🎯 Key Features

### 🔐 Authentication & Authorization
- JWT-based login/register system
- Role-based access control: `ADMIN`, `EDITOR`, `VIEWER`
- Fine-grained endpoint security using `@PreAuthorize`

### Project Management
- Create and manage projects (grouping for APIs)
- Ownership mapping (creator is assigned as project owner)

### API Definition & Registry
- Register APIs with metadata:
    - HTTP Method, Path, Tags, Auth Type
    - Request/Response examples
- Search API definitions by keyword, path, or tag
- Assign multiple tags to group APIs by domain or function
- Associate APIs with a project

### API Versioning
- Manage multiple versions of the same API
- Mark versions as `ACTIVE` or `DEPRECATED`
- View historical versions and schemas

### API Testing Engine
- Send test HTTP requests to real or mocked APIs
- Store request and response history
- View logs for every test: status code, payload, time
- Useful for regression testing and monitoring

### API Mocking Module
- Generate and store mock responses per version
- Serve static JSON responses via `/mock-api/{versionId}`
- Test APIs even before real backend is implemented

### Dependency Tracking
- Link APIs to other APIs or shared models
- Track call chain and schema-level dependencies
- View what gets impacted if one API changes

### Access & Analytics (WIP for Week 3)
- Most-used APIs
- Least-used APIs (potential deprecation)
- Trends of API creation and test activity
- Planned: Suggest deprecations based on inactivity

### Logging & Exception Handling
- Centralized exception handling using `@ControllerAdvice`
- Structured logging with request IDs
- Logs for API creation, updates, test executions, etc.

---

## Endpoints (Sample)

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/auth/register` | POST | Register new user |
| `/auth/login` | POST | Authenticate and get JWT |
| `/projects` | CRUD | Create/manage projects |
| `/api-definitions` | CRUD | Register APIs |
| `/api-definitions/{id}/versions` | CRUD | Manage versions |
| `/mock-api/{versionId}` | GET/POST | Serve and create mock responses |
| `/test-requests/{versionId}` | POST | Send and log test request |
| `/api-dependencies` | POST | Declare API dependency |
| `/api-definitions/search` | GET | Search by keyword/tag |

Full API Contract available in `/docs/api-contract.md` and Postman collection.

---

## ⚙️ Getting Started

### ✅ Prerequisites
- Java 17+
- Maven
- MySQL or PostgreSQL

### ▶️ Run Locally

```bash
git clone https://github.com/miswah/apihub.git
cd apihub
