package ZooManagementSystem.people;

public class Human {
    private int ID;
    private String FirstName;
    private String LastName;
    private String BirthDay;
    private int PhoneNumber;

    public Human(int ID, String firstName, String lastName, String birthDay, int phoneNumber) {
        this.ID = ID;
        FirstName = firstName;
        LastName = lastName;
        BirthDay = birthDay;
        PhoneNumber = phoneNumber;
    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getBirthDay() {
        return BirthDay;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }
}
