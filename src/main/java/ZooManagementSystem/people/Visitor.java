package ZooManagementSystem.people;

import ZooManagementSystem.tickets.TicketsInfo;

public class Visitor extends Human{

    private int numberOfTickets;
     private TicketsInfo[] ticketenterace;

     private boolean joinMailinglist;

    public Visitor(int ID, String firstName, String lastName , String birthDay , int phoneNumber ){
        super(ID,firstName,lastName,birthDay,phoneNumber);
        this.numberOfTickets=0;
        ticketenterace = new TicketsInfo[10];
    }

    public void UpdateTicket(TicketsInfo ticketenterace){
        this.ticketenterace[numberOfTickets]=ticketenterace;
        numberOfTickets++;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public boolean isJoinMailinglist() {
        return joinMailinglist;
    }

    public void setJoinMailinglist(boolean joinMailinglist) {
        this.joinMailinglist = joinMailinglist;
    }

    public TicketsInfo getTicketenterace(int i) {
        return ticketenterace[i];
    }

    public void setTicketenterace(TicketsInfo ticketenterace , int i) {
        this.ticketenterace[i] = ticketenterace;
    }
}
