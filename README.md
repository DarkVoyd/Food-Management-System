# Food Management System

A backend system for food delivery management - built with **Spring Boot** and **MySQL**, exposing a REST API for managing customers, drivers, restaurants, menus, orders, and reviews. Includes a lightweight vanilla JS/HTML dashboard for interacting with the API.

## Features

- **User Management** - supports three user types (`Customer`, `Driver`, `Restaurant`), each with role-specific fields (e.g. driver licence, delivery address, restaurant description)
- **Menu Management** - restaurants can create, update, and delete dishes, each with category, price, prep time, calories, and availability
- **Order Management** - places orders linking customers, restaurants, and delivery drivers, with status tracking (`PENDING`, etc.) and filtering by status
- **Messaging & Reviews** - direct messaging between users, plus a review system for rating restaurants and drivers tied to completed orders
- **Web Dashboard** - a tabbed single-page interface (Users / Menu / Orders / Communications) for browsing and managing all entities without a separate frontend framework

## Tech Stack

- **Backend:** Java 17, Spring Boot 4 (Spring Web, Spring Data JPA, Spring HATEOAS)
- **Database:** MySQL
- **Frontend:** Vanilla HTML/CSS/JavaScript (no framework)
- **Build tool:** Gradle

## Architecture

The backend follows a standard layered structure:

```
controllers/   REST endpoints (Menu, Orders, Users, Messages)
model/         JPA entities (User, Customer, Driver, Restaurant, Dish, FoodOrder, Review, Message, etc.)
repos/         Spring Data JPA repositories
service/       Business logic layer
```

Polymorphic user types (`Customer`, `Driver`, `Restaurant`) extend a common `User` entity, allowing shared queries (e.g. listing all users, filtering by type) while preserving role-specific data.

## API Overview

| Endpoint | Description |
|---|---|
| `GET /api/users?type=` | List all users, optionally filtered by type |
| `POST /api/users/customer` | Create or update a customer |
| `POST /api/users/driver` | Create or update a driver |
| `POST /api/users/restaurant` | Create or update a restaurant |
| `DELETE /api/users/{id}` | Delete a user and cascade-clean related orders/messages/dishes |
| `GET /api/menu` | List all dishes |
| `POST /api/menu` | Create or update a dish |
| `DELETE /api/menu/{id}` | Delete a dish |
| `GET /api/orders?status=` | List orders, optionally filtered by status |
| `POST /api/orders` | Create or update an order |
| `DELETE /api/orders/{id}` | Delete an order |
| `GET /api/messages` | List all messages |
| `POST /api/messages/message` | Send a message |
| `POST /api/messages/review` | Submit a review |
| `DELETE /api/messages/{id}` | Delete a message |

## Getting Started

### Prerequisites

- Java 17
- MySQL running locally on port 3306

### Setup

1. Clone the repository
2. Configure your database credentials in `src/main/resources/application.properties` (defaults to `root` with no password, targeting a local MySQL instance - adjust for your environment)
3. Run the application:

   ```bash
   ./gradlew bootRun
   ```

4. The schema is auto-generated on startup (`spring.jpa.hibernate.ddl-auto=update`) - no manual migration needed
5. Open `http://localhost:8080` to use the dashboard, or hit the REST endpoints directly

## Notes

This project was built as university coursework (Programming Techniques) to practice REST API design with Spring Boot, JPA relationship management, and full-stack integration without a frontend framework.
