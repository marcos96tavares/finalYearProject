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


📦 Features
🧑 Admins can manage classes, attendance, and analytics

🧾 Users can register, pay, and book classes

🤖 Integrated LLM chatbot for real-time assistance

📊 ML model predicts class attendance

🔐 Secure API with JWT Authentication

🚀 Running Locally
Clone the repo:

bash
Copy
Edit
git clone https://github.com/your-username/gymshark-backend.git
cd gymshark-backend
Set up MySQL and update application.properties.

Start each microservice:

bash
Copy
Edit
./mvnw spring-boot:run
Ensure Eureka (service-registry) and api-gateway are running.
