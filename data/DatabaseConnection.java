package ZooManagementSystem.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/zoo_database";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                getConfigValue("zoo.db.url", "ZOO_DB_URL", DEFAULT_URL),
                getConfigValue("zoo.db.user", "ZOO_DB_USER", "root"),
                getConfigValue("zoo.db.password", "ZOO_DB_PASSWORD", "")
        );
    }

    private static String getConfigValue(String propertyName, String environmentName, String defaultValue) {
        String propertyValue = System.getProperty(propertyName);
        if (propertyValue != null && !propertyValue.isBlank()) {
            return propertyValue;
        }
        String environmentValue = System.getenv(environmentName);
        if (environmentValue != null && !environmentValue.isBlank()) {
            return environmentValue;
        }
        return defaultValue;
    }
}
