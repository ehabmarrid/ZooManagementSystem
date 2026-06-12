package ZooManagementSystem.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RevenueReport {
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final int ticketsSold;
    private final BigDecimal totalRevenue;

    public RevenueReport(LocalDate fromDate, LocalDate toDate, int ticketsSold, BigDecimal totalRevenue) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.ticketsSold = ticketsSold;
        this.totalRevenue = totalRevenue;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }
}
