# Dynamic Application Properties Changes via DB

This project demonstrates how to dynamically update application properties from a database in a Spring Boot application using `@Value`, `@RefreshScope`, and the Spring Cloud `actuator/refresh` endpoint. The property changes in the database can be reflected in the application without restarting the application.

## Features

- Fetches dynamic application properties from a database.
- Allows properties to be updated at runtime via REST API.
- Automatically refreshes property values using the `/actuator/refresh` endpoint.
- Provides API endpoints to get and update properties dynamically.

## Prerequisites

- Java 17+
- Spring Boot 3.x
- PostgreSQL/MySQL (or any other relational database)
- Maven or Gradle

## Technologies Used

- Spring Boot
- Spring Cloud
- Spring Actuator
- Spring Data JPA
- REST API
- PostgreSQL/MySQL (or any other relational database)

## Project Setup

### Clone the repository

```bash
git clone https://github.com/yourusername/dynamic-properties-db-update.git
cd dynamic-properties-db-update
```

### Database Configuration

1. Create a database (e.g., `dynamic_properties_db`).
2. Update the `application.properties` file with your database configuration:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/dynamic_properties_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Run the application

Use the following command to run the application:

```bash
mvn spring-boot:run
```

The application will start on the default port `8080`.

## REST API Endpoints

### 1. Get Property Value

**URL:** `GET /api/properties/get?propertyName=<propertyName>`

This endpoint returns the current value of the specified property.

```bash
curl http://localhost:8080/api/properties/get?propertyName=greeting
```

### 2. Update Property Value

**URL:** `POST /api/properties/update?propertyName=<propertyName>&newValue=<newValue>`

This endpoint updates the value of the specified property in the database.

```bash
curl -X POST "http://localhost:8080/api/properties/update?propertyName=greeting&newValue=HelloWorld"
```

### 3. Trigger Application Property Refresh

**URL:** `POST /actuator/refresh`

This endpoint refreshes the application’s property values from the database.

```bash
curl -X POST http://localhost:8080/actuator/refresh
```

### 4. Test Greeting

**URL:** `GET /greet`

This endpoint retrieves the dynamic greeting message.

```bash
curl http://localhost:8080/greet
```

## How It Works

1. The application loads property values from the database when it starts up.
2. The properties can be dynamically updated via the provided API endpoints.
3. After updating the property values, the `/actuator/refresh` endpoint is triggered to reload the new values into the application.
4. The `@Value` annotated fields, such as the greeting message, will reflect the updated value once refreshed.

## Database Table Example

```sql
CREATE TABLE dynamic_properties (
    id SERIAL PRIMARY KEY,
    dynamic_property_name VARCHAR(255) NOT NULL,
    dynamic_property_value VARCHAR(255) NOT NULL
);
```

Insert sample data:

```sql
INSERT INTO dynamic_properties (dynamic_property_name, dynamic_property_value) 
VALUES ('greeting', 'hellofromdb');
```

## Future Enhancements

- Implement caching to avoid frequent DB calls for static properties.
- Add authentication for updating and fetching properties.
- Support for multiple environments (dev, prod, etc.) using profiles.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details. 
