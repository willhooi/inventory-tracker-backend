# 📦 Simple Inventory Tracker

A no-frills inventory tracker built for small businesses like hawkers, minimarts, and home-based sellers. It helps manage stock levels without complex setup or expensive subscriptions.

---

## 🧭 Project Goal

Many small business owners still track stock using pen-and-paper or Excel. This often leads to:

- ❌ Confusion or misplaced records  
- 📦 Overbuying  
- 😰 Running out of items during peak hours

Most inventory apps today are built for large businesses - bloated with features, difficult to use, or costly.

**This project aims to change that** offer a clean, simple solution that:

- ✅ Lets users **add and update product stock** easily  
- 🔁 Allows **quick quantity adjustments** after each sale or restock  
- 🚨 Sends **low stock alerts** so users can restock in time  
- 📱 **Works on both phone and desktop** without special training

---

## 🛠 Tech Stack

### 🧩 Backend

- **Java 17**: Modern Java features and improved performance  
- **Spring Boot**: Builds and serves the RESTful API  
- **Spring Data JPA**: Simplifies database access and ORM  
- **PostgreSQL**: Primary production database  
- **H2**: Lightweight in-memory database for testing  
- **Lombok**: Reduces boilerplate code (getters, setters, constructors)  

### 🧪 Testing & Debugging

- **JUnit 5**: Unit testing framework  
- **Spring Boot Test**: Simplified Spring context testing  
- **MockMvc & Mockito**: For controller testing and mocking  
- **SLF4J + Logback**: Structured logging for development and debugging

---
## ⚒️ DevOps

- Start PostgreSQL in local machine:
    ```sudo service postgresql start```
- Make changes to **develop** branch to run build & test jobs
- Make changes to **release** branch to run build, test, docker_build, scan, docker_push and deploy jobs.
- Check running jobs in CircleCI
- If green build, use Postman to check endpoints


This project is a group collaboration (together with Zarni, Ernest & Andy) for the SCTP Software Engineering (Module 3 Java backend framework & API & Module 4 DevOps.)
