package ZooManagementSystem.services;

import ZooManagementSystem.data.TicketReportRepository;
import ZooManagementSystem.models.RevenueReport;

import java.sql.SQLException;
import java.time.LocalDate;

public class TicketReportService {
    private final TicketReportRepository ticketReportRepository;

    public TicketReportService(TicketReportRepository ticketReportRepository) {
        this.ticketReportRepository = ticketReportRepository;
    }

    public RevenueReport revenueBetween(LocalDate fromDate, LocalDate toDate) throws SQLException {
        if (fromDate == null || toDate == null) {
            throw new IllegalArgumentException("Report dates are required.");
        }
        if (fromDate.isAfter(toDate)) {
            throw new IllegalArgumentException("Report start date cannot be after end date.");
        }
        return ticketReportRepository.revenueBetween(fromDate, toDate);
    }
}
