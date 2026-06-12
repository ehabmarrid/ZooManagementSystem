package ZooManagementSystem.data;

import ZooManagementSystem.models.AnimalRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnimalRepository {
    public int create(AnimalRecord animal) throws SQLException {
        if (animal.getAnimalID() > 0) {
            String sql = "INSERT INTO Animal(animalID, species, name, age, healthStatus, habitatType, zooID, enclosureID) VALUES(?,?,?,?,?,?,?,?)";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                bindAnimal(statement, animal, true);
                statement.executeUpdate();
                return animal.getAnimalID();
            }
        }

        String sql = "INSERT INTO Animal(species, name, age, healthStatus, habitatType, zooID, enclosureID) VALUES(?,?,?,?,?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bindAnimal(statement, animal, false);
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
            return 0;
        }
    }

    public Optional<AnimalRecord> findById(int animalID) throws SQLException {
        String sql = "SELECT animalID, species, name, age, healthStatus, habitatType, zooID, enclosureID FROM Animal WHERE animalID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, animalID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapAnimal(resultSet));
                }
                return Optional.empty();
            }
        }
    }

    public List<AnimalRecord> findAll() throws SQLException {
        String sql = "SELECT animalID, species, name, age, healthStatus, habitatType, zooID, enclosureID FROM Animal ORDER BY animalID";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<AnimalRecord> animals = new ArrayList<>();
            while (resultSet.next()) {
                animals.add(mapAnimal(resultSet));
            }
            return animals;
        }
    }

    public boolean update(AnimalRecord animal) throws SQLException {
        String sql = "UPDATE Animal SET species = ?, name = ?, age = ?, healthStatus = ?, habitatType = ?, zooID = ?, enclosureID = ? WHERE animalID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, animal.getSpecies());
            statement.setString(2, animal.getName());
            statement.setInt(3, animal.getAge());
            statement.setString(4, animal.getHealthStatus());
            statement.setString(5, animal.getHabitatType());
            statement.setInt(6, animal.getZooID());
            setNullableInt(statement, 7, animal.getEnclosureID());
            statement.setInt(8, animal.getAnimalID());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean delete(int animalID) throws SQLException {
        String sql = "DELETE FROM Animal WHERE animalID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, animalID);
            return statement.executeUpdate() > 0;
        }
    }

    public int countByEnclosure(int enclosureID) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Animal WHERE enclosureID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, enclosureID);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? resultSet.getInt(1) : 0;
            }
        }
    }

    private void bindAnimal(PreparedStatement statement, AnimalRecord animal, boolean includeID) throws SQLException {
        int index = 1;
        if (includeID) {
            statement.setInt(index++, animal.getAnimalID());
        }
        statement.setString(index++, animal.getSpecies());
        statement.setString(index++, animal.getName());
        statement.setInt(index++, animal.getAge());
        statement.setString(index++, animal.getHealthStatus());
        statement.setString(index++, animal.getHabitatType());
        statement.setInt(index++, animal.getZooID());
        setNullableInt(statement, index, animal.getEnclosureID());
    }

    private AnimalRecord mapAnimal(ResultSet resultSet) throws SQLException {
        int enclosureID = resultSet.getInt("enclosureID");
        Integer enclosure = resultSet.wasNull() ? null : enclosureID;
        return new AnimalRecord(
                resultSet.getInt("animalID"),
                resultSet.getString("species"),
                resultSet.getString("name"),
                resultSet.getInt("age"),
                resultSet.getString("healthStatus"),
                resultSet.getString("habitatType"),
                resultSet.getInt("zooID"),
                enclosure
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
