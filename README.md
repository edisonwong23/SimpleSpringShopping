## 🛍️ Online Shopping Admin Panel - Baseline Web Application
This project is a simple baseline web application developed for Alice's upcoming online shopping company. It provides core admin functionalities for managing products and user sessions securely.

## 🚀 Features
Admin Login

Add Product (Name and Price)

Edit Product (Name and Price)

Delete Product

Logout

## 🔐 Security Features
Password Hashing using SHA-256

Price Encryption using AES-256 (with a hardcoded encryption key for this phase)

## 🛠️ Technologies Used
Frontend: JSF (Jakarta Faces), jQuery

Backend: Spring Boot 3.5.0, Hibernate ORM

Server: GlassFish

Database: MySQL

IDE: IntelliJ IDEA

## 📦 Setup Instructions
Clone the repository:
```bash
git clone https://github.com/yourusername/your-repo-name.git](https://github.com/edisonwong23/SimpleSpringShopping.git
```
Configure database connection in application.yml.
```bash
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/simpleshoppingdb
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    database-platform: org.hibernate.dialect.MySQL8Dialect
```
Insert initial admin credentials directly into the database (SHA-256 hashed password).

Build and run the project using your IDE or via:

Access the web app via:
```bash
http://localhost:8080
```
## 🧑‍💻 Admin Credentials
For simplicity, insert initial admin credentials directly into the database.

Example SQL:
```bash
-- Create the database
CREATE DATABASE SimpleShoppingDB;
USE SimpleShoppingDB;

-- Create users table
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Create products table
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price VARCHAR(255) NOT NULL
);

INSERT INTO users (name, password) VALUES
('alice', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');-- password: '123

-- Insert sample products (prices can be encrypted as needed)
INSERT INTO products (name, price) VALUES 
('Apple iPhone 15', 'rMSAActU6FV3GyfUfELKgg=='),
('Samsung Galaxy S23', 'Dmnt0uGay2En4JaOl6vo1g=='),
('Google Pixel 7a', 'hDpWe+/oJp0BjaSZXUFD6g==');
```
## 🔒 Notes
SHA-256 is used to hash passwords during both registration and login.

AES-256 is used to encrypt/decrypt product prices in the database.

This version uses hardcoded encryption keys. Replace with secure key management in future iterations.

## 🧾 API Endpoint
```bash
GET /api/products
Returns a list of all products.
GET /api/products/{id}
Returns a product base on the id.
```
## 🔄 JSON Response Format
Each product will be returned in the following structure:
```bash
{
  "name": "product name",
  "price": "product price"
}
```

## 🛡️ API Security
To ensure the API is only accessible by the mobile application, basic token-based security has been implemented.

Requests must include a valid API token in the header:
```bash
Authorization: MySecureApiKey_123
```
## 🛠️ Technologies Used for API
-REST Framework: Jersey (or Spring MVC REST)
-JSON Serialization: Gson / Jackson

## 🧪 Testing Tips
To test the endpoint:

Use Postman or cURL.

Add the Authorization header as shown above.

Ensure the backend is running and connected to the database.
