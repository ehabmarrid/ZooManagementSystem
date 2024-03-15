package ZooManagementSystem;


import java.util.Arrays;
import java.util.stream.Collectors;

public class AquariumFish {

	private int age;
	private double length;
	private Colour[] colours;
	private Pattern pattern;

	private int happiness;

	private static int LifeSpan= 25;

	private final String Fish_Noise = "blob";

	
	
	//Default Constructor
	public AquariumFish() {
		age = 0;
		length = 0;
		
	}
	//Constructor
	public AquariumFish(int age, double length) {
		this.age = age;
		this.length = length;
		this.happiness=100;
	}
	public static void ZooDetails() {
		
	}

	public double HowMuchMealFish(){
		if (this.age<3)
			return 3;
		else
			return length + 3;

	}
	
	public double feed() {
		return HowMuchMealFish();
	}
	
	//Returns an integer array with the length of (10) each value is UNIQUE (between 0-9).
	public static int[] randomNumberArray() {
		int[] randomNumArray = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
		for(int i = 0; i < randomNumArray.length; i++) {
			int num = (int)(Math.random()*10);
			for(int j = 0; j < randomNumArray.length-1; j++) {
				while(num == randomNumArray[j]) {
					num = (int)(Math.random()*10);
					j=0;
				}
			}
			randomNumArray[i] = num;
			
		}
		return randomNumArray;
	}
	
	
	//Returns an Enum of a RANDOM array with UNIQUE colour values, each array is formed with the length between 1 to 10 (including).
	//the array indicates the colour(s) of the fish(object) it belongs to.

	public static Colour[] randomColour() {
		Colour[] allColours = Colour.values();
		Colour[] singularFishColours = new Colour[(int)(Math.random()*10)+1];
		int[] randomUniqueNumberArray = randomNumberArray();
		for(int i = 0; i<singularFishColours.length; i++) {
			singularFishColours[i] = allColours[randomUniqueNumberArray[i]];
			Zoo.numOfFishColours[randomUniqueNumberArray[i]]++;
		}
		
		 return singularFishColours;
	}

	//Returns a random enum value of pattern.
	public static Pattern randomPattern() {
		Pattern[] ranPat = Pattern.values();
		return ranPat[(int)(Math.random()*ranPat.length)];
	}


	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}
	public int getAge() {
		return age;
	}
	public Pattern getPattern() {
		return pattern;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}


	//Returns a String of the Fish's colours.
	public String getColours() {
		String x = "";
		for(int i = 0; i < colours.length; i++) {
			x+=colours[i]+  " ";
		}
		return x;
	}

	// Using stream and distinct to remove duplicates when we want to show all the fishes
	public static String removeDuplicateWords(String inputString) {
		String[] words = inputString.split("\\s+");
		String result = Arrays.stream(words)
				.distinct()
				.collect(Collectors.joining(" "));

		return result;
	}

	public void setColours(Colour[] colours) {
		this.colours = colours;
	}

	public String makeNoise(){
		return Fish_Noise;
	}


	public String toString() {
		return "Fish.";
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
