# Vertease Backend

A concise, production-ready backend for Vertease — a Spring Boot service that manages users, examinations, ML-based diagnosis predictions (Weka), and verification workflows.

## About

Vertease Backend implements the server-side for the Vertease project, a prototype platform focused on vertigo diagnosis. The service exposes REST APIs to manage users and roles (PATIENT, DOCTOR, ADMIN), record and retrieve examinations and user entries, run Weka-based ML predictions, and support verification workflows where clinicians can confirm or correct model outputs. This repository is intended for development, research, and integration with client applications; it is not a certified medical device and should not be used for clinical decision‑making without validation and regulatory approval.

---

## Table of contents

- [Key features](#key-features)
- [Tech stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Configuration & environment](#configuration--environment)
- [Run the application](#run-the-application)
- [API and authentication](#api-and-authentication)
- [Machine learning](#machine-learning)
- [Development notes & good practices](#development-notes--good-practices)
- [Testing](#testing)
- [Contributing](#contributing)
- [Contact](#contact)
- [License](#license)

---

## Key features ✅

- JWT-based authentication (register / login) and role-based access control (PATIENT / DOCTOR / ADMIN)
- CRUD for examinations and user entries
- ML prediction pipeline using Weka (model loading, prediction, confidence score)
- Admin and verification workflows for ML analyses
- OpenAPI / Swagger UI for interactive API docs

## Tech stack 🔧

- Java 17
- Spring Boot (parent: 4.0.1)
- Spring Security (JWT)
- Spring Data JPA (MySQL)
- Weka (ML)
- SpringDoc OpenAPI (Swagger UI)
- Lombok for boilerplate reduction
- Maven build

## Prerequisites ⚙️

- Java 17 JDK
- Maven 3.6+ (or use the bundled `./mvnw`)
- MySQL (or another supported RDBMS with proper configuration)

## Configuration & environment 💡

Important environment variables and properties (example names used by the project):

- DB_URL (used as `spring.datasource.url`)
- DB_USERNAME (used as `spring.datasource.username`)
- DB_PASSWORD (used as `spring.datasource.password`)

Application properties file uses these variables by default (`src/main/resources/application.properties`).

Security notes:

- The code currently contains a static JWT secret in `JwtUtils`. Replace this with a secure secret injected from environment variables or a secrets manager before production use.
- `spring.jpa.hibernate.ddl-auto` is set to `create-drop` — this is suitable for development only. Use `update` or managed migrations (Flyway / Liquibase) in production.

## Run the application ▶️

1. Export required env vars (example):

```bash
export DB_URL="jdbc:mysql://localhost:3306/vertease"
export DB_USERNAME="root"
export DB_PASSWORD="password"
```

2. Build and run with Maven:

```bash
./mvnw clean package
java -jar target/vertease-0.0.1-SNAPSHOT.jar
```

or run directly during development:

```bash
./mvnw spring-boot:run
```

Default server port: `8080` (configurable in `application.properties`).

## API and authentication 🔒

- API base paths are under `/api/*`.
- Public endpoints: `/api/auth/register` and `/api/auth/login` (receive JWT access token).
- Swagger UI: `/swagger-ui/index.html`
- OpenAPI JSON: `/v3/api-docs`

Example: register and call a protected endpoint (simplified)

```bash
# Register
curl -X POST -H "Content-Type: application/json" -d '{"name":"Test","email":"a@b.com","password":"pass","role":"PATIENT"}' http://localhost:8080/api/auth/register

# Login to get token
curl -X POST -H "Content-Type: application/json" -d '{"email":"a@b.com","password":"pass"}' http://localhost:8080/api/auth/login

# Use token
curl -H "Authorization: Bearer <access_token>" http://localhost:8080/api/profile/me
```

### Notable endpoints

- Auth: `/api/auth/register`, `/api/auth/login`
- Examinations: `/api/examinations` (create, list, get)
- ML Analysis: `/api/ml-analysis` (run, fetch), Admin ML endpoints under `/api/admin/ml`
- Verifications: `/api/verifications` (mark analysis as correct/incorrect)
- Role-specific routes: `/api/patient`, `/api/doctor`, `/api/users`, `/api/profile`

Refer to the OpenAPI docs for full request/response schema and validation rules.

## Machine learning ⚗️

- Model inference uses Weka. The `MLPredictionService` takes mapped inputs and returns:
  - `predictedDiagnosis`
  - `confidenceScore`
  - `modelVersion`
- Model loading and training utilities reside under `src/main/java/com/vertease/ml/data`.
- The repository uses an included model loader; confirm model file location and update `MLModelLoader` if you change storage strategy.

## Development notes & good practices 📝

- Replace the hard-coded JWT secret with an environment-based secret or secrets manager.
- Switch `hibernate.ddl-auto` to a safe migration strategy for non-development environments.
- Add integration tests for ML components that mock model outputs to avoid non-determinism.
- Consider extraction of ML model artifacts and versioning (S3/Blob storage or similar) for reproducible predictions.

## Testing ✅

Run unit and integration tests using:

```bash
./mvnw test
```

## Contributing 🤝

- Fork the repo, create a feature branch, add tests, and open a PR with a clear description.
- Follow existing code patterns (DTOs, services, repositories) and keep controller methods focused and small.

## Contact 📬

- Project maintainer (from OpenAPI metadata): Barash Sharma — barash1311@gmail.com

## License ⚖️

Add a LICENSE file to clearly state project licensing (MIT, Apache 2.0, etc.).

---

If you want, I can:
- Generate a `README.md` (same content) instead of `readmn.md`,
- Add a short example project diagram or quickstart script,
- Or include a checklist for production hardening (secrets, DB migrations, observability).

Tell me which of the above you'd like me to add next.