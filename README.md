# ABCDE-Internship-
Full stack Shopping Cart

E-commerce Application (React + Spring Boot)
Project Overview
This e-commerce application, built with a Spring Boot backend and a React frontend, fulfills the requirements for user management, item management, cart operations, and order management. It features token-based authentication to secure cart and order endpoints, ensuring only authenticated users can add items to a cart or view/create orders. The backend uses Spring Security with a custom TokenAuthenticationFilter to validate tokens, and the frontend uses Axios to interact with the API. The application resolves 403 Forbidden errors by properly handling tokens and configuring CORS for http://localhost:3000.
Features Implemented

User Management:
Register users (POST /users).
Login to generate a token (POST /users/login).
List all users (GET /users).


Item Management:
Create items (POST /items).
List all items (GET /items).


Cart Management:
Add items to a user’s cart (POST /carts, authenticated).
List all carts (GET /carts).


Order Management:
Create an order from a cart (POST /orders, authenticated).
List a user’s orders (GET /orders, authenticated).


Security:
Token-based authentication via Authorization header.
CORS configured for http://localhost:3000.
CSRF disabled for testing (production recommendations provided).


Frontend: React component (ItemList.js) for user interaction, including login, item listing, cart addition, and order viewing.

Technologies Used

Backend: Spring Boot, Spring Security, Spring Data JPA, H2 Database
Frontend: React, Axios
Tools: Maven, Node.js, Postman

Prerequisites

Backend:
Java 17 or higher
Maven 3.6+
H2 Database (in-memory, default)


Frontend:
Node.js 16+ and npm
Browser (e.g., Chrome)


Testing:
Postman for API testing


IDE (optional): IntelliJ IDEA, VS Code

Setup Instructions
Backend Setup

Clone the Repository:
git clone <repository-url>
cd ecommerce-backend


Project Structure:Ensure the following files are in place:

src/main/java/com/example/ecommerce_backend/controller/:
UserController.java (user registration, login, listing)
ItemController.java (item creation, listing)
CartController.java (cart operations)
OrderController.java (order operations)


src/main/java/com/example/ecommerce_backend/service/:
UserService.java (user logic with token generation)
ItemService.java (item logic)
CartService.java (cart logic)
OrderService.java (order logic)


src/main/java/com/example/ecommerce_backend/security/SecurityConfig.java (token-based authentication)
pom.xml (dependencies)
src/main/resources/application.properties (database configuration)


Dependencies:Update pom.xml:
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>


Database Configuration:Add to src/main/resources/application.properties:
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
logging.level.org.springframework.security=DEBUG


Run the Backend:
mvn spring-boot:run


Backend runs on http://localhost:8080.
H2 console: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:testdb).



Frontend Setup

Create or Clone Frontend:
npx create-react-app frontend
cd frontend


Install Axios:
npm install axios


Add ItemList.js:Copy the provided ItemList.js (from previous responses) to src/ItemList.js. It handles login, item listing, cart addition, and order viewing with proper token management.

Run the Frontend:
npm start


Frontend runs on http://localhost:3000.



Running the Application

Start the Backend:
cd ecommerce-backend
mvn spring-boot:run


Start the Frontend:
cd frontend
npm start


Initialize Data:

Create a user:curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{"username": "testuser", "password": "testpass"}'


Create an item:curl -X POST http://localhost:8080/items \
-H "Content-Type: application/json" \
-d '{"name": "Test Item", "price": 10.0}'




Interact via Frontend:

Open http://localhost:3000.
Click “Login” with testuser/testpass (update ItemList.js with valid credentials).
Use “Add to Cart” and “View Orders” buttons.



Interacting with the API
The API uses token-based authentication. Obtain a token via POST /users/login and include it in the Authorization header for protected endpoints (POST /carts, POST /orders, GET /orders).
Endpoints

Users:
POST /users: Register a user (no auth).
POST /users/login: Login to get a token (no auth).
GET /users: List all users (no auth).


Items:
POST /items: Create an item (no auth).
GET /items: List all items (no auth).


Carts:
POST /carts: Add item to cart (requires Authorization: <token>).
GET /carts: List all carts (no auth).


Orders:
POST /orders: Create an order (requires Authorization: <token>).
GET /orders: List user’s orders (requires Authorization: <token>).



Postman Testing

Import the provided ecommerce-api.postman_collection.json into Postman.
Set the token environment variable after running Login.
Run requests in order: register, login, create item, add to cart, create order, view orders.
See the Postman collection for raw data samples.

Troubleshooting

403 Forbidden Errors:
Ensure the Authorization header contains the exact token from POST /users/login.
Verify the token exists in the users table (H2 console: SELECT * FROM users).
Check server logs (logging.level.org.springframework.security=DEBUG) for authentication errors.
Test with Postman to isolate frontend issues.


CORS Issues:
Confirm http://localhost:3000 is allowed in SecurityConfig.java.
Check browser console for CORS errors.


Database Issues:
Verify users and items exist (H2 console: http://localhost:8080/h2-console).
Ensure itemId and cartId are valid in POST /carts and POST /orders.



Notes for Production

Re-enable CSRF protection in SecurityConfig.java and configure the frontend to include CSRF tokens.
Use a persistent database (e.g., MySQL) instead of H2.
Secure the token storage in the frontend (e.g., HttpOnly cookies instead of localStorage).
