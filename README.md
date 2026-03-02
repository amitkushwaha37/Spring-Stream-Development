# Employee REST API (Spring Boot)

## 📌 Project Overview

This project is a **Spring Boot REST API** for managing employee data.
It provides APIs to **create, read, update, and delete employees** and also integrates **Swagger for API documentation**, **Actuator for monitoring**, and **RabbitMQ for messaging**.

---

# 🚀 Technologies Used

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven
* MySQL
* Lombok
* Swagger (OpenAPI)
* Spring Boot Actuator
* RabbitMQ
* Postman
* Git & GitHub

---

# 📂 Project Structure

```
restApi
 ├── controller
 │     └── EmployeeController
 │
 ├── service
 │     └── EmployeeService
 │
 ├── serviceimpl
 │     └── EmployeeServiceImpl
 │
 ├── repository
 │     └── EmployeeRepository
 │
 ├── entity
 │     └── EmployeeEntity
 │
 ├── model
 │     └── EmployeeModel
 │
 ├── customException
 │     └── EmployeeNotFoundException
 │
 ├── rabbitmq
 │     ├── config
 │     │     └── RabbitMQConfig
 │     │
 │     ├── producer
 │     │     └── EmployeeProducer
 │     │
 │     └── consumer
 │           └── EmployeeConsumer
 │
 └── RestApiApplication.java
```


# 📡 API Endpoints

### Create Employee

POST /employees

### Get All Employees

GET /employees

### Get Employee by ID

GET /employees/{id}

### Update Employee

PUT /employees/{id}

### Delete Employee

DELETE /employees/{id}

---

# 📖 Swagger API Documentation

Swagger provides an interactive UI to test APIs directly from the browser.

### Swagger URL

```
http://localhost:8080/swagger-ui/index.html
```

### Dependency

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>
```

---

# 📊 Spring Boot Actuator

Actuator helps to **monitor application health, metrics, and environment information**.

### Actuator Endpoint

```
http://localhost:8080/actuator
```

### Example Endpoints

```
/actuator/health
/actuator/info
/actuator/metrics
```

### Dependency

```xml
<dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

---

# 🐰 RabbitMQ Integration

RabbitMQ is used for **asynchronous messaging between services**.

### RabbitMQ Dashboard

```
http://localhost:15672
```

Default credentials:

```
username : guest
password : guest
```

### Dependency

```xml
<dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

### RabbitMQ Flow

```
Producer → Exchange → Queue → Consumer
```

Example:

* Producer sends employee message
* Exchange routes message
* Queue stores message
* Consumer processes message

---

# 🧪 API Testing

You can test APIs using **Postman** or **Swagger UI**.

Example request:

POST /employees

Body:

```json
{
  "name": "Amit Kushwaha",
  "email": "amitkushwaha37@gmail.com",
  "salary": 500,
  "department": "IT",
  "age": 29,
  "city": "Navi Mumbai"
}
```

---

# ▶️ Run the Project

Clone repository

```
git clone https://github.com/your-username/repository-name.git
```

Go to project folder

```
cd restApi
```

Run project

```
mvn spring-boot:run
```

Application will start at

```
http://localhost:8080
```

---

# 👨‍💻 Author

Amit Kushwaha
