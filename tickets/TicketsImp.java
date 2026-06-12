package ZooManagementSystem.tickets;

import ZooManagementSystem.data.DatabaseConnection;
import ZooManagementSystem.people.Visitor;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static ZooManagementSystem.tickets.VisitorsMangamentSys.*;
import static ZooManagementSystem.zoo.Zoo.*;

public class TicketsImp implements Tickets{
    @Override
    public void BuyTicket() {
        Scanner input = new Scanner(System.in);
        int visitorId;
        int requestedticket;
        boolean flag=true;
        System.out.println("Please Enter The id of the Visitor: ");
        visitorId=input.nextInt();
        System.out.println("Please Enter the requested ticket (Enter the number of the ticket) :");
        requestedticket = input.nextInt();
        while (requestedticket<0 || requestedticket>23){
            System.out.println("Enter a correct number!: ");
            requestedticket=input.nextInt();
        }
        TicketType req = TicketType.values()[requestedticket];
        LocalDate todayDate = LocalDate.now();
        DateTimeFormatter FormatedDateSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter FormatedDateobj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String FormatedDate = todayDate.format(FormatedDateobj);
        String SQLDATE = todayDate.format(FormatedDateSQL);
        TicketsInfo temp = new TicketsInfo(1,visitorId,req,FormatedDate,req.getValue());
        if(requestedticket>6) {
            temp.setETA("1-Year");
            temp.setCanBeCancelled(false); // Cancelling the annual ticket will not be possible
            System.out.println("You've Purchase An annual Ticket will be valid to 1 Year from now\n"+
                    "This Ticket by as our policy describe can't be cancelled!\n" +
                    "Thanks you!\n");
        }
        else {
            temp.setETA("One use");
            temp.setCanBeCancelled(true);
            System.out.println("You've Purchase A One use Ticket will be valid for one entrance \n"+
                    "This Ticket by as our policy describe can be cancelled unless you used it!\n" +
                    "Thanks you!\n");
        }
        for(int i=0;i<getNumberOfVisitors();i++){
            if(getVisitor(i).getID()==visitorId) {
                getVisitor(i).UpdateTicket(temp);
                flag=false;
                LocalDate date = LocalDate.now();
                System.out.println("Ticket updated Successfully");
                int idt = (int)(Math.random() * 100 + 1);
                int vid= getVisitor(i).getID();
                String ttype =temp.getTicket_type().toString();
                int tprice = temp.getPrice();
                int numoft= getVisitor(i).getNumberOfTickets();
                try (Connection connection = DatabaseConnection.getConnection()) {
                    System.out.println("Connection to the DataBase established successfully.");
                    String query = "INSERT INTO Ticket (ticketID, visitorID, dateOfVisit, ticketType, price) VALUES (?,?,?,?,?);";
                    String query2 = "UPDATE Visitor SET numberOfTickets =  ? WHERE visitorID=?;";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1,idt);
                    statement.setInt(2,vid);
                    statement.setDate(3, Date.valueOf(date));
                    statement.setString(4,ttype);
                    statement.setInt(5,tprice);
                    int resultSet = statement.executeUpdate();
                    PreparedStatement statement2 = connection.prepareStatement(query2);
                    statement2.setInt(1,numoft);
                    statement2.setInt(2,vid);
                    int resultSet2 = statement2.executeUpdate();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (flag) {
            System.out.println("There is no visitor with this ID, would you like to create a visitor with this ID?\n1) Yes.\nany other number)Exit.\n");
            int choice = input.nextInt();
            if(choice==1) {
                System.out.println("\nPlease enter first name\n");
                input.skip("[\r\n]+"); // To skip the input of the next line char
                String fName = input.nextLine();
                System.out.println("\nPlease enter last name\n");
                String lName = input.nextLine();
                System.out.println("\nPlease enter date of birth (divided by \"/\" DD/MM/YYYY)\n");
                String DOB = input.nextLine();
                System.out.println("\nPlease enter phone number");
                int phoneNum = input.nextInt();
                Visitor newVisitor = new Visitor(visitorId, fName, lName, DOB, phoneNum);
                System.out.println("Do you want to join our promotion mailing list? (write yes Or no) : ");
                input.skip("[\r\n]+"); // To skip the input of the next line char
                String joinmailing = input.nextLine();
                if(joinmailing.equals("yes"))
                    newVisitor.setJoinMailinglist(true);
                else
                    newVisitor.setJoinMailinglist(false);
                newVisitor.UpdateTicket(temp);
                int idt = (int)(Math.random() * 100 + 1);
                try (Connection connection = DatabaseConnection.getConnection()) {
                    System.out.println("Connection to the DataBase established successfully.");
                    String query = "INSERT INTO Ticket (ticketID, visitorID, dateOfVisit, ticketType, price) VALUES (?,?,?,?,?);";
                    String query2 = "INSERT INTO Visitor (visitorID,firstName,lastName,birthDay,phoneNumber,numberOfTickets,joinMailinglist) VALUES (?,?,?,?,?,?,?)";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, idt);
                    statement.setInt(2, newVisitor.getID());
                    statement.setDate(3, Date.valueOf(LocalDate.now()));
                    statement.setString(4, temp.getTicket_type().toString());
                    statement.setInt(5, temp.getPrice());
                    PreparedStatement statement2 = connection.prepareStatement(query2);
                    statement2.setInt(1, newVisitor.getID());
                    statement2.setString(2, newVisitor.getFirstName());
                    statement2.setString(3, newVisitor.getLastName());
                    SimpleDateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date sqlDate = null;
                    try {
                        java.util.Date date = inputFormatter.parse(newVisitor.getBirthDay());
                        sqlDate = new Date(date.getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    statement2.setDate(4, sqlDate);
                    statement2.setString(5, String.valueOf(newVisitor.getPhoneNumber()));
                    statement2.setInt(6, newVisitor.getNumberOfTickets());
                    statement2.setBoolean(7, newVisitor.isJoinMailinglist());
                    int resultSet2 = statement2.executeUpdate();
                    int resultSet = statement.executeUpdate();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("Ticket updated Successfully");
                addNewVisitor(newVisitor);
            } else {
                return;
            }
        }
    }

    @Override
    public  void CancelTicket() {
        Scanner inputproc = new Scanner(System.in);
        int visitorid ;
        int numberofticket;
        boolean flag=true;
        System.out.println("Please Enter the Id of the visitor: ");
        visitorid=inputproc.nextInt();
        for(int i=0;i<getNumberOfVisitors();i++){
            if(visitorid==getVisitor(i).getID()) {
                if (getVisitor(i).getNumberOfTickets() == 0) {
                    System.out.println("There is no ticket to cancel");
                    flag=false;
                    break;
                }
                else {
                    for(int j=0;j<getVisitor(i).getNumberOfTickets();j++){
                        if(getVisitor(i).getTicketenterace(j).isCanBeCancelled())
                            System.out.println((j+1)+") " + getVisitor(i).getTicketenterace(j).toString());
                    }
                    System.out.println("Please enter the number of ticket do you wish to cancel: ");
                    numberofticket=inputproc.nextInt();
                    for(int j=numberofticket-1;j<getVisitor(i).getNumberOfTickets();j++){
                        getVisitor(i).setTicketenterace(getVisitor(i).getTicketenterace(j+1),j);
                    }
                    getVisitor(i).setNumberOfTickets(getVisitor(i).getNumberOfTickets()-1);
                    System.out.println("The Ticket has been cancelled!");
                    flag=false;
                    break;
                }
            }
        }
        if (flag)
            System.out.println("There is no visitor with this ID.");
    }

    @Override
    public String SearchTicket() {
        Scanner inputproc = new Scanner(System.in);
        int visitorid ;
        String tickets="";
        boolean flag=true;
        System.out.println("Please Enter the Id of the visitor: ");
        visitorid=inputproc.nextInt();
        for(int i=0;i<getNumberOfVisitors();i++){
            if(visitorid==getVisitor(i).getID()){
                for(int j=0;j<getVisitor(i).getNumberOfTickets();j++){
                    tickets+=((j+1)+") " + getVisitor(i).getTicketenterace(j).toString());
                    tickets+="\n";
                }
                flag=false;
                break;
            }
        }
        if (flag)
            return "There is no tickets on this ID!";
        return tickets;
    }

    @Override
    public  void PrintInfo() {
        Scanner inputprint = new Scanner(System.in);
        int choice;
        String date;
        System.out.println("How would you like to do the search\n"+
                "1)by ID.\n"+
                "2)by Purchase date.");
        System.out.println("Enter the number of the way requested: ");
        choice = inputprint.nextInt();
        if(choice==1)
            System.out.println(new TicketsImp().SearchTicket());
        else {
            System.out.println("Please enter the date of the Purchase (format it like DD/MM/YYYY): ");
            inputprint.skip("[\r\n]+"); // To skip the input of the next line char
            date = inputprint.nextLine();
            boolean flag = true;
            String tickets = "";
            for (int i = 0; i < getNumberOfVisitors(); i++) {
                for (int j = 0; j < getVisitor(i).getNumberOfTickets(); j++)
                    if (getVisitor(i).getTicketenterace(j).getPurchasedDate().equals(date)) {
                        tickets += ((j + 1) + ") " + getVisitor(i).getTicketenterace(j).toString());
                        tickets += "\n";
                        flag = false;
                    }
            }
            if (flag)
                System.out.println("There is no tickets were Purchased in this Date " +date+"\n");
            else
                System.out.println(tickets);
        }

    }

    @Override
    public TicketsInfo  EnterPark(){
        Scanner inputentrance = new Scanner(System.in);
        int id;
        String tickets="";
        int visitorplace=-1;
        boolean flag=true;
        System.out.println("Please Enter the Visitor ID: ");
        id= inputentrance.nextInt();
        for(int i=0;i<getNumberOfVisitors();i++){
            if(id==getVisitor(i).getID()){
                visitorplace=i;
                for(int j=0;j<getVisitor(i).getNumberOfTickets();j++){
                    if (!getVisitor(i).getTicketenterace(j).getETA().equals("Used")) {
                        flag=false;
                        tickets += ((j + 1) + ") " + getVisitor(i).getTicketenterace(j).toString());
                        tickets += "\n";
                    }
                }
                break;
            }
        }

        if (flag) {
            System.out.println("There is no tickets on this ID!");
        } else{
            TicketsInfo usedticket;
            System.out.println(tickets);
            System.out.println("Enter the number of ticket would you like to use: ");
            int numticket = inputentrance.nextInt();
            if(getVisitor(visitorplace).getTicketenterace(numticket-1).getETA().equals("One use")){
                if(getVisitor(visitorplace).getTicketenterace(numticket-1).isCanBeCancelled()) {
                    getVisitor(visitorplace).getTicketenterace(numticket - 1).setETA("Used");
                    getVisitor(visitorplace).getTicketenterace(numticket-1).setCanBeCancelled(false);
                    usedticket=getVisitor(visitorplace).getTicketenterace(numticket-1);
                    System.out.println("The ticket have been used you can enter the park:)\n");
                    return usedticket; // returning the used tickets for entrance history
                }
            }
            else {
                System.out.println("The ticket is annual you can enter the park!:)\n");
            }
        }
        return null;

    }

    @Override
    public  String ExitSys() {
        return  "Visitor System Exited!\n";
    }
}
