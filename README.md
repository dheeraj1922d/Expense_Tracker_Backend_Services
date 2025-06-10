# 📅 Expense Tracker App

A microservices-based system for tracking personal expenses, integrating AI-powered extraction and event-driven architecture. The platform includes services for authentication, user profile management, expense CRUD operations, and AI-assisted data ingestion using LLMs.

---

## 🗂️ Individual Service Repositories

* 🔐 [Authservice Repository](https://github.com/dheeraj1922d/Authservice) - Handles authentication and JWT token management
* 👥 [Userservice Repository](https://github.com/dheeraj1922d/Userservice) - Manages user profile data and consumes Kafka user events
* 💰 [ExpenseService Repository](https://github.com/dheeraj1922d/Expense-Service) - Provides CRUD APIs for managing expenses
* 🧠 [LLMService Repository](https://github.com/dheeraj1922d/LLMService) - Extracts structured expense data using LangChain and Mistral
* 🧠 [Frontend Repository](https://github.com/dheeraj1922d/ExpenseTrackerApp) - React Native frontend
* 🧠 [Expense Tracker Deps](https://github.com/dheeraj1922d/expense-tracker-deps) - expense Tracker dependencies including kong api gateways

---

## 🥚 Microservices Overview

| Service        | Technology                          | Port    | Responsibilities                              |
| -------------- | ----------------------------------- | ------- | --------------------------------------------- |
| Authservice    | Java 21, Spring Boot 3.4.1          | Dynamic | JWT authentication, user registration/login   |
| Userservice    | Java 21, Spring Boot 3.4.1          | Dynamic | User profile management, Kafka event consumer |
| ExpenseService | Java 21, Spring Boot 3.4.1          | Dynamic | Expense creation, update, retrieval           |
| LLMService     | Python, Flask, LangChain, MistralAi | Dynamic | AI-powered expense data extraction via KafkaA |

---

## 📁 Architecture Overview

![Architecture Diagram](https://res.cloudinary.com/draptrzrc/image/upload/v1749528239/xtwwoejxy6ywwghs1ppo.png)

* Stateless JWT authentication
* Kafka-based messaging for user & expense events
* AI service consumes raw expense text and extracts structured data

---

## 🤖 Core Features

### 🔑 Authentication (Authservice)

* `POST /auth/v1/signup`: Register and return JWT + refresh token
* `POST /auth/v1/login`: Authenticate and return JWT + refresh token
* `GET /auth/v1/ping`: Verify JWT and return user ID
* Stateless JWT secured endpoints

### 👤 User Profile (Userservice)

* Kafka listener: consumes `UserInfoDto` for user creation
* `POST /user/v1/createOrUpdate`: Create/update user profile
* `GET /user/v1/getUser`: Retrieve profile using header `X-User-Id`

### 💳 Expense Tracking (ExpenseService)

* `POST /expense/v1/addExpense`: Create expense (requires `X-User-Id`)
* `GET /expense/v1/getExpense`: List all expenses for the user

### 🧐 AI-Powered Ingestion (LLMService)

* Listenning to incoming sms
* Uses LangChain + Mistral to extract:

  * Amount
  * Merchant
  * Currency
* Converts unstructured text into structured Expense JSON

---

## 🚀 Tech Stack

| Component         | Stack Details                                       |
| ----------------- | --------------------------------------------------- |
| Runtime           | Java 21, Python 3.10+                               |
| Frameworks        | Spring Boot 3.4.1, Flask 3.1.0                      |
| Messaging         | Apache Kafka 2.0+                                   |
| ORM               | Spring Data JPA, Hibernate, Pydantic                |
| Database          | MySQL 8.0.33                                        |
| Security          | Spring Security, JJWT 0.12.6, BCrypt                |
| Kafka Integration | Spring Kafka, kafka-python 2.0.2                    |
| AI Integration    | LangChain 0.3.14, Mistral AI (mistral-large-latest) |

---

## 🔄 Communication Patterns

* **Synchronous**: REST APIs with JWT Bearer tokens
* **Asynchronous**:

  * `Authservice` → `userservice`: `userservice-topic`
  * `ExpenseService` → `LLMService`: `expense-events-topic`

---

## 🛠️ Configuration Highlights

### Environment Variables (Spring Services)

```env
MYSQL_HOST=localhost
MYSQL_PORT=3307
KAFKA_HOST=localhost
KAFKA_PORT=29092
```

### Kafka Setup

```properties
bootstrap.servers=localhost:29092
auto-offset-reset=earliest
group-id=event
key-deserializer=StringDeserializer
value-deserializer=JsonDeserializer
```

### Security (JWT)

* Access Token Expiry: 1 min
* Refresh Token Expiry: 10 min
* Signing Algo: HS256 (Base64 secret)

---

## 📂 Database Overview

* **Authservice**: UserInfo, UserRoles, RefreshToken
* **Userservice**: UserInfo (profile)
* **ExpenseService**: Expense (UUID, currency, amount, merchant)

Each service uses its own MySQL schema with HikariCP pooling.

---

## 📚 Build & Deployment

### Java Services

```bash
./mvnw spring-boot:run   # Run locally
./mvnw clean package     # Build JAR
```

### Python LLMService

```bash
pip install -r requirements.txt
python3 src/app/service/llmService.py
```

---

## 📊 Future Enhancements

* OAuth2 social login
* Expense filtering & analytics
* Redis cache for token blacklist

---

## 📄 License

This project is open-source under the MIT License.
