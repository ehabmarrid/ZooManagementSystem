package ZooManagementSystem.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AnimalManagment {
    public void addAnimal(int animalID, String species, String name, int age, String healthStatus, String habitatType, int zooID) {
        try (Connection conn = DatabaseConnection.getConnection()){
            String query = "INSERT INTO Animal(animalID, species, name, age, healthStatus, habitatType, zooID) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, animalID);
            pstmt.setString(2, species);
            pstmt.setString(3, name);
            pstmt.setInt(4, age);
            pstmt.setString(5, healthStatus);
            pstmt.setString(6, habitatType);
            pstmt.setInt(7, zooID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
