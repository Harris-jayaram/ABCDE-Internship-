# ABCDE-Internship-
Full stack Shopping Cart

E-commerce Application
Overview
This e-commerce application features a Spring Boot backend and React frontend, supporting user registration, login, item management, cart operations, and order management. It uses token-based authentication for secure cart and order actions, with CORS configured for http://localhost:3000. The project addresses authentication issues (e.g., 403 Forbidden) via a custom token filter.
Features

User registration and login with token generation
Create and list items
Add items to cart (authenticated)
Create and view orders (authenticated)

Prerequisites

Java 17+
Maven 3.6+
Node.js 16+ and npm
Postman for API testing
Browser (e.g., Chrome)

Setup and Running
Clone the Repository
Clone the project:

git clone <repository-url>

Backend Setup

Navigate to backend:
cd ecommerce-backend


Install dependencies:
Ensure pom.xml includes Spring Boot, Security, JPA, and H2 dependencies.


Configure database:
Use H2 in-memory database (configured in application.properties).


Run the backend:
mvn spring-boot:run
Backend runs on http://localhost:8080.



Frontend Setup

Navigate to frontend:
cd frontend


Install dependencies:
npm install
Ensure Axios is installed (npm install axios).


Run the frontend:
npm start
Frontend runs on http://localhost:3000.



Initialize Data

Create a user via Postman (see Postman collection).
Create an item via Postman.
Use the frontend to login, add items to cart, and view orders.

API Interaction
Use Postman to test the API:

Import ecommerce-api.postman_collection.json (provided).
Register a user and login to get a token.
Use the token in the Authorization header for cart and order requests.
Refer to the Postman collection for endpoint details and sample data.

Key Endpoints

POST /users: Register user 

POST /users/login: Get token
GET /users: List users
POST /items: Create item
GET /items: List items
POST /carts: Add to cart (authenticated)
GET /carts: List carts
POST /orders: Create order (authenticated)
GET /orders: List orders (authenticated)

Troubleshooting

403 Forbidden:
Verify the Authorization header uses a valid token from POST /users/login.
Check server logs (logging.level.org.springframework.security=DEBUG).
Ensure user and item exist in the H2 database (http://localhost:8080/h2-console).


CORS Issues:
Confirm http://localhost:3000 is allowed in SecurityConfig.java.


Database:
Verify data in H2 console (JDBC URL: jdbc:h2:mem:testdb).



Postman Testing
Import the provided Postman collection to test all endpoints. Set the token environment variable after login. Sample request/response data is included.
