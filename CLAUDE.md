# Project Context

This is a Spring Boot inventory tracker application for small businesses. 

## Project Structure
- **Language**: Java 17
- **Framework**: Spring Boot 3.5.4
- **Database**: PostgreSQL (production), H2 (testing)
- **Build Tool**: Maven
- **Package**: `com.simple_inventory_tracker.project`

## Key Components
- **Entities**: Product, Stock, Supplier
- **Controllers**: ProductController, StockController, SupplierController, NotificationController
- **Services**: ProductService, StockService, SupplierService, NotificationService
- **Repositories**: ProductRepository, StockRepository, SupplierRepository
- **DTOs**: ProductDto, StockDto
- **Exceptions**: Custom exceptions with GlobalExceptionHandler

## Development Commands

### Build & Test
```bash
./mvnw clean compile
./mvnw test
./mvnw spring-boot:run
```

### Database
- Start PostgreSQL: `sudo service postgresql start`
- Application uses Spring Data JPA with auto-configuration

### Branches
- **develop**: For development with build & test jobs
- **release**: Full CI/CD pipeline with Docker build/push and deployment

## Testing
- Unit tests in `src/test/java/`
- Uses JUnit 5, MockMvc, Mockito
- Test classes: ProductControllerTest, StockControllerTest, StockServiceImplTest

## Key Features
- Product management with stock tracking
- Supplier management
- Low stock notifications
- REST API endpoints
- Exception handling with custom error messages