package ZooManagementSystem.data;

import ZooManagementSystem.models.WorkerAssignment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerAssignmentRepository {
    public int create(WorkerAssignment assignment) throws SQLException {
        String sql = "INSERT INTO WorkerAssignment(workerID, animalID, enclosureID, responsibility, startDate, endDate) VALUES(?,?,?,?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, assignment.getWorkerID());
            setNullableInt(statement, 2, assignment.getAnimalID());
            setNullableInt(statement, 3, assignment.getEnclosureID());
            statement.setString(4, assignment.getResponsibility());
            statement.setDate(5, Date.valueOf(assignment.getStartDate()));
            if (assignment.getEndDate() == null) {
                statement.setNull(6, Types.DATE);
            } else {
                statement.setDate(6, Date.valueOf(assignment.getEndDate()));
            }
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : 0;
            }
        }
    }

    public List<WorkerAssignment> findActiveByWorker(int workerID) throws SQLException {
        String sql = "SELECT assignmentID, workerID, animalID, enclosureID, responsibility, startDate, endDate FROM WorkerAssignment WHERE workerID = ? AND endDate IS NULL";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, workerID);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<WorkerAssignment> assignments = new ArrayList<>();
                while (resultSet.next()) {
                    assignments.add(mapAssignment(resultSet));
                }
                return assignments;
            }
        }
    }

    private WorkerAssignment mapAssignment(ResultSet resultSet) throws SQLException {
        int animalID = resultSet.getInt("animalID");
        Integer animal = resultSet.wasNull() ? null : animalID;
        int enclosureID = resultSet.getInt("enclosureID");
        Integer enclosure = resultSet.wasNull() ? null : enclosureID;
        Date endDate = resultSet.getDate("endDate");
        return new WorkerAssignment(
                resultSet.getInt("assignmentID"),
                resultSet.getInt("workerID"),
                animal,
                enclosure,
                resultSet.getString("responsibility"),
                resultSet.getDate("startDate").toLocalDate(),
                endDate == null ? null : endDate.toLocalDate()
        );
    }

    private void setNullableInt(PreparedStatement statement, int index, Integer value) throws SQLException {
        if (value == null) {
            statement.setNull(index, Types.INTEGER);
        } else {
            statement.setInt(index, value);
        }
    }
}
