package ZooManagementSystem;

public class Tiger extends CarnivorousAnimal{

        private final String Tiger_Noise = "roar";

        private final double MeatCalcMale = 0.02;
        private final double MeatCalcFemale= 0.03;
        public Tiger(){
            super();
        }

        public Tiger(String name,int age, double weight,Gender gender){
            super(name,age,weight,gender);
        }

        public int howMuchMeatDoesTigerEat(){
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


        public String makeNoise(){
            return Tiger_Noise;
        }
        
        public double feed() {
        	return howMuchMeatDoesTigerEat();
        }

}
