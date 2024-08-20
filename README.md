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
El Proyecte Grande is a modern social media platform with a robust backend and a responsive frontend. Users can upload images, add descriptions, like posts, and comment. This project demonstrates comprehensive full-stack development, including database management, RESTful API design, user authentication, and responsive UI development.

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
  - User Registration and Authentication using Spring Security and JWT tokens.
  - Features include:
    - User Registration: Allows new users to sign up.
    - Authentication: Users log in to receive a JWT token.
    - Authorization: Secures endpoints to authenticated users.
    - Role-based Access Control: Differentiates between user and admin roles.

  ### Frontend Integration
  - Built with React-Vite for a responsive and dynamic user interface.
  - Features include:
    - Responsive design for various screen sizes.
    - RESTful API integration for smooth data exchange between frontend and backend.
    - Client-side routing with React Router for multiple pages.

  ### Integration Testing
  - Comprehensive integration tests using MockMvc and Mockito.
  - Tests cover API calls and database interactions to ensure system reliability.

## Usage
Using El Proyecte Grande:
  - Register an Account:
    - Detail: Sign up to create a new account.
    - Visual: ![SignupImage](.//ImagesReadme/signup.png)
  - Log In:
    - Detail: Use your credentials to log in.
    - Visual: ![LoginImage](.//ImagesReadme/login.png)
  - Set the Theme:
    - Detail: Choose between light or dark mode.
    - Visuals: ![LightModeImage](.//ImagesReadme/lightMode.png) ![DarkModeImage](.//ImagesReadme/darkMode.png)
  - Post Content:
    - Detail: Upload pictures, add descriptions, and share them with others.
    - Visual: ![UploadPicture](.//ImagesReadme/uploadPicture.png)
  - Interact:
    - Detail: Like posts and leave comments to engage with the community.
    - Visuals: ![LikePost](.//ImagesReadme/likePost.png) ![comment](.//ImagesReadme/comment.png)
  - Report posts:
    - Detail: Flag inappropriate content for review.
    - Visual: ![ReportPost](.//ImagesReadme/reportPost.png)
  - Admin Access:
    - Detail: Admins can manage users and posts via the [http://localhost:5173/admin-dashboard](Admin Dashboard).
    - Visuals: ![HandleMembers](.//ImagesReadme/handleMembers.png) ![HandlePosts](.//ImagesReadme/handlePosts.png)

## Technologies
Technologies used in this application:
  - Backend: Spring Boot, Spring MVC, Spring Security, Hibernate, PostgreSQL
  - Frontend: React-Vite, Vanilla css
  - Containerization: Docker, Docker Compose