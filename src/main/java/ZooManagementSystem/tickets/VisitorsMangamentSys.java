package ZooManagementSystem.tickets;

import ZooManagementSystem.people.Visitor;
import ZooManagementSystem.promotions.PromotionManager;
import ZooManagementSystem.promotions.VisitorPromo;
import ZooManagementSystem.zoo.Zoo;

import java.util.List;
import java.util.Scanner;

public class VisitorsMangamentSys {
    TicketsImp VMSFunc = new TicketsImp();

    static PromotionManager promotionManager;

    private TicketsInfo[] UsedTickets;
    private int numberOfUsedTickets;
    private static int choice;
    public VisitorsMangamentSys(){
        promotionManager= new PromotionManager();
        UsedTickets = new TicketsInfo[1];
        numberOfUsedTickets=0;
        Scanner input= new Scanner(System.in);
        System.out.println("\n1)Purchase Ticket\n"
                + "2)Ticket Cancellation\n"
                + "3)Search Ticket\n"
                + "4)Print Information Ticket\n"
                + "5)Visitor Entrance\n"
                + "6)Exit\n"
        );
        System.out.println("Enter your option:");
        choice = input.nextInt();
        while(choice!=6) {
            switch(choice) {
                case 1:
                    PrintAllTickets();
                    VMSFunc.BuyTicket();
                    break;
                case 2:
                    VMSFunc.CancelTicket();
                    break;
                case 3:
                    System.out.println(VMSFunc.SearchTicket());
                    break;
                case 4:
                    VMSFunc.PrintInfo();
                    break;
                case 5:
                    UpdateUsedTickets(VMSFunc.EnterPark());
                    break;
                case 6:
                    System.out.println(VMSFunc.ExitSys());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + input);
            }
            int discount = (int)(Math.random() * 20 + 1);
            promotionManager.setPromotion("\n"+discount+"% off on weekend tickets!");
            System.out.println("\n1)Purchase Ticket\n"
                    + "2)Ticket Cancellation\n"
                    + "3)Search Ticket\n"
                    + "4)Print Information Ticket\n"
                    + "5)Visitor Entrance\n"
                    + "6)Exit\n"
            );
            System.out.println("Enter your option:");
            choice=input.nextInt();
        }
    }

    public void PrintAllTickets(){
        List<TicketType> result2 = List.of(TicketType.values());
        for (int i=0;i< result2.size();i++){
            System.out.println(i+")"+result2.get(i)+"   "+result2.get(i).getValue()+" nis");
        }
    }

    public void UpdateUsedTickets(TicketsInfo ticket) {
        if (numberOfUsedTickets == UsedTickets.length) {
            TicketsInfo[] tempTicketsArray = new TicketsInfo[numberOfUsedTickets * 2];
            System.arraycopy(UsedTickets, 0, tempTicketsArray, 0, numberOfUsedTickets);
            tempTicketsArray[tempTicketsArray.length - 1] = ticket;
            numberOfUsedTickets++;
            UsedTickets = tempTicketsArray;
        } else {
            UsedTickets[numberOfUsedTickets] = ticket;
            numberOfUsedTickets++;
        }
    }

    public static void addNewVisitor(Visitor newVisitor)
    {
        if(newVisitor.isJoinMailinglist()) {
            VisitorPromo temp = new VisitorPromo(newVisitor.getFirstName(),newVisitor.getPhoneNumber());
            promotionManager.addObserver(temp);
        }
        Zoo.addVisitor(newVisitor);
    }
}
