package ZooManagementSystem;


import java.util.List;

public class Penguin {
	private double height;
	private int age;
	private String name;

	private static int LastSortWayused=2; // Defult is by height

	private static int LifeSpan= 6;

	private int happiness ;
	private final String P_noise;

	//Constructor
	public Penguin(String name, int age, double height) {
		this.name = name;
		this.age = age;
		this.height = height;
		this.P_noise= "squack";
		this.happiness=100;
	}
	
	//Function to Sort the list of the penguies by Hieght
	public static List<Penguin> SortByHeight(List<Penguin> arr) {
		Penguin temp;
		for(int i = 0; i<arr.size()-1 && arr.get(i) != null; i++) {
			for(int j = i+1; j < arr.size() && arr.get(j) != null; j++) {
				if (arr.get(i).height <= arr.get(j).height) {
					temp= arr.get(i);
					arr.set(i,arr.get(j));
					arr.set(j,temp);
				}
			}
		}
		return arr;
	}

	public static List<Penguin> SortByAge(List<Penguin> arr) {
		Penguin temp;
		for(int i = 0; i<arr.size()-1 && arr.get(i) != null; i++) {
			for(int j = i+1; j < arr.size() && arr.get(j) != null; j++) {
				if (arr.get(i).age > arr.get(j).age) {
					temp= arr.get(i);
					arr.set(i,arr.get(j));
					arr.set(j,temp);
				}
			}
		}
		return arr;
	}
	public static List<Penguin> SortByName(List<Penguin> arr) {
		Penguin temp;
		for(int i = 0; i<arr.size()-1 && arr.get(i) != null; i++) {
			for(int j = i+1; j < arr.size() && arr.get(j) != null; j++) {
				if (arr.get(j).name.compareTo(arr.get(i).name) > 0 ) {
					temp= arr.get(i);
					arr.set(i,arr.get(j));
					arr.set(j,temp);
				}
			}
		}
		return arr;
	}

	public static int getLastSortWayused() {
		return LastSortWayused;
	}

	public static void setLastSortWayused(int lastSortWayused) {
		LastSortWayused = lastSortWayused;
	}

	// Deleting a fish after it was eaten by a penguin
	public AquariumFish[] Feed_Penguins(AquariumFish[] fishes){
		AquariumFish[] temp= new AquariumFish[fishes.length-1];
		int i=0;
		while(fishes[i+1]!=null){
			temp[i]=fishes[i];
			i++;
		}
		// Removing from the aquarium the last fish that was eaten
		AquariumFish Fish_to_eat = fishes[i];
		if(Fish_to_eat!=null)
			System.out.println("Successfully deleted " + Fish_to_eat);
		return temp;
	}
	
	public double feed() {
		return 1.0;
	}

	public String makeNoise(){
		return P_noise;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHappiness() {
		return happiness;
	}

	public void setHappiness(int happiness) {
		this.happiness = happiness;
	}
	public boolean ageOneYear(){
		this.age+=1;
		return this.age <= LifeSpan;
	}
}

