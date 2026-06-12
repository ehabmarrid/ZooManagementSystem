package ZooManagementSystem.tickets;

public enum TicketType {
    Adult(71),
    Boy(54),
    PoliceMan(54),
    Student(54),
    Soldier(54),
    Veteran(54),
    DisabilityP(35),
    Boy_Annual(245),
    Adult_Annual(330),
    Student_Annual(245),
    PolicemanPeopleWithDisabilitySoldierSeniorcitizen_Annual(245),
    Pair_Annual(570),
    Pair_1_Annual(690),
    Pair_2_Annual(750),
    Pair_3_Annual(810),
    Pair_4_Annual(860),
    Pair_5_Annual(915),
    Pair_6above_Annual(970),
    Parent1_Annual(515),
    Parent2_Annual(665),
    Parent3_Annual(735),
    Parent4_Annual(755),
    Parent5_Annual(795),
    Parent6above_Annual(830);


    private int value;

    private TicketType(int value){
        this.value=value;
    }

    public int getValue(){
        return this.value;
    }
}
