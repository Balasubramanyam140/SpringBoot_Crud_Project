# 🏦 Spring Boot Customer CRUD API

A RESTful CRUD application built with **Spring Boot 4.1.1** for managing customer records. This project demonstrates clean layered architecture using Spring MVC, Spring Data JPA, and an H2 in-memory database.

---

## 🛠️ Tech Stack

| Technology | Version |
|---|---|
| Java | 17 |
| Spring Boot | 4.1.1 |
| Spring Data JPA | (via Spring Boot) |
| Spring Web MVC | (via Spring Boot) |
| H2 Database | In-Memory |
| Maven | Build Tool |

---

## 📁 Project Structure

```
app-crud/
├── src/
│   ├── main/
│   │   ├── java/com/bank/app/
│   │   │   ├── AppMainApplication.java       # Spring Boot entry point
│   │   │   ├── controller/
│   │   │   │   └── CustomerController.java   # REST API layer
│   │   │   ├── model/
│   │   │   │   └── Customer.java             # JPA Entity
│   │   │   ├── repository/
│   │   │   │   └── CustomerRepository.java   # Data access layer
│   │   │   └── service/
│   │   │       └── CustomerService.java      # Business logic layer
│   │   └── resources/
│   │       └── application.properties        # App configuration
│   └── test/
│       └── java/com/bank/app/
│           └── AppMainApplicationTests.java
└── pom.xml
```

---

## ⚙️ Configuration

**`application.properties`**

```properties
spring.application.name=app-crud

# H2 In-Memory Database
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:test
```

> **H2 Console** is accessible at: `http://localhost:8080/h2-console`
> Use JDBC URL: `jdbc:h2:mem:test`

---

## 🧩 Customer Entity

```json
{
  "id":          "Long   (Auto-generated primary key)",
  "firstName":   "String",
  "lastName":    "String",
  "email":       "String  (Unique constraint)",
  "phoneNumber": "String"
}
```

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.6+

### Run the Application

```bash
# Clone the repository
git clone https://github.com/<your-username>/app-crud.git
cd app-crud

# Run with Maven wrapper
./mvnw spring-boot:run
```

The server starts at: **`http://localhost:8080`**

---

## 📡 API Reference

**Base URL:** `http://localhost:8080/api/customer`

---

### 1. ➕ Create Customer

| Field | Value |
|---|---|
| **Method** | `POST` |
| **URL** | `/api/customer` |
| **Content-Type** | `application/json` |

**Request Body:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "9876543210"
}
```

**Success Response — `200 OK`:**
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "9876543210"
}
```

---

### 2. 📋 Get All Customers

| Field | Value |
|---|---|
| **Method** | `GET` |
| **URL** | `/api/customer` |

**Success Response — `200 OK`:**
```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "9876543210"
  },
  {
    "id": 2,
    "firstName": "Jane",
    "lastName": "Smith",
    "email": "jane.smith@example.com",
    "phoneNumber": "9123456789"
  }
]
```

---

### 3. 🔍 Get Customer by ID

| Field | Value |
|---|---|
| **Method** | `GET` |
| **URL** | `/api/customer/{id}` |
| **Path Variable** | `id` — Customer ID (Long) |

**Success Response — `200 OK`:**
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "9876543210"
}
```

**Not Found — `404 Not Found`:**
Returned when no customer exists with the given `id`.

---

### 4. ✏️ Update Customer

| Field | Value |
|---|---|
| **Method** | `PUT` |
| **URL** | `/api/customer/{id}` |
| **Content-Type** | `application/json` |
| **Path Variable** | `id` — Customer ID (Long) |

**Request Body:**
```json
{
  "firstName": "John",
  "lastName": "Updated",
  "email": "john.updated@example.com",
  "phoneNumber": "9000000000"
}
```

**Success Response — `200 OK`:**
```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Updated",
  "email": "john.updated@example.com",
  "phoneNumber": "9000000000"
}
```

**Not Found — `404 Not Found`:**
Returned when no customer exists with the given `id`.

---

### 5. 🗑️ Delete Customer

| Field | Value |
|---|---|
| **Method** | `DELETE` |
| **URL** | `/api/customer/{id}` |
| **Path Variable** | `id` — Customer ID (Long) |

**Success Response — `200 OK`:**
```
Customer Deleted
```

**Not Found — `404 Not Found`:**
Returned when no customer exists with the given `id`.

---

## 📊 API Summary Table

| Operation | Method | Endpoint | Body Required |
|---|---|---|---|
| Create Customer | `POST` | `/api/customer` | ✅ Yes |
| Get All Customers | `GET` | `/api/customer` | ❌ No |
| Get Customer by ID | `GET` | `/api/customer/{id}` | ❌ No |
| Update Customer | `PUT` | `/api/customer/{id}` | ✅ Yes |
| Delete Customer | `DELETE` | `/api/customer/{id}` | ❌ No |

---

## 🧪 Testing with cURL

```bash
# Create a customer
curl -X POST http://localhost:8080/api/customer \
  -H "Content-Type: application/json" \
  -d '{"firstName":"John","lastName":"Doe","email":"john@example.com","phoneNumber":"9876543210"}'

# Get all customers
curl http://localhost:8080/api/customer

# Get customer by ID
curl http://localhost:8080/api/customer/1

# Update a customer
curl -X PUT http://localhost:8080/api/customer/1 \
  -H "Content-Type: application/json" \
  -d '{"firstName":"John","lastName":"Updated","email":"john.updated@example.com","phoneNumber":"9000000000"}'

# Delete a customer
curl -X DELETE http://localhost:8080/api/customer/1
```

---

## 🏗️ Architecture

```
Client Request
      │
      ▼
CustomerController   ──── REST Layer (@RestController)
      │
      ▼
CustomerService      ──── Business Logic Layer (@Service)
      │
      ▼
CustomerRepository   ──── Data Access Layer (JpaRepository)
      │
      ▼
H2 In-Memory DB      ──── Database (jdbc:h2:mem:test)
```

---

## 📝 License

This project is open-source and available for learning and practice purposes.
