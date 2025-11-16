# Spring Boot + Spring Security 6 – Role-Based API Security (ADMIN / USER)

A production-ready demo of Spring Security with **Basic Authentication**, **BCrypt password encoding**, and **role-based access control** using **MySQL** database.

## Features Implemented
- Zero XML – Pure Java Configuration (Spring Boot 3 + Security 6)
- Two roles: `ADMIN` and `USER`
- Secure endpoints:
  - `/admin/**` → Only `ADMIN`
  - `/user/**`  → `USER` or `ADMIN`
- BCryptPasswordEncoder for password hashing
- MySQL database integration (JPA + auto table creation)
- Basic Auth enabled (perfect for REST APIs)
- Clean separation: config, controller, entity, service, repository

## Test Users (Auto-seeded on startup)
| Username | Password   | Role  | Access                              |
|----------|------------|-------|-------------------------------------|
| `admin`  | `adminpass`| ADMIN | `/admin/**` + `/user/**`            |
| `john`   | `johnpass` | USER  | Only `/user/**`                     |

## Quick Start
1. Install MySQL, create DB: `CREATE DATABASE security_db;`
2. Update `application.properties` with your MySQL creds.
```bash
mvn spring-boot:run

## Database Setup (MySQL)
1. **Create DB**: Run in MySQL CLI or Workbench:
   ```sql
   CREATE DATABASE security_db;

## Test with cURL / Postman

```bash
# USER access - Success (200 OK)
curl -u john:johnpass http://localhost:8085/user/profile

# USER tries admin endpoint - 403 Forbidden
curl -u john:johnpass http://localhost:8085/admin/dashboard

# ADMIN access - Success (200 OK)
curl -u admin:adminpass http://localhost:8085/admin/dashboard

# ADMIN can also access user endpoints
curl -u admin:adminpass http://localhost:8085/user/profile

## Endpoints

| Method | URL                    | Allowed Roles       | Response Example                          |
|--------|------------------------|---------------------|-------------------------------------------|
| GET    | `/user/profile`        | USER, ADMIN         | "Your profile: This is secure user profile." |
| GET    | `/user/info`           | USER, ADMIN         | "User info: Basic details here."          |
| GET    | `/admin/dashboard`     | ADMIN only          | "Welcome Admin! This is secure admin dashboard." |
| GET    | `/admin/users`         | ADMIN only          | "List of all users: admin, john."         |

## Tech Stack
- Spring Boot 3.2+
- Spring Security 6
- Spring Data JPA + MySQL
- Maven
- Java 21+
- BCryptPasswordEncoder (password hashing)
