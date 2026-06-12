package ZooManagementSystem.data;

import ZooManagementSystem.models.MedicalRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordRepository {
    public int create(MedicalRecord record) throws SQLException {
        String sql = "INSERT INTO MedicalRecord(animalID, visitDate, diagnosis, treatment, veterinarian, notes) VALUES(?,?,?,?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, record.getAnimalID());
            statement.setDate(2, Date.valueOf(record.getVisitDate()));
            statement.setString(3, record.getDiagnosis());
            statement.setString(4, record.getTreatment());
            statement.setString(5, record.getVeterinarian());
            statement.setString(6, record.getNotes());
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : 0;
            }
        }
    }

    public List<MedicalRecord> findByAnimalId(int animalID) throws SQLException {
        String sql = "SELECT recordID, animalID, visitDate, diagnosis, treatment, veterinarian, notes FROM MedicalRecord WHERE animalID = ? ORDER BY visitDate DESC";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, animalID);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<MedicalRecord> records = new ArrayList<>();
                while (resultSet.next()) {
                    records.add(new MedicalRecord(
                            resultSet.getInt("recordID"),
                            resultSet.getInt("animalID"),
                            resultSet.getDate("visitDate").toLocalDate(),
                            resultSet.getString("diagnosis"),
                            resultSet.getString("treatment"),
                            resultSet.getString("veterinarian"),
                            resultSet.getString("notes")
                    ));
                }
                return records;
            }
        }
    }
}
