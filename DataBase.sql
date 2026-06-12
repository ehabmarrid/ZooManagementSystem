CREATE DATABASE IF NOT EXISTS zoo_database;

USE zoo_database;

CREATE TABLE IF NOT EXISTS Zoo (
    zooID INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Enclosure (
    enclosureID INT PRIMARY KEY AUTO_INCREMENT,
    zooID INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    habitatType VARCHAR(50) NOT NULL,
    capacity INT NOT NULL,
    CONSTRAINT chk_enclosure_capacity CHECK (capacity > 0),
    FOREIGN KEY (zooID) REFERENCES Zoo(zooID)
);

CREATE TABLE IF NOT EXISTS Visitor (
    visitorID INT PRIMARY KEY,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    birthDay DATE,
    phoneNumber VARCHAR(15),
    numberOfTickets INT DEFAULT 0,
    joinMailinglist BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS Worker (
    workerID INT PRIMARY KEY,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    birthDay DATE,
    phoneNumber VARCHAR(15),
    role VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Animal (
    animalID INT PRIMARY KEY AUTO_INCREMENT,
    species VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    healthStatus VARCHAR(50) NOT NULL,
    habitatType VARCHAR(50) NOT NULL,
    zooID INT NOT NULL,
    enclosureID INT,
    CONSTRAINT chk_animal_age CHECK (age >= 0),
    FOREIGN KEY (zooID) REFERENCES Zoo(zooID),
    FOREIGN KEY (enclosureID) REFERENCES Enclosure(enclosureID)
);

CREATE TABLE IF NOT EXISTS WorkerAssignment (
    assignmentID INT PRIMARY KEY AUTO_INCREMENT,
    workerID INT NOT NULL,
    animalID INT,
    enclosureID INT,
    responsibility VARCHAR(100) NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE,
    FOREIGN KEY (workerID) REFERENCES Worker(workerID),
    FOREIGN KEY (animalID) REFERENCES Animal(animalID),
    FOREIGN KEY (enclosureID) REFERENCES Enclosure(enclosureID)
);

CREATE TABLE IF NOT EXISTS FeedingSchedule (
    feedingID INT PRIMARY KEY AUTO_INCREMENT,
    animalID INT,
    enclosureID INT,
    scheduledAt DATETIME NOT NULL,
    foodType VARCHAR(100) NOT NULL,
    amount VARCHAR(50) NOT NULL,
    completed BOOLEAN DEFAULT FALSE,
    completedAt DATETIME,
    completedByWorkerID INT,
    FOREIGN KEY (animalID) REFERENCES Animal(animalID),
    FOREIGN KEY (enclosureID) REFERENCES Enclosure(enclosureID),
    FOREIGN KEY (completedByWorkerID) REFERENCES Worker(workerID)
);

CREATE TABLE IF NOT EXISTS MedicalRecord (
    recordID INT PRIMARY KEY AUTO_INCREMENT,
    animalID INT NOT NULL,
    visitDate DATE NOT NULL,
    diagnosis VARCHAR(255) NOT NULL,
    treatment VARCHAR(255),
    veterinarian VARCHAR(100),
    notes TEXT,
    FOREIGN KEY (animalID) REFERENCES Animal(animalID)
);

CREATE TABLE IF NOT EXISTS Ticket (
    ticketID INT PRIMARY KEY AUTO_INCREMENT,
    visitorID INT NOT NULL,
    dateOfVisit DATE NOT NULL,
    ticketType VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (visitorID) REFERENCES Visitor(visitorID)
);

CREATE INDEX idx_animal_enclosure ON Animal(enclosureID);
CREATE INDEX idx_ticket_date ON Ticket(dateOfVisit);
CREATE INDEX idx_feeding_completed ON FeedingSchedule(completed, scheduledAt);
CREATE INDEX idx_medical_animal ON MedicalRecord(animalID, visitDate);

SELECT * FROM Zoo;
SELECT * FROM Enclosure;
SELECT * FROM Visitor;
SELECT * FROM Worker;
SELECT * FROM Animal;
SELECT * FROM WorkerAssignment;
SELECT * FROM FeedingSchedule;
SELECT * FROM MedicalRecord;
SELECT * FROM Ticket;
