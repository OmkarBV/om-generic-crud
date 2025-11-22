# Generic CRUD Module (Java 21 + Spring Boot)

This module provides a fully reusable and extensible **Generic CRUD Framework** for Spring Boot applications using **Java 21**, **Spring Data JPA**, and **Maven**.  
It eliminates the need to repeatedly write boilerplate CRUD logic for every entity.

---

## ✅ What Problem Does This Solve?

Typical Spring Boot projects require writing the same code repeatedly:

- Entity-specific repositories
- Duplicate CRUD methods
- Repeated controller endpoints
- Manual full/partial update logic
- Pagination handling
- Soft delete patterns
- Repeating service layer code

This module provides a **generic**, **clean**, and **reusable** solution:

- One `BaseEntity`
- One `BaseRepository`
- One `BaseService`
- One `BaseController`
- Derived modules only override entity-specific logic

You can create a full CRUD module for any entity in less than **2 minutes**.

---

## 🚀 How This Works

### 1. BaseEntity
Includes common fields:
- `id`
- `createdAt`
- `updatedAt`
- `isDeleted`

Every entity must extend this base.

---

### 2. BaseRepository
Extends:
- `JpaRepository`
- `JpaSpecificationExecutor`

Provides ready pagination + dynamic query support.

---

### 3. BaseService
Provides:
- Create
- Full update (PUT)
- Partial update (PATCH)
- Get by ID
- Soft delete
- Pagination

Also provides:
- `copyFull()` for replacing entity
- `copyNonNull()` for partial updates
- Lifecycle hooks: before/after create/update/delete

Every entity-specific service extends this.

---

### 4. BaseController
Provides REST endpoints:
- GET /{id}
- GET /
- POST /
- PUT /{id}
- PATCH /{id}
- DELETE /{id}

Works for any entity automatically.

---

## 📌 Example: Customer Module

To understand how this module is used, see the **Customer example**.  
It includes:

- `Customer` entity
- `CustomerRepository`
- `CustomerService`
- `CustomerController`

This example shows how easy it is to plug an entity into the generic CRUD architecture.

Look at the Customer package to see the exact implementation.

---

## 🧩 Benefits

- Reuse 90% of CRUD logic
- Cleaner codebase
- Faster development
- Consistent API design
- Less errors and fewer duplicate files

---

## ✔ Requirements

- Java 21
- Spring Boot 3.x
- Spring Data JPA
- Maven
- Lombok

---

## 🧪 Testing

You can test all endpoints via:

- Swagger UI → `/swagger-ui.html`
- Postman / Thunder Client
- JUnit tests

The Customer example is fully runnable out of the box.

---

## 🎯 Summary

This module drastically reduces boilerplate CRUD code and allows developers to build new domain modules quickly with clean, maintainable architecture. The Customer example demonstrates exactly how simple it is to plug in a new entity and instantly get a full REST CRUD API.

See the **Customer example** to learn how it works.
