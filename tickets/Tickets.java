package ZooManagementSystem.tickets;

public interface Tickets {
    void BuyTicket();
    void CancelTicket();
    String SearchTicket();
    void PrintInfo();

    TicketsInfo EnterPark();
    String ExitSys();
}
