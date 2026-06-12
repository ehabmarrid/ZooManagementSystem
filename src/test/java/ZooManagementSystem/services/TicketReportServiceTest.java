package ZooManagementSystem.services;

import ZooManagementSystem.data.TicketReportRepository;
import ZooManagementSystem.models.RevenueReport;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TicketReportServiceTest {
    @Test
    void rejectsDateRangeWhenStartIsAfterEnd() {
        TicketReportService service = new TicketReportService(new FakeTicketReportRepository());

        assertThrows(IllegalArgumentException.class,
                () -> service.revenueBetween(LocalDate.of(2026, 2, 1), LocalDate.of(2026, 1, 1)));
    }

    @Test
    void returnsRevenueReportForValidRange() throws SQLException {
        TicketReportService service = new TicketReportService(new FakeTicketReportRepository());

        RevenueReport report = service.revenueBetween(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 31));

        assertEquals(2, report.getTicketsSold());
        assertEquals(new BigDecimal("125.00"), report.getTotalRevenue());
    }

    private static class FakeTicketReportRepository extends TicketReportRepository {
        @Override
        public RevenueReport revenueBetween(LocalDate fromDate, LocalDate toDate) {
            return new RevenueReport(fromDate, toDate, 2, new BigDecimal("125.00"));
        }
    }
}
