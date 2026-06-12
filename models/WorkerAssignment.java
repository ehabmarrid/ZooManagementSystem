package ZooManagementSystem.models;

import java.time.LocalDate;

public class WorkerAssignment {
    private final int assignmentID;
    private final int workerID;
    private final Integer animalID;
    private final Integer enclosureID;
    private final String responsibility;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public WorkerAssignment(int assignmentID, int workerID, Integer animalID, Integer enclosureID, String responsibility, LocalDate startDate, LocalDate endDate) {
        this.assignmentID = assignmentID;
        this.workerID = workerID;
        this.animalID = animalID;
        this.enclosureID = enclosureID;
        this.responsibility = responsibility;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public int getWorkerID() {
        return workerID;
    }

    public Integer getAnimalID() {
        return animalID;
    }

    public Integer getEnclosureID() {
        return enclosureID;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
