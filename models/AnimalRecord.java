package ZooManagementSystem.models;

public class AnimalRecord {
    private final int animalID;
    private final String species;
    private final String name;
    private final int age;
    private final String healthStatus;
    private final String habitatType;
    private final int zooID;
    private final Integer enclosureID;

    public AnimalRecord(int animalID, String species, String name, int age, String healthStatus, String habitatType, int zooID, Integer enclosureID) {
        this.animalID = animalID;
        this.species = species;
        this.name = name;
        this.age = age;
        this.healthStatus = healthStatus;
        this.habitatType = habitatType;
        this.zooID = zooID;
        this.enclosureID = enclosureID;
    }

    public int getAnimalID() {
        return animalID;
    }

    public String getSpecies() {
        return species;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public String getHabitatType() {
        return habitatType;
    }

    public int getZooID() {
        return zooID;
    }

    public Integer getEnclosureID() {
        return enclosureID;
    }
}
