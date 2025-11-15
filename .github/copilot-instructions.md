# Midprove Codebase - AI Coding Agent Instructions

## Project Overview

**Midprove** is a Spring Boot 3.5.7 REST API application built with:
- **Java 21** - Target language and runtime
- **Spring Boot Starter Web** - REST API framework
- **Spring Data JPA** - ORM and database abstraction
- **MySQL** - Relational database with `mysql-connector-j`
- **Lombok** - Reduces boilerplate code generation
- **Maven** - Build tool (uses wrapper: `mvnw.cmd` on Windows)

Package structure: `com.mindprove.midprove.*`

## Critical Architecture & Patterns

### Package Organization
- **Root package**: `com.mindprove.midprove` - Application entry point (`MidproveApplication.java`)
- **Subpackages**: Controllers live in dedicated packages (e.g., `usercontroller` for user-related REST endpoints)
- **Naming convention**: Package names are lowercase; controller classes may have inconsistent naming (see `userContoller` - note the lowercase 't')

### Spring Boot Entry Point
`MidproveApplication.java` uses `@SpringBootApplication` and `SpringApplication.run()`. This is standard Spring Boot convention - **do not modify this structure** unless adding component scanning annotations.

### Controllers Pattern
Controllers are structured by domain (e.g., `usercontroller` package). Currently `userContoller.java` is empty but should be enhanced to handle user REST endpoints. Follow Spring conventions:
```java
@RestController
@RequestMapping("/api/users")  // Suggested pattern for user endpoints
public class userController {
    // Implementation here
}
```

## Development Workflows

### Build & Run
```powershell
# Build the project
.\mvnw.cmd clean package

# Run the application
.\mvnw.cmd spring-boot:run

# Run tests only
.\mvnw.cmd test
```

### Database Configuration
`application.properties` is minimal. Database credentials should be added for MySQL connectivity:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/midprove
spring.datasource.username=root
spring.datasource.password=<password>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

## Code Generation & Conventions

### Lombok Integration
The project uses **Lombok** for reducing boilerplate:
- Use `@Data`, `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor` on entity/DTO classes
- Maven compiler configuration already includes Lombok's annotation processor path (`pom.xml`)
- When creating entity classes, always apply appropriate Lombok annotations

### Entity & DTO Pattern
Expected structure in `com.mindprove.midprove`:
- `models/` or `entities/` - JPA entity classes with `@Entity` and Lombok annotations
- `dto/` - Data transfer objects for API requests/responses
- `repositories/` - Spring Data JPA repository interfaces extending `JpaRepository<Entity, ID>`
- `services/` - Business logic layer (Service classes)
- `usercontroller/` and other controllers - REST endpoints with `@RestController`

### Naming Conventions
- Classes: PascalCase (e.g., `UserController`, `UserEntity`)
- **NOTE**: Current codebase has `userContoller` (lowercase 'C') - suggest standardizing to PascalCase
- Methods: camelCase
- Constants: UPPER_SNAKE_CASE

## Testing Strategy

Tests are located in `src/test/java/com/mindprove/midprove/`.

- Use `MockMvc` for controller tests (already available via `spring-boot-starter-test`)
- Unit tests follow naming pattern: `<ClassName>Tests.java` (see `MidproveApplicationTests.java`)
- Run tests with: `.\mvnw.cmd test`

## Integration Points & Dependencies

### Spring Boot Auto-Configuration
The `@SpringBootApplication` annotation enables:
- Component scanning in `com.mindprove.midprove` package and subpackages
- Auto-configuration of data sources, JPA, and web MVC
- Property file loading from `application.properties`

### MySQL & JPA
- Entities must be annotated with `@Entity` in scanned packages
- Use `@Repository` for repository interfaces or extend `JpaRepository`
- Connection pooling is auto-configured by Spring Boot

### REST API Response Pattern
When creating controllers, follow standard REST conventions:
```java
@GetMapping("/{id}")
public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
    // Implementation
    return ResponseEntity.ok(user);
}
```

## Key Files Reference

| File | Purpose |
|------|---------|
| `pom.xml` | Maven dependencies and build config; contains Lombok processor path |
| `MidproveApplication.java` | Spring Boot entry point (`@SpringBootApplication`) |
| `usercontroller/userContoller.java` | User endpoints (currently empty, ready for implementation) |
| `application.properties` | Spring Boot configuration (minimal - needs database setup) |

## AI Agent Guidance

1. **Before adding new features**: Check if JPA entities, repositories, and services exist; create them if missing
2. **Controllers first**: New functionality should start with REST endpoint definition, then implement service layer
3. **Lombok usage**: Always use Lombok annotations on entities/DTOs to match project conventions
4. **Database queries**: Use Spring Data JPA query methods (`findById`, `findAll`, etc.) rather than native SQL
5. **Property-driven configuration**: Add configuration to `application.properties` rather than hardcoding values
