# Claude AI Spring Boot Application

This is a Spring Boot application that exposes a REST API with JWT and OAuth2 security, connecting to a PostgreSQL database.

## Prompt

```text
Create the application that exposes a REST API and connects to a PostgreSQL database in the current directory.
The application should have a Person entity with an id and typical fields for each person.
All REST endpoints should be protected with JWT and OAuth2.
The codebase should use Skaffold to deploy on Kubernetes.
Don’t create a plan.
Don’t ask for confirmation, just continue.
```

## Features

- REST API endpoints for Person entities
- JWT and OAuth2 authentication/authorization
- PostgreSQL database integration
- Docker support with docker-compose
- Kubernetes deployment using Skaffold

## Architecture

The application follows a clean architecture approach with:
- Controller layer for REST endpoints
- Service layer for business logic
- Repository layer for database access

## Getting Started

### Prerequisites
- Java 21+
- Maven
- Docker and Docker Compose (for local development)
- Kubernetes cluster (for Skaffold deployment)

### Local Development

1. Clone the repository
2. Build and run with Docker Compose:
   ```bash
   docker-compose up -d
   ```

3. The application will be available at http://localhost:8080

### Kubernetes Deployment with Skaffold

1. Ensure you have a Kubernetes cluster running
2. Deploy using Skaffold:
   ```bash
   skaffold run
   ```

## API Endpoints

- GET /api/persons - Retrieve all persons
- GET /api/persons/{id} - Retrieve a person by ID
- POST /api/persons - Create a new person
- PUT /api/persons/{id} - Update an existing person
- DELETE /api/persons/{id} - Delete a person

## Security

All API endpoints are secured with JWT and OAuth2 authentication.

## Database Schema

The application uses PostgreSQL database with a `persons` table containing:
- id (auto-generated primary key)
- first_name
- last_name
- email
- phone

## Testing

The application includes unit tests for all components and integration tests.

## CI/CD

CircleCI pipeline is configured to:
- Build the application
- Run tests
- Build Docker image
- Test image deployment
