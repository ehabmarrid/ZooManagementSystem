package ZooManagementSystem.models;

public class Enclosure {
    private final int enclosureID;
    private final int zooID;
    private final String name;
    private final String habitatType;
    private final int capacity;

    public Enclosure(int enclosureID, int zooID, String name, String habitatType, int capacity) {
        this.enclosureID = enclosureID;
        this.zooID = zooID;
        this.name = name;
        this.habitatType = habitatType;
        this.capacity = capacity;
    }

    public int getEnclosureID() {
        return enclosureID;
    }

    public int getZooID() {
        return zooID;
    }

    public String getName() {
        return name;
    }

    public String getHabitatType() {
        return habitatType;
    }

    public int getCapacity() {
        return capacity;
    }
}
