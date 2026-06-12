package ZooManagementSystem.services;

import ZooManagementSystem.data.MedicalRecordRepository;
import ZooManagementSystem.models.MedicalRecord;

import java.sql.SQLException;
import java.util.List;

public class MedicalHistoryService {
    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalHistoryService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public int addRecord(MedicalRecord record) throws SQLException {
        if (record.getAnimalID() <= 0 || record.getVisitDate() == null || record.getDiagnosis() == null || record.getDiagnosis().isBlank()) {
            throw new IllegalArgumentException("Animal, visit date, and diagnosis are required.");
        }
        return medicalRecordRepository.create(record);
    }

    public List<MedicalRecord> historyForAnimal(int animalID) throws SQLException {
        if (animalID <= 0) {
            throw new IllegalArgumentException("Animal ID is required.");
        }
        return medicalRecordRepository.findByAnimalId(animalID);
    }
}
