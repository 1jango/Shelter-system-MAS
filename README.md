# ShelterSystem

School course project — an animal shelter management system (Spring Boot + H2 + React/Vite). The GUI implements only the **adoption application processing** use case (review, verification, acceptance/rejection of applications). The backend domain model covers a broader scope (animals, employees, volunteers, contracts), but there is no dedicated UI for it.

## Requirements

- Java 17+
- Maven 3.9+
- Node.js 18+ and npm

## Running

Backend:

```bash
mvn spring-boot:run
```

The app starts on `http://localhost:8080`; the H2 database is created and seeded with sample data automatically on startup.

Frontend:

```bash
cd shelter-frontend
npm install
npm run dev
```

## API

Base prefix: `/api/applications`

| Method | Path                     | Description                          |
|--------|---------------------------|----------------------------------------|
| GET    | `/`                       | List adoption applications             |
| GET    | `/{id}`                   | Application details                    |
| POST   | `/{id}/select`            | Select application for further review  |
| POST   | `/{id}/flag-incomplete`   | Flag application as incomplete         |
| PUT    | `/{id}/client-update`     | Update data submitted by client        |
| POST   | `/{id}/accept`            | Accept application                     |
| POST   | `/{id}/reject`            | Reject application                     |
