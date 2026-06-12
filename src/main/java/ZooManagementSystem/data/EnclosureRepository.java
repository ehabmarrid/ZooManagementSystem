package ZooManagementSystem.data;

import ZooManagementSystem.models.Enclosure;

import java.sql.*;
import java.util.Optional;

public class EnclosureRepository {
    public int create(Enclosure enclosure) throws SQLException {
        String sql = "INSERT INTO Enclosure(zooID, name, habitatType, capacity) VALUES(?,?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, enclosure.getZooID());
            statement.setString(2, enclosure.getName());
            statement.setString(3, enclosure.getHabitatType());
            statement.setInt(4, enclosure.getCapacity());
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : 0;
            }
        }
    }

    public Optional<Enclosure> findById(int enclosureID) throws SQLException {
        String sql = "SELECT enclosureID, zooID, name, habitatType, capacity FROM Enclosure WHERE enclosureID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, enclosureID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new Enclosure(
                            resultSet.getInt("enclosureID"),
                            resultSet.getInt("zooID"),
                            resultSet.getString("name"),
                            resultSet.getString("habitatType"),
                            resultSet.getInt("capacity")
                    ));
                }
                return Optional.empty();
            }
        }
    }
}
