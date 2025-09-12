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

### CI/CD Pipeline Strategy

This project implements a **branch-based CI/CD pipeline** using CircleCI with different behaviors for development and production environments:

#### Branch Workflow
- **`develop` branch**: Continuous Integration only (build → test → security scan)
- **`release` branch**: Full CI/CD pipeline (build → test → security scan → containerize → deploy)

#### Pipeline Stages

**1. Build & Test (Both Branches)**
- ☕ **Build**: Compiles Java 17 application using Maven with dependency caching
- 🧪 **Test**: Runs 6 comprehensive unit tests against PostgreSQL 16.3 database
- 🔒 **SAST Scan**: Static application security testing using Snyk (fails on high-severity issues)

**2. Production Pipeline (Release Branch Only)**
- 🐳 **Docker Build**: Creates containerized application tagged with git commit SHA
- 🛡️ **Vulnerability Scan**: Snyk scans Docker image for container vulnerabilities
- 📦 **Docker Push**: Publishes validated image to Docker Hub (`willhooi/inventory-tracker`)
- 🚀 **Deploy**: Automated deployment to Heroku with database configuration

#### Security Features
- **Fail-fast approach**: Pipeline stops immediately on any failure
- **Multi-layer scanning**: Both code (SAST) and container vulnerability scanning
- **Branch protection**: Only `release` branch can deploy to production
- **Secure credential management**: Environment variables for sensitive data

#### Usage
- Push to **`develop`** to validate changes (build, test, security scan)
- Push to **`release`** to deploy to production (full pipeline)
- Monitor pipeline status in CircleCI dashboard
- Test deployed application endpoints using Postman


This project is a group collaboration (together with Zarni, Ernest & Andy) for the SCTP Software Engineering (Module 3 Java backend framework & API & Module 4 DevOps.)
