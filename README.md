# Spring Boot Security — JWT + Role-Based Authorization

A Spring Boot project demonstrating JWT authentication and role-based access control (RBAC) using Spring Security.

---

## Tech Stack

- Java + Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- Lombok
- MySQL / PostgreSQL

---

## Features

- User registration with BCrypt password encoding
- JWT token generation on login
- Role-based authorization (`ROLE_ADMIN`, `ROLE_STAFF`, `ROLE_GUEST`)
- Protected REST endpoints based on roles
- Custom JWT filter integrated with Spring Security filter chain

---

## Project Structure

```
src/main/java/com/learning/secority/
├── UserController.java       # User registration & authentication
├── RoomController.java       # Protected room endpoints
├── UserEntity.java           # User JPA entity
├── UserRepository.java       # JPA repository
├── UserRequestDTO.java       # Registration request DTO
├── AuthRequest.java          # Login request DTO
├── JwtService.java           # JWT token generation & validation
├── JwtFilter.java            # JWT request filter
└── SecurityConfig.java       # Spring Security configuration
```

---

## API Endpoints

### Auth (Public)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/user/encodePassword` | Register a new user |
| POST | `/user/authenticate` | Login and get JWT token |

### Rooms (Protected)

| Method | Endpoint | Role Required |
|--------|----------|---------------|
| POST | `/rooms` | `ROLE_ADMIN` |
| GET | `/rooms` | `ROLE_ADMIN`, `ROLE_STAFF` |
| GET | `/rooms/{id}` | `ROLE_ADMIN`, `ROLE_STAFF`, `ROLE_GUEST` |

---

## Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL or PostgreSQL running

### Setup

1. Clone the repo
```bash
git clone https://github.com/your-username/spring-boot-security.git
cd spring-boot-security
```

2. Configure `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

jwt.secret=your_secret_key
jwt.expiration=86400000
```

3. Run the project
```bash
mvn spring-boot:run
```

---

## Usage

### Register a User

```
POST /user/encodePassword
Content-Type: application/json

{
  "username": "aman",
  "password": "1234",
  "role": "ROLE_ADMIN"
}
```

Available roles: `ROLE_ADMIN`, `ROLE_STAFF`, `ROLE_GUEST`

### Login & Get Token

```
POST /user/authenticate
Content-Type: application/json

{
  "username": "aman",
  "password": "1234"
}
```

Response: JWT token string

### Access Protected Endpoint

```
GET /rooms
Authorization: Bearer <your_jwt_token>
```

---

## Security Configuration

- `/user/**` — public (no auth required)
- `POST /rooms` — ADMIN only
- `GET /rooms` — ADMIN, STAFF
- `GET /rooms/{id}` — ADMIN, STAFF, GUEST
- All other requests — authenticated

---

## Learning Goals

This project was built to practice:
- Spring Security filter chain
- JWT integration with `UsernamePasswordAuthenticationFilter`
- Role-based access control with `hasRole()` / `hasAnyRole()`
- Password encoding with `BCryptPasswordEncoder`
- DTO pattern for clean controller inputs

---

## Author

**Aman** — B.Tech CSE, learning Java Spring Boot backend development.

[GitHub](https://github.com/your-username) • [LinkedIn](https://linkedin.com/in/your-profile)
