package ZooManagementSystem;

public class CarnivorousAnimal {
    private String name;
    private int age;
    private double weight;
    private Gender gender;

    private int happiness;
    private static int LifeSpan = 15;

    public CarnivorousAnimal(){
        name = "";
        age = 0;
        weight = 0;
    }

    public CarnivorousAnimal(String name, int age, double weight, Gender gender){
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
        this.happiness=100;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean ageOneYear(){
        this.age+=1;
        return this.age <= LifeSpan;
    }
}
