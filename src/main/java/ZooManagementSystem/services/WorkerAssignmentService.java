package ZooManagementSystem.services;

import ZooManagementSystem.data.WorkerAssignmentRepository;
import ZooManagementSystem.models.WorkerAssignment;

import java.sql.SQLException;

public class WorkerAssignmentService {
    private final WorkerAssignmentRepository assignmentRepository;

    public WorkerAssignmentService(WorkerAssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public int assignWorker(WorkerAssignment assignment) throws SQLException {
        if (assignment.getWorkerID() <= 0) {
            throw new IllegalArgumentException("Worker ID is required.");
        }
        if (assignment.getAnimalID() == null && assignment.getEnclosureID() == null) {
            throw new IllegalArgumentException("Assign a worker to either an animal or an enclosure.");
        }
        if (assignment.getResponsibility() == null || assignment.getResponsibility().isBlank()) {
            throw new IllegalArgumentException("Responsibility is required.");
        }
        if (assignment.getStartDate() == null) {
            throw new IllegalArgumentException("Start date is required.");
        }
        return assignmentRepository.create(assignment);
    }
}
