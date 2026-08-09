## 📖 Table of Contents

- [Description](#description)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture Overview](#architecture-overview)
- [Database Schema Overview](#database-schema-overview)
- [Installation & Setup](#installation-setup)
- [Configuration](#configuration)
- [Running the Project](#running-the-project)
- [Contributing](#contributing)

<a id="description"></a>
## 📝 Description

The **College Attendance Management System** is a backend REST API built with **Spring Boot** that streamlines academic record-keeping for colleges and universities. It enables administrators to manage students, subjects, and teacher-subject assignments, while allowing teachers to mark and monitor attendance for the subjects they teach.

The system enforces **role-based access control (RBAC)**, ensuring that sensitive operations (e.g., creating students or assigning subjects) are restricted to administrators, while teachers have scoped access to attendance-related operations for their own classes.

<a id="features"></a>
## ✨ Features

| Feature | Description |
|---|---|
| 🔐 **User Authentication** | Secure login for Admins and Teachers |
| 🛡️ **Role-Based Authorization** | Distinct permissions for `ADMIN` and `TEACHER` roles |
| 👨‍🎓 **Student Management** | Create, update, view, and manage student records |
| 📚 **Subject Management** | Manage subjects offered across departments/semesters |
| 🔗 **Student-Subject Enrollment** | Map students to the subjects they are enrolled in |
| 👩‍🏫 **Teacher-Subject Assignment** | Assign teachers to the subjects they instruct |
| ✅ **Attendance Management** | Mark, update, and retrieve attendance records |
| 🧪 **Input Validation** | Request validation using Jakarta Bean Validation |
| 🌐 **RESTful APIs** | Clean, resource-oriented API design |
| 🗄️ **PostgreSQL Integration** | Reliable relational data persistence |

<a id="tech-stack"></a>
## 🛠️ Tech Stack

<div align="center">

| Category | Technology |
|---|---|
| Language | ![Java](https://img.shields.io/badge/Java%2017-ED8B00?style=flat-square&logo=openjdk&logoColor=white) |
| Framework | ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white) |
| Security | ![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=flat-square&logo=springsecurity&logoColor=white) |
| Data Access | ![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=flat-square&logo=spring&logoColor=white) ![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=flat-square&logo=hibernate&logoColor=white) |
| Database | ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=flat-square&logo=postgresql&logoColor=white) |
| Build Tool | ![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white) |
| Utility | ![Lombok](https://img.shields.io/badge/Lombok-BC4521?style=flat-square&logo=lombok&logoColor=white) |

</div>

<a id="architecture-overview"></a>
## 🏗️ Architecture Overview

The application follows a classic **layered (N-tier) architecture**, promoting separation of concerns and testability.

```mermaid
flowchart TD
    A[Client/Postman/Frontend] -->|HTTP Request| B[Controller Layer]
    B --> C[Service Layer]
    C --> D[Repository Layer JPA]
    D --> E[(PostgreSQL Database)]
    B -.-> F[Spring Security Filter Chain]
    F -.-> B
    C --> G[DTO / Validation Layer]
```

- **Controller Layer** – Exposes REST endpoints, handles request/response mapping.
- **Service Layer** – Contains business logic and orchestrates operations.
- **Repository Layer** – Interfaces with the database via Spring Data JPA/Hibernate.
- **Security Layer** – Intercepts requests for authentication and role-based authorization.
- **DTO/Validation Layer** – Ensures clean data contracts and enforces input validation.

<a id="database-schema-overview"></a>
## 🗃️ Database Schema Overview

The system is built around six core entities that model the academic relationships between users, students, subjects, and attendance.

```mermaid
erDiagram
    USER ||--o{ TEACHER_SUBJECT : assigned
    STUDENT ||--o{ STUDENT_SUBJECT : enrolled_in
    SUBJECT ||--o{ STUDENT_SUBJECT : includes
    SUBJECT ||--o{ TEACHER_SUBJECT : taught_by
    STUDENT ||--o{ ATTENDANCE : has
    SUBJECT ||--o{ ATTENDANCE : recorded_for
    USER ||--o{ ATTENDANCE : marked_by

    USER {
        Long id PK
        String username
        String password
        String role
    }
    STUDENT {
        Long id PK
        String name
        String rollNumber
        String department
    }
    SUBJECT {
        Long id PK
        String name
        String code
        String semester
    }
    STUDENT_SUBJECT {
        Long id PK
        Long student_id FK
        Long subject_id FK
    }
    TEACHER_SUBJECT {
        Long id PK
        Long teacher_id FK
        Long subject_id FK
    }
    ATTENDANCE {
        Long id PK
        Long student_id FK
        Long subject_id FK
        LocalDate date
        String status
        Long marked_by FK
    }
```

| Entity | Purpose |
|---|---|
| `User` | Stores login credentials and role (`ADMIN` / `TEACHER`) |
| `Student` | Holds student profile and academic details |
| `Subject` | Represents a course/subject offered |
| `StudentSubject` | Join entity mapping students to enrolled subjects |
| `TeacherSubject` | Join entity mapping teachers to assigned subjects |
| `Attendance` | Records daily attendance per student, per subject |

<a id="installation-setup"></a>
## ⚙️ Installation & Setup

### Prerequisites

- Java 17+
- Maven 3.8+
- PostgreSQL 13+
- Git

### Clone the Repository

```bash
git clone https://github.com/sayandwip2004/Students_Attendance_Database.git
cd Students_Attendance_Database
```

### Create the Database

```sql
CREATE DATABASE attendance_db;
```

### Build the Project

```bash
mvn clean install
```

<a id="configuration"></a>
## 🔧 Configuration

Update `src/main/resources/application.properties` with your local database credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/attendance_db
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
app.jwt.secret=your_jwt_secret_key
app.jwt.expiration-ms=86400000
```

<a id="running-the-project"></a>
## ▶️ Running the Project

### Using Maven

```bash
mvn spring-boot:run
```

### Using the Packaged JAR

```bash
mvn clean package
java -jar target/college-attendance-management-system-0.0.1-SNAPSHOT.jar
```

The API will be available at:

```
http://localhost:8080
```

<a id="contributing"></a>
## 🤝 Contributing

Contributions are welcome! To contribute:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Open a Pull Request

Please ensure your code follows existing style conventions and includes relevant tests.


<div align="center">

⭐️ If you found this project useful, consider giving it a star!

</div>
