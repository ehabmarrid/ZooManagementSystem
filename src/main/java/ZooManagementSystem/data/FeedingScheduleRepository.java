package ZooManagementSystem.data;

import ZooManagementSystem.models.FeedingTask;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FeedingScheduleRepository {
    public int create(FeedingTask task) throws SQLException {
        String sql = "INSERT INTO FeedingSchedule(animalID, enclosureID, scheduledAt, foodType, amount, completed, completedAt, completedByWorkerID) VALUES(?,?,?,?,?,?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setNullableInt(statement, 1, task.getAnimalID());
            setNullableInt(statement, 2, task.getEnclosureID());
            statement.setTimestamp(3, Timestamp.valueOf(task.getScheduledAt()));
            statement.setString(4, task.getFoodType());
            statement.setString(5, task.getAmount());
            statement.setBoolean(6, task.isCompleted());
            setNullableTimestamp(statement, 7, task.getCompletedAt());
            setNullableInt(statement, 8, task.getCompletedByWorkerID());
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : 0;
            }
        }
    }

    public boolean markCompleted(int feedingID, int workerID, LocalDateTime completedAt) throws SQLException {
        String sql = "UPDATE FeedingSchedule SET completed = TRUE, completedAt = ?, completedByWorkerID = ? WHERE feedingID = ? AND completed = FALSE";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setTimestamp(1, Timestamp.valueOf(completedAt));
            statement.setInt(2, workerID);
            statement.setInt(3, feedingID);
            return statement.executeUpdate() > 0;
        }
    }

    public List<FeedingTask> findOpenTasks() throws SQLException {
        String sql = "SELECT feedingID, animalID, enclosureID, scheduledAt, foodType, amount, completed, completedAt, completedByWorkerID FROM FeedingSchedule WHERE completed = FALSE ORDER BY scheduledAt";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<FeedingTask> tasks = new ArrayList<>();
            while (resultSet.next()) {
                tasks.add(mapTask(resultSet));
            }
            return tasks;
        }
    }

    private FeedingTask mapTask(ResultSet resultSet) throws SQLException {
        int animalID = resultSet.getInt("animalID");
        Integer animal = resultSet.wasNull() ? null : animalID;
        int enclosureID = resultSet.getInt("enclosureID");
        Integer enclosure = resultSet.wasNull() ? null : enclosureID;
        Timestamp completedAt = resultSet.getTimestamp("completedAt");
        int workerID = resultSet.getInt("completedByWorkerID");
        Integer worker = resultSet.wasNull() ? null : workerID;
        return new FeedingTask(
                resultSet.getInt("feedingID"),
                animal,
                enclosure,
                resultSet.getTimestamp("scheduledAt").toLocalDateTime(),
                resultSet.getString("foodType"),
                resultSet.getString("amount"),
                resultSet.getBoolean("completed"),
                completedAt == null ? null : completedAt.toLocalDateTime(),
                worker
        );
    }

    private void setNullableInt(PreparedStatement statement, int index, Integer value) throws SQLException {
        if (value == null) {
            statement.setNull(index, Types.INTEGER);
        } else {
            statement.setInt(index, value);
        }
    }

    private void setNullableTimestamp(PreparedStatement statement, int index, LocalDateTime value) throws SQLException {
        if (value == null) {
            statement.setNull(index, Types.TIMESTAMP);
        } else {
            statement.setTimestamp(index, Timestamp.valueOf(value));
        }
    }
}
