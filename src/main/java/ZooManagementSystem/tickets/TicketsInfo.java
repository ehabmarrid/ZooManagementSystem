package ZooManagementSystem.tickets;

public class TicketsInfo {
    private int TicketNumber;
    private int TicketHolderID;
    private TicketType ticket_type;

    private boolean CanBeCancelled;
    private String PurchasedDate;

    private String ETA;
    private int price;

    public TicketsInfo(int ticketNumber, int ticketHolderID, TicketType ticket_type,String PurchasedDate ,int price ) {
        TicketNumber = ticketNumber;
        TicketHolderID = ticketHolderID;
        this.ticket_type = ticket_type;
        this.PurchasedDate=PurchasedDate;
        this.price = price;
    }

    public int getTicketNumber() {
        return TicketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        TicketNumber = ticketNumber;
    }

    public int getTicketHolderID() {
        return TicketHolderID;
    }

    public void setTicketHolderID(int ticketHolderID) {
        TicketHolderID = ticketHolderID;
    }

    public TicketType getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(TicketType ticket_type) {
        this.ticket_type = ticket_type;
    }

    public String getPurchasedDate() {
        return PurchasedDate;
    }

    public int getPrice() {
        return price;
    }

    public void setCanBeCancelled(boolean canBeCancelled) {
        CanBeCancelled = canBeCancelled;
    }

    public boolean isCanBeCancelled() {
        return CanBeCancelled;
    }

    public void setETA(String ETA) {
        this.ETA = ETA;
    }

    public String getETA() {
        return ETA;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TicketsInfo{" +
                "TicketNumber=" + TicketNumber +
                ", TicketHolderID=" + TicketHolderID +
                ", ticket_type=" + ticket_type +
                ", CanBeCancelled=" + CanBeCancelled +
                ", PurchasedDate='" + PurchasedDate + '\'' +
                ", ETA='" + ETA + '\'' +
                ", price=" + price +
                '}';
    }
}
