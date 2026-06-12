package ZooManagementSystem.data;

import ZooManagementSystem.models.RevenueReport;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;

public class TicketReportRepository {
    public RevenueReport revenueBetween(LocalDate fromDate, LocalDate toDate) throws SQLException {
        String sql = "SELECT COUNT(*) AS ticketsSold, COALESCE(SUM(price), 0) AS totalRevenue FROM Ticket WHERE dateOfVisit BETWEEN ? AND ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(fromDate));
            statement.setDate(2, Date.valueOf(toDate));
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new RevenueReport(
                            fromDate,
                            toDate,
                            resultSet.getInt("ticketsSold"),
                            resultSet.getBigDecimal("totalRevenue")
                    );
                }
                return new RevenueReport(fromDate, toDate, 0, BigDecimal.ZERO);
            }
        }
    }
}
