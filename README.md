# Welcome to the El Proyecte Grande Project Repository!

El Proyecte Grande is a full-stack web application designed as a social media platform where users can upload pictures, add descriptions, like posts, and leave comments. The primary focus of this data-centric application is to manage and process user-generated data effectively.

Developed by:
- [![fodoreszter][fodoreszter]][fodoreszter-url]
- [![zsugonicsbrigitta][zsugonicsbrigitta]][zsugonicsbrigitta-url]
- [![keszegroland][keszegroland]][keszegroland-url]

![LightModeImage](.//ImagesReadme/epg_lightMode.png) 

## Table of Contents
- [Overview](#overview)
- [Technologies](#technologies)
- [Getting Started](#getting-Started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Features](#features)
  - [Dockerization](#dockerization)
  - [User Management](#user-management)
  - [Frontend Integration](#frontend-integration)
  - [Integration Testing](#integration-testing)
- [Usage](#usage)

## Overview
El Proyecte Grande is a modern social media platform with a robust backend and a responsive frontend. Users can upload images, add descriptions, like posts, and comment. This project demonstrates comprehensive full-stack development, including database management, RESTful API design, user authentication, and responsive UI development.

## Technologies
### Backend:
- [![spring-boot][spring-boot]][spring-boot-url]
- [![spring-web-mvc][spring-web-mvc]][spring-web-mvc-url]
- [![spring-data-jpa][spring-data-jpa]][spring-data-jpa-url]
- [![spring-security][spring-security]][spring-security-url]
- [![hibernate][hibernate]][hibernate-url]

### Database:
- [![postgresql][postgresql]][postgresql-url]

### Frontend:
- [![React-Vite][React-Vite]][Vite-URL]
- [![Css][Css3]][Css-url]

### Containerization:
- [![docker][docker]][docker-url]

## Getting Started

### Prerequisites

#### To Run the Project with Docker:
  - [![docker][docker]][docker-url]

#### To Run Backend and Frontend Separately:
  - [![java][java]][java-url]
  - [![maven][maven]][maven-url]
  - [![postgresql][postgresql]][postgresql-url]
  - [![nodejs][node.js]][node-url]
  - [![npm][npm]][npm-url]


### Installation

#### Running the Project with Docker
1. Clone the repository from GitHub into your desired folder:
   ```bash
    git clone git@github.com:CodecoolGlobal/el-proyecte-grande-sprint-1-java-keszegroland.git

    # navigate into the project directory
    cd <foldername>
   ```
2. Set environment variables in `.env.sample`:
   ```bash
   # db env variables
   POSTGRES_DB=YOUR_DB_NAME
   POSTGRES_USER=YOUR_DB_USERNAME
   POSTGRES_PASSWORD=YOUR_DB_PASSWORD

   ## backend env variables
   SPRING_DATASOURCE_USERNAME=YOUR_DB_USERNAME
   SPRING_DATASOURCE_PASSWORD=YOUR_DB_PASSWORD
   CODECOOL_APP_JWTSECRET=YOUR_JWT_SECRET_KEY
   ```
   *The JWT secret key should be 64 characters long and should only include alphanumeric characters (A-Z, a-z, 0-9). It is advisable to avoid using special characters such as `-`, `/`, `+`, and `=` to prevent potential issues with encryption and encoding.*

3. Rename `.env.sample` to `.env`:
   ```
   mv .env.sample .env
   ```

4. Build and start the containers:
   ```bash
   docker compose up --build
   ```

   *Docker will automatically set up the database and run the backend and frontend services.*


#### Running Backend and Frontend Separately

##### Backend Setup:

   - Navigate to the backend directory:
     ```bash
      cd backend
     ```

   - Build the project:
     ```bash
      mvn clean install
     ```

   - Set environment variables:
     - Update application.properties (src/main/resources/application.properties) with your database credentials and security data or you can use terminal commands:
      - Option 1: Using PowerShell
        ```bash
        $env:DATABASE_URL="jdbc:postgresql://localhost:5432/strings"
        $env:DATABASE_USERNAME="YOUR_DATABASE_USERNAME"
        $env:DATABASE_PASSWORD="YOUR_DATABASE_PASSWORD"
        $env:JWTSECRETKEY="YOUR_JWT_SECRET_KEY"
        ```
      *The JWT secret key should be 64 characters long and should only include alphanumeric characters (A-Z, a-z, 0-9). It is advisable to avoid using special characters such as `-`, `/`, `+`, and `=` to prevent potential issues with encryption and encoding.*

      - Option 2: Using Command Prompt
        ```bash
        set DATABASE_URL=jdbc:postgresql://localhost:5432/strings
        set DATABASE_USERNAME=YOUR_DATABASE_USERNAME
        set DATABASE_PASSWORD=YOUR_DATABASE_PASSWORD
        set JWTSECRETKEY=YOUR_JWT_SECRET_KEY
        ```
       *JWT Secret key should be 64 characters long.*

   - Run the application:
     ```bash
      mvn spring-boot:run
     ```

##### Frontend Setup:

   - Navigate to the frontend directory:
     ```bash
      cd ../frontend_vite
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
  - Running with Docker Compose eliminates the need to manually create the database or configure environment variables locally.

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
    - Visual: ![SignupImage](.//ImagesReadme/epg_signup.png)
  - Log In:
    - Detail: Use your credentials to log in.
    - Visual: ![LoginImage](.//ImagesReadme/epg_login.png)
  - Set the Theme:
    - Detail: Set the theme to dark mode.
    - Visuals: ![DarkModeImage](.//ImagesReadme/epg_darkMode.png)
  - Post Content:
    - Detail: Upload pictures, add descriptions, and share them with others.
    - Visual: ![UploadPicture](.//ImagesReadme/epg_uploadPicture.png)
  - Interact:
    - Detail: Like posts and leave comments to engage with the community.
    - Visuals: ![LikePost](.//ImagesReadme/epg_likePost.png) ![comment](.//ImagesReadme/epg_comment.png)
  - Report posts:
    - Detail: Flag inappropriate content for review.
    - Visual: ![ReportPost](.//ImagesReadme/epg_reportPost.png)
  - Admin Access:
    - Detail: Admins can manage users and posts via the [http://localhost:5173/admin-dashboard](Admin Dashboard).
    - Visuals: ![HandleMembers](.//ImagesReadme/epg_handleMembers.png) ![HandlePosts](.//ImagesReadme/epg_handlePosts.png)


  [React-Vite]: https://img.shields.io/badge/-Vite-D3D3D3?logo=Vite&logoColor=646CFF
[Vite-URL]: https://vitejs.dev/guide/

[Css3]: https://img.shields.io/badge/Css-4361ee?style=for-the-badge&logo=css&logoColor=61DAFB
[Css-url]: https://en.wikipedia.org/wiki/CSS

[docker]: https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white
[docker-url]: https://docs.docker.com/engine/install/

[spring-boot]: https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=Spring&logoColor=white
[spring-boot-url]: https://docs.spring.io/spring-boot/installing.html

[spring-web-mvc]: https://img.shields.io/badge/SPRING%20WEB%20MVC-6DB33F?style=for-the-badge&logo=Spring&logoColor=white
[spring-web-mvc-url]: https://docs.spring.io/spring-framework/reference/web/webmvc.html

[spring-data-jpa]: https://img.shields.io/badge/SPRING%20DATA%20JPA-6DB33F?style=for-the-badge&logo=Spring&logoColor=white
[spring-data-jpa-url]: https://spring.io/projects/spring-data-jpa

[spring-security]: https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white
[spring-security-url]: https://spring.io/projects/spring-security

[hibernate]: https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white
[hibernate-url]: https://hibernate.org/ 

[postgresql]: https://img.shields.io/badge/postgresql-4169e1?style=for-the-badge&logo=postgresql&logoColor=white
[postgresql-url]: https://www.postgresql.org/download/

[java]: https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&labelColor=ED8B00&logo=java&color=808080[Java
[java-url]: https://www.java.com/en/download/

[maven]: https://img.shields.io/badge/Maven-4%2B-ED8B00?style=for-the-badge&labelColor=ED8B00&logo=maven&color=808080[Maven
[maven-url]: https://maven.apache.org/

[node.js]: https://img.shields.io/badge/Node.js-339933?style=for-the-badge&logo=nodedotjs&logoColor=white
[node-url]: https://nodejs.org/en

[npm]: https://img.shields.io/badge/npm-CB3837?style=for-the-badge&logo=npm&logoColor=white
[npm-url]: https://www.npmjs.com/

[keszegroland]: https://img.shields.io/badge/Roland%20Keszeg-181717?style=for-the-badge&logo=github&logoColor=white
[keszegroland-url]: https://github.com/keszegroland

[zsugonicsbrigitta]: https://img.shields.io/badge/Brigitta%20Zsugonics-181717?style=for-the-badge&logo=github&logoColor=white
[zsugonicsbrigitta-url]: https://github.com/zsbrigi

[fodoreszter]: https://img.shields.io/badge/Eszter%20Fodor-181717?style=for-the-badge&logo=github&logoColor=white
[fodoreszter-url]: https://github.com/eszti9902