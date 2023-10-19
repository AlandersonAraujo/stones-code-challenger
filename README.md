# StarStore API Documentation

This documentation outlines the StarStore API, an e-commerce project built with Spring Boot. The project includes features such as product management, purchase history, integration with H2 database, and Memcached caching.

## Overview

The StarStore API is a RESTful application developed to provide basic e-commerce functionalities. Below are some of the key features of the API:

- **Products:**
    - Querying all products.
    - Adding new products.

- **Purchase History:**
    - Querying the purchase history of all customers.
    - Querying the purchase history of a specific customer.

- **Purchases:**
    - Processing a new purchase.
    - Associating customers and credit cards with purchases.

## API Endpoints

### Products

#### Query All Products

- **Endpoint:** `/starstore/product`
- **Method:** `GET`
- **Description:** Returns all products available in the store.

#### Add New Product

- **Endpoint:** `/starstore/product`
- **Method:** `POST`
- **Description:** Adds a new product to the store catalog.

### Purchase History

#### Query Purchase History

- **Endpoint:** `/starstore/history`
- **Method:** `GET`
- **Description:** Returns the purchase history of all customers.

#### Query Customer Purchase History

- **Endpoint:** `/starstore/history/{clientId}`
- **Method:** `GET`
- **Path Parameters:**
    - `{clientId}`: Customer ID for which to retrieve the purchase history.
- **Description:** Returns the purchase history of a specific customer.

### Purchases

#### Process New Purchase

- **Endpoint:** `/starstore/buy`
- **Method:** `POST`
- **Description:** Processes a new purchase, associating the customer and credit card with the purchase.

## Environment Configuration

### Database

The StarStore API uses the H2 database. Ensure that you correctly configure environment variables or configuration files to specify the database credentials.

### Memcached

Integration with Memcached is performed for caching operations. Ensure that a Memcached server is available and configured correctly. Settings can be adjusted in the `docker-compose.yml` file and in the application's environment variables.

## How to Run the Project

1. **Build the Project:**
   ```bash
   mvn clean install

2. **Run with Docker Compose:**
   ```bash
   docker-compose up --build

The project will be available at http://localhost:8080

### References
This project was developed as part of a technical challenge. The original challenge can be found on https://github.com/stone-payments/desafio-backend.
