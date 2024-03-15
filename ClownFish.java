package ZooManagementSystem;

public class ClownFish extends AquariumFish {

	private Pattern pattern = Pattern.STRIPES;
	private Colour[] colour = {Colour.ORANGE, Colour.BLACK, Colour.WHITE};

	private static int LifeSpan= 8;
	public ClownFish(int age, double length) {
		super(age, length);
		Zoo.numOfFishColours[0]++;
		Zoo.numOfFishColours[2]++;
		Zoo.numOfFishColours[4]++;
	}
	
	public double feed() {
		return 2.0;
	}
	public String toString() {
		return "ClownFish.";
	}
	
	public String getColours() {
		String x = "";
		for(int i = 0; i < colour.length; i++) {
			x+=colour[i]+  " ";
		}
		return x;
	}

	@Override
	public Pattern getPattern() {
		return pattern;
	}

	@Override
	public boolean ageOneYear(){
		this.setAge(this.getAge()+1);
		return this.getAge() <= LifeSpan;
	}
}
