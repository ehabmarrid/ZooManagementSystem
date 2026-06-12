package ZooManagementSystem.models;

import java.time.LocalDate;

public class MedicalRecord {
    private final int recordID;
    private final int animalID;
    private final LocalDate visitDate;
    private final String diagnosis;
    private final String treatment;
    private final String veterinarian;
    private final String notes;

    public MedicalRecord(int recordID, int animalID, LocalDate visitDate, String diagnosis, String treatment, String veterinarian, String notes) {
        this.recordID = recordID;
        this.animalID = animalID;
        this.visitDate = visitDate;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.veterinarian = veterinarian;
        this.notes = notes;
    }

    public int getRecordID() {
        return recordID;
    }

    public int getAnimalID() {
        return animalID;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getVeterinarian() {
        return veterinarian;
    }

    public String getNotes() {
        return notes;
    }
}
