USE zoo_database;

INSERT INTO Zoo (zooID, name, location) VALUES
(1, 'Happy Zoo', '123 Zoo St');

INSERT INTO Enclosure (enclosureID, zooID, name, habitatType, capacity) VALUES
(1, 1, 'Savannah Yard', 'Savannah', 6),
(2, 1, 'Arctic Pool', 'Arctic', 4),
(3, 1, 'Aquarium Hall', 'Sea', 20);

INSERT INTO Visitor (visitorID, firstName, lastName, birthDay, phoneNumber, numberOfTickets, joinMailinglist) VALUES
(1, 'Sample', 'Visitor', '2001-01-15', '0512345678', 1, TRUE),
(2, 'Example', 'Guest', '1995-05-20', '0523456789', 1, FALSE);

INSERT INTO Worker (workerID, firstName, lastName, birthDay, phoneNumber, role) VALUES
(1, 'Sample', 'Worker', '1998-01-18', '0534567891', 'Keeper'),
(2, 'Example', 'Medic', '1991-03-14', '0545678912', 'Veterinarian');

INSERT INTO Animal (animalID, species, name, age, healthStatus, habitatType, zooID, enclosureID) VALUES
(1, 'Lion', 'Simba', 5, 'Healthy', 'Savannah', 1, 1),
(2, 'Penguin', 'Pingu', 3, 'Healthy', 'Arctic', 1, 2),
(3, 'ClownFish', 'Stripe', 1, 'Healthy', 'Sea', 1, 3);

INSERT INTO WorkerAssignment (assignmentID, workerID, animalID, enclosureID, responsibility, startDate, endDate) VALUES
(1, 1, 1, 1, 'Daily feeding and enclosure check', '2026-01-01', NULL),
(2, 2, 2, 2, 'Monthly health check', '2026-01-01', NULL);

INSERT INTO FeedingSchedule (feedingID, animalID, enclosureID, scheduledAt, foodType, amount, completed, completedAt, completedByWorkerID) VALUES
(1, 1, 1, '2026-01-10 09:00:00', 'Meat', '8 kg', TRUE, '2026-01-10 09:20:00', 1),
(2, 2, 2, '2026-01-10 10:00:00', 'Fish', '3 fish', FALSE, NULL, NULL);

INSERT INTO MedicalRecord (recordID, animalID, visitDate, diagnosis, treatment, veterinarian, notes) VALUES
(1, 1, '2026-01-05', 'Routine check', 'No treatment needed', 'Example Medic', 'Animal is active and eating normally'),
(2, 2, '2026-01-06', 'Minor wing irritation', 'Observation and rest', 'Example Medic', 'Follow up in two weeks');

INSERT INTO Ticket (ticketID, visitorID, dateOfVisit, ticketType, price) VALUES
(1, 1, '2026-01-08', 'Adult', 71.00),
(2, 2, '2026-01-09', 'Boy', 54.00);
