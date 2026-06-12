package ZooManagementSystem.promotions;

public class VisitorPromo implements Observer {
    private String name;
    private int pnumber;

    public VisitorPromo(String name, int pnumber) {
        this.name = name;
        this.pnumber = pnumber;
    }

    @Override
    public void update(String message) {
        System.out.println("Hello " + name + ", new promotion: " + message + "\n Sent to "+ pnumber);
    }
}