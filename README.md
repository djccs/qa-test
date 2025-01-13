<<<<<<< HEAD
# qa-test
=======
# README - Booking API Test Framework

## **Project Overview**
This repository contains automated tests for the Booking API, designed using Java, Maven, and TestNG. The tests validate the functionality, reliability, and robustness of the Booking API endpoints, including creating bookings, retrieving bookings, and handling overlapping bookings.

---

## **Features**
- Automated test cases for:
  - Creating a booking.
  - Retrieving a booking by ID.
  - Validating overlapping booking restrictions.
- Integration with Faker library to generate realistic test data dynamically.
- Supports HTTP request validation and response verification using RestAssured.

---

## **Prerequisites**

### **Tools and Technologies:**
- **Java**: Programming language for the test framework.
- **Maven**: Build and dependency management.
- **TestNG**: Test framework.
- **RestAssured**: HTTP client library for API testing.
- **Faker**: Library for generating fake test data.

### **System Requirements:**
- JDK 8 or higher
- Maven 3.6 or higher
- IDE (e.g., IntelliJ IDEA, Eclipse)

---

## **Installation**
1. Clone the repository:
   ```bash
   git clone <repository_url>
   ```
2. Navigate to the project directory:
   ```bash
   cd booking-api-tests
   ```
3. Install dependencies:
   ```bash
   mvn install
   ```

---

## **Running Tests**
1. Execute all tests:
   ```bash
   mvn test
   ```
2. Run a specific test class:
   ```bash
   mvn -Dtest=BookingTests test
   ```
3. Generate test reports:
   ```bash
   mvn surefire-report:report
   ```

---

## **Test Plan**

### **Objective**
The objective is to ensure the provided API endpoints function as intended, handle errors gracefully, and return the correct responses for all test scenarios.

### **Scope**
#### **In-Scope:**
- Functional Testing of API Endpoints:
   - `/properties` (Create and Retrieve Properties)
   - `/bookings` (Create Bookings and Validate Overlapping Reservations)
- Validation of API responses for valid and invalid inputs.
- Error handling and status code validation.

#### **Out-of-Scope:**
- Load and performance testing.
- Security testing outside HTTP BASIC Authentication.

### **Test Environment**
- **Base URL:** [https://qa-assessment.services.hostfully.com](https://qa-assessment.services.hostfully.com)  
- **Swagger Documentation:** [Swagger UI](https://qa-assessment.services.hostfully.com/swagger-ui/index.html)  
- **Authentication:** HTTP BASIC Auth with credentials:
   - **User:** candidate@hostfully.com  
   - **Password:** NaX5k1wFadtkFf

---

## **Test Scenarios**

### **1. /properties Endpoint**
#### **1.1 Property Creation**
- **Positive Test Cases:**
  1. Create a property with all required fields (`id`, `alias`).
  2. Validate the response for a successful creation (`201 Created`).

- **Negative Test Cases:**
  1. Missing required fields (`id`, `alias`).
  2. Invalid data types for fields (e.g., passing a number for `alias`).
  3. Duplicate `id`.
  4. Empty strings for `alias` or other fields.
  5. Long strings exceeding the maximum character limit.

#### **1.2 Property Retrieval**
- **Positive Test Cases:**
  1. Retrieve a property with a valid `id` and verify all fields in the response.
  2. Validate the HTTP status code is `200 OK`.

- **Negative Test Cases:**
  1. Retrieve a property with a non-existent `id` (`404 Not Found`).
  2. Retrieve a property with an invalid `id` format (e.g., malformed UUID).
  3. Empty `id` in the request.

---

### **2. /bookings Endpoint**
#### **2.1 Booking Creation**
- **Positive Test Cases:**
  1. Create a booking with all required fields (`id`, `propertyId`, `startDate`, `endDate`, `status`, `guest`).
  2. Validate a successful booking creation (`201 Created`) with correct details in the response.

- **Negative Test Cases:**
  1. Missing required fields:
     - `propertyId`: Ensure booking creation fails (`400 Bad Request`).
     - `startDate`, `endDate`: Ensure booking creation fails (`400 Bad Request`).
     - `guest` details (`firstName`, `lastName`, `dateOfBirth`): Ensure error response.
  2. Overlapping bookings for the same `propertyId` (`400 Bad Request`).
  3. Invalid date formats for `startDate`, `endDate` (e.g., non-`yyyy-MM-dd`).
  4. End date earlier than start date.
  5. Invalid `status` value (e.g., values outside expected set like `CONFIRMED`, `CANCELLED`).
  6. Property ID does not exist (`404 Not Found`).

#### **2.2 Booking Retrieval**
- **Positive Test Cases:**
  1. Retrieve a booking with a valid `id` and validate field values.
  2. Ensure response status is `200 OK`.

- **Negative Test Cases:**
  1. Retrieve a booking with a non-existent `id` (`404 Not Found`).
  2. Retrieve a booking with an invalid `id` format.
  3. Empty `id` in the request.

---

### **3. Error Handling**
- **Scenarios:**
  1. Invalid HTTP method usage (e.g., `PUT` on `/bookings` endpoint should return `405 Method Not Allowed`).
  2. Unauthenticated requests (missing or incorrect credentials) (`401 Unauthorized`).
  3. Invalid content types in requests (e.g., `text/plain` instead of `application/json`) (`415 Unsupported Media Type`).
  4. Unavailable endpoints (`404 Not Found`).

---

## **Test Data Generation**
Dynamic test data is generated using the **Faker** library. For example:
- Guest names: Randomly generated.
- Dates: Formatted as `yyyy-MM-dd`.
- Unique identifiers: Generated using `UUID`.

---

## **Directory Structure**
```plaintext
src/
 ├── main/
 │   └── java/
 │       └── api/
 │           ├── endpoints/     # API endpoint classes
 │           └── payload/       # POJOs for request/response bodies
 └── test/
     └── java/
         └── api/
             └── test/          # Test classes
images/
README.md
pom.xml
```

---

## **Passing tests proff**
![Passing tests](./images/Screenshot%202025-01-13%20at%206.06.58%E2%80%AFPM.png "Passing tests")

---

>>>>>>> 7b6b840 (Initial commit - Adding framework for Hostfully API tests)
