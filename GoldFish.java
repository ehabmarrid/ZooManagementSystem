package ZooManagementSystem;

public class GoldFish extends AquariumFish{
	
	private static Colour colour;
	private Pattern pattern = Pattern.CLEAR;

	private static int LifeSpan= 12;
	
	
	//Constructor,  a random colour will be chosen.
	public GoldFish(int age, double length) {
		super(age, length);
		Colour[] allColours = {Colour.BLACK, Colour.GOLD, Colour.ORANGE, Colour.YELLOW};
		int num = (int)(Math.random()*3)+1;
		colour = allColours[num];
		Zoo.numOfFishColours[num]++;
		
	}
	
	
	public double feed() {
		return 1.0;
	}


	public static Colour getColour() {
		return colour;
	}

	public static void setColour(Colour colour) {
		GoldFish.colour = colour;
	}

	public Pattern getPattern() {
		return pattern;
	}
	
	public String toString() {
		return "GoldFish.";
	}
	
	public String getColours() {
		String x = ""+colour;
		
		return x;
	}

	@Override
	public boolean ageOneYear(){
		this.setAge(this.getAge()+1);
		return this.getAge() <= LifeSpan;
	}

}
