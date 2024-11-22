# Spring Boot Product Management API

A RESTful API for managing computer hardware products built with Spring Boot and H2 Database.

## Features

- CRUD operations for computer hardware products
- In-memory H2 database for data persistence
- RESTful API endpoints
- Input validation
- Global exception handling
- CORS support

## Tech Stack

- Java 17
- Spring Boot 3.3.4
- Spring Data JPA
- H2 Database
- Maven

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the application:
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

### H2 Database Console

Access the H2 Console at `http://localhost:8080/h2-console` with these credentials:
- JDBC URL: `jdbc:h2:mem:productdb`
- Username: `sa`
- Password: (leave empty)

## API Endpoints

### Products

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/products` | Get all products |
| GET | `/products/{id}` | Get product by ID |
| POST | `/products` | Create new product |
| PUT | `/products/{id}` | Update product |
| DELETE | `/products/{id}` | Delete product |

### Request/Response Examples

#### Create Product
```json
POST /products
{
    "name": "Gaming Monitor",
    "category": "Visual",
    "price": 699.99,
    "stock": 10
}
```

#### Get All Products
```json
GET /products
Response:
[
    {
        "id": "123e4567-e89b-12d3-a456-426614174000",
        "name": "Gaming Monitor",
        "category": "Visual",
        "price": 699.99,
        "stock": 10
    }
]
```

## Data Validation

The API validates:
- Product name (cannot be empty)
- Price (must be greater than 0)
- Stock (cannot be negative)

## Error Handling

The API returns appropriate HTTP status codes and error messages:
- 200: Success
- 400: Bad Request (validation errors)
- 404: Not Found
- 500: Internal Server Error

## Development

### Project Structure
```
src/main/java/com/spring/products/
├── config/
│   └── WebConfig.java
├── controller/
│   └── ProductController.java
├── entity/
│   ├── BaseEntity.java
│   └── ProductEntity.java
├── exception/
│   └── GlobalExceptionHandler.java
├── repository/
│   └── ProductRepository.java
├── service/
│   └── ProductService.java
└── DemoApplication.java
```

### Key Components

- `BaseEntity`: Abstract base class for all entities
- `ProductEntity`: JPA entity for products
- `ProductService`: Business logic implementation
- `ProductController`: REST endpoint definitions
- `GlobalExceptionHandler`: Centralized error handling
- `WebConfig`: CORS and web configuration