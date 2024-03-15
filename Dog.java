package ZooManagementSystem;

public class Dog {
    private String name;

    private int age;

    private double weight;

    private final double MeatCalcMale=0.05;
    private final double MeatCalcFemale=0.03;


    private DogType Type;
    private Gender gender;

    private int happiness;
    private static int LifeSpan = 14;
    private final String DogNoise="BARK";
    public Dog(String name, int age ,double weight, DogType Type ,Gender gender){
        this.name = name;
        this.age = age;
        this.weight=weight;
        this.Type=Type;
        this.gender=gender;
        this.happiness=100;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public int HowMuchDogEat(){
        double meat = (int)(getWeight()*getAge());
        if(getGender() == Gender.Male) {
            meat*=MeatCalcMale;
            return (int)meat;
        }
        else {
            meat*=MeatCalcFemale;
            return (int)meat;
        }
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public DogType getType() {
        return Type;
    }

    public void setType(DogType type) {
        Type = type;
    }

    public String makeNoise(){
        return DogNoise;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean ageOneYear(){
        this.age+=1;
        return this.age <= LifeSpan;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
