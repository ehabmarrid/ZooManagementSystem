package ZooManagementSystem;

public class Lynx extends CarnivorousAnimal{
	
	
	 private final String Lynx_Noise = "RAAAHHH";

	 private final double MeatCalcMale = 1.1;
	 private final double MeatCalcFemale= 1.3;
     public Lynx(){
         super();
     }
     
     public Lynx(String name, int age, double weight, Gender gender) {
    	 super(name,age,weight,gender);
     }
     
     public int howMuchMeatDoesLynxEat() {
    	 double meat = (int)(getWeight()*0.1 + getAge()*2);
    	 if(getGender() == Gender.Male) {
    		 meat *= MeatCalcMale;
    		 return (int)(meat);
    	 }
    	 else {
    		 meat *= MeatCalcFemale;
    		 return (int)(meat);
    	 }
     }

     public String makeNoise() {
    	 return Lynx_Noise;
     }
     
     public double feed() {
    	 return howMuchMeatDoesLynxEat();
     }
     
	
}
