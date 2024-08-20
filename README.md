# Welcome to the El Proyecte Grande Project Repository!

El Proyecte Grande is a full-stack web application designed as a social media platform where users can upload pictures, add descriptions, like posts, and leave comments. The primary focus of this data-centric application is to manage and process user-generated data effectively.

Developed by:
- [Eszter Fodor](https://github.com/eszti9902)
- [Brigitta Zsugonics](https://github.com/zsbrigi)
- [Roland Keszeg](https://github.com/keszegroland)

## Table of Contents
- [Overview](#overview)
- [Getting Started](#getting-Started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Features](#features)
  - [Dockerization](#dockerization)
  - [User Management](#user-management)
  - [Frontend Integration](#frontend-integration)
  - [Integration Testing](#integration-testing)
- [Usage](#usage)
- [Technologies](#technologies)

## Overview

## Getting Started
  ### Prerequisites
  Ensure the following are installed:
  - Java 17
  - Maven 3.9+
  - Docker
  - PostgreSQL
  - Node.js and npm (for the frontend)

  ### Installation
  To set up the project locally:
1. Clone the repository from GitHub:
   ```bash
    git@github.com:CodecoolGlobal/el-proyecte-grande-sprint-1-java-keszegroland.git

    # navigate into the project directory
    cd el-proyecte-grande-sprint-1-java-keszegroland/
   ```

2. Backend Setup:
   - Navigate to the backend directory:
     ```bash
      cd backend
     ```

   - Build the project:
     ```bash
      mvn clean install
     ```

   - Set environment variables:
     - Option 1: Using PowerShell
       ```bash
        $env:DATABASE_URL="jdbc:postgresql://localhost:5432/strings"
        $env:DATABASE_USERNAME="YOUR_DATABASE_USERNAME"
        $env:DATABASE_PASSWORD="YOUR_DATABASE_PASSWORD"
        $env:JWTSECRETKEY="YOUR_JWT_SECRET_KEY"
       ```

     - Option 2: Using Command Prompt
       ```bash
        set DATABASE_URL=jdbc:postgresql://localhost:5432/strings
        set DATABASE_USERNAME=YOUR_DATABASE_USERNAME
        set DATABASE_PASSWORD=YOUR_DATABASE_PASSWORD
        set JWTSECRETKEY=YOUR_JWT_SECRET_KEY
       ```

   - Run the application:
     ```bash
      mvn spring-boot:run
     ```

3. Frontend Setup:
   - Navigate to the frontend directory:
     ```bash
      cd frontend_vite
     ```

   - Install dependencies:
     ```bash
      npm install
     ```

   - Start the development server:
       ```bash
        npm run dev
       ```

## Features
  ### Dockerization
  - The application is fully containerized using Docker.
  - Docker Compose manages multi-container setups, including the PostgreSQL database.
  - To run the application with Docker Compose:
    - Set environment variables in `docker-compose.yaml`.
        ```bash
        # db env variables
         POSTGRES_DB=YOUR_DB_NAME
         POSTGRES_USER=YOUR_DB_USERNAME
         POSTGRES_PASSWORD=YOUR_DB_PASSWORD

        ## backend env variables
         SPRING_DATASOURCE_USERNAME=YOUR_DB_USERNAME
         SPRING_DATASOURCE_PASSWORD=YOUR_DB_PASSWORD
        ```
    - Build and start the containers:
        ```bash
         docker compose up --build
        ```

  ### User Management

  ### Frontend Integration

  ### Integration Testing

## Usage

## Technologies