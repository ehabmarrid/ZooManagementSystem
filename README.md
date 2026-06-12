# Zoo Management System

![Java](https://img.shields.io/badge/Java-17-orange)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![Tests](https://img.shields.io/badge/Tests-JUnit%205-green)

A Java and MySQL application for managing zoo operations.

![Console menu](docs/screenshots/console-menu.svg)

## Features

- Animal CRUD operations
- Enclosure capacity validation
- Worker assignments
- Feeding schedule and completion tracking
- Medical records
- Ticket sales and revenue reporting
- JUnit service tests
- ER diagram and console screenshot

## Project Evolution

Originally developed as an object-oriented programming course project and later modernized with MySQL persistence, layered architecture, service-level business rules, Maven, and automated tests.

## Architecture

The project uses the standard Maven folder layout:

```text
src/main/java/ZooManagementSystem/    Application source packages
src/main/resources/                   SQL schema and sample data
src/test/java/ZooManagementSystem/    JUnit tests
```

The source packages are organized by responsibility:

```text
ZooManagementSystem.app          Console entry point
ZooManagementSystem.zoo          Main zoo coordinator
ZooManagementSystem.animals      Animal domain classes
ZooManagementSystem.people       Visitor, worker, and human classes
ZooManagementSystem.tickets      Ticket and visitor management flows
ZooManagementSystem.promotions   Observer-based promotion notifications
ZooManagementSystem.data         JDBC repositories and database connection
ZooManagementSystem.models       Database row models used by services
ZooManagementSystem.services     Business rules and use cases
ZooManagementSystem.common       Shared enums
ZooManagementSystem.exceptions   Validation exceptions
```

## Database Setup

Create and seed the MySQL database:

```bash
mysql -u root -p < src/main/resources/DataBase.sql
mysql -u root -p zoo_database < src/main/resources/sample.sql
```

Configure credentials outside the code:

```bash
export ZOO_DB_URL="jdbc:mysql://localhost:3306/zoo_database"
export ZOO_DB_USER="root"
export ZOO_DB_PASSWORD="your-db-password"
export ZOO_ADMIN_USERNAME="Admin"
export ZOO_ADMIN_PASSWORD="your-admin-password"
```

The same values can also be provided with Java system properties: `zoo.db.url`, `zoo.db.user`, `zoo.db.password`, `zoo.admin.username`, and `zoo.admin.password`.

## ER Diagram

```mermaid
erDiagram
    ZOO ||--o{ ENCLOSURE : contains
    ZOO ||--o{ ANIMAL : owns
    ENCLOSURE ||--o{ ANIMAL : houses
    WORKER ||--o{ WORKER_ASSIGNMENT : receives
    ANIMAL ||--o{ WORKER_ASSIGNMENT : assigned_for
    ENCLOSURE ||--o{ WORKER_ASSIGNMENT : assigned_for
    ANIMAL ||--o{ FEEDING_SCHEDULE : has
    ENCLOSURE ||--o{ FEEDING_SCHEDULE : has
    WORKER ||--o{ FEEDING_SCHEDULE : completes
    ANIMAL ||--o{ MEDICAL_RECORD : has
    VISITOR ||--o{ TICKET : buys

    ZOO {
        int zooID PK
        varchar name
        varchar location
    }

    ENCLOSURE {
        int enclosureID PK
        int zooID FK
        varchar name
        varchar habitatType
        int capacity
    }

    ANIMAL {
        int animalID PK
        varchar species
        varchar name
        int age
        varchar healthStatus
        varchar habitatType
        int zooID FK
        int enclosureID FK
    }

    WORKER {
        int workerID PK
        varchar firstName
        varchar lastName
        date birthDay
        varchar phoneNumber
        varchar role
    }

    WORKER_ASSIGNMENT {
        int assignmentID PK
        int workerID FK
        int animalID FK
        int enclosureID FK
        varchar responsibility
        date startDate
        date endDate
    }

    FEEDING_SCHEDULE {
        int feedingID PK
        int animalID FK
        int enclosureID FK
        datetime scheduledAt
        varchar foodType
        varchar amount
        boolean completed
        datetime completedAt
        int completedByWorkerID FK
    }

    MEDICAL_RECORD {
        int recordID PK
        int animalID FK
        date visitDate
        varchar diagnosis
        varchar treatment
        varchar veterinarian
        text notes
    }

    VISITOR {
        int visitorID PK
        varchar firstName
        varchar lastName
        date birthDay
        varchar phoneNumber
        int numberOfTickets
        boolean joinMailinglist
    }

    TICKET {
        int ticketID PK
        int visitorID FK
        date dateOfVisit
        varchar ticketType
        decimal price
    }
```

## Running

Compile with Maven:

```bash
mvn compile
```

Run the console application:

```bash
mvn exec:java
```

## Tests

Run the JUnit tests:

```bash
mvn test
```

The current tests cover enclosure capacity validation, habitat mismatch validation, safe animal updates inside full enclosures, and ticket revenue report date validation.

## Console Database Features

The console menu includes database-backed options for:

```text
16) Animal Database Management
17) Assign Worker
18) Schedule Feeding
19) Complete Feeding
20) Add Medical Record
21) Show Medical History
22) Ticket Revenue Report
```

These options use the `services` package, which keeps business rules separate from menu input and JDBC code.
