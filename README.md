# Gym Shark Backend

This is the backend service for **Gym Shark**, an intelligent web application designed to streamline the management of a Muay Thai gym. Built using **Spring Boot**, it provides RESTful APIs for user management, class scheduling, payment processing, and integrates Machine Learning and LLM-based chatbot functionalities.

---

## 🛠️ Technologies Used

- Java 17
- Spring Boot
- MySQL
- JWT (JSON Web Token)
- PayPal API
- Azure (Deployment)
- Scikit-learn / TensorFlow (ML Integration)
- ChatGPT API (LLM Integration)

---

## 🧩 Microservices Overview

- `admin-service`: Handles class creation, tracking, and reporting
- `client-service`: Manages users, memberships, video access, and bookings
- `api-gateway`: Entry point with JWT authentication
- `llm-module`: Chatbot powered by OpenAI's LLM
- `service-registry`: (Eureka) Service discovery for microservices

---

## 📁 Project Structure

```bash
backend/
├── admin-service/
│   ├── controller/
│   ├── dto/
│   ├── entity/
│   ├── service/
├── client-service/
│   ├── controller/
│   ├── video/
│   ├── membership/
│   └── payment/
├── api-gateway/
│   ├── filters/
│   ├── config/
├── llm-module/
│   └── chatbot/
└── service-registry/


bash
Copy
Edit
./mvnw spring-boot:run
Ensure Eureka (service-registry) and api-gateway are running.
