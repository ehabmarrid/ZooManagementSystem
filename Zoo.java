package ZooManagementSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static ZooManagementSystem.Gender.*;


public class Zoo {

	private static final int max_size=1000;
	private static final int max_happiness=100;
	private static String name;
	private static String location;
	static List<Penguin> penguins = new ArrayList<Penguin>(max_size);
	private static Lion[] lions = new Lion[max_size];
	private static Lynx[] lynxes = new Lynx[max_size];

	private static Dog[] dogs = new Dog[max_size];
	private static AquariumFish[] fishes = new AquariumFish[max_size];
	private static Tiger[] tigers = new Tiger[max_size];

	static int[] numOfFishColours= new int[Colour.values().length+1];
	static int numberOfLions;
	static int numberOfPenguins;
	static int numberOfFishes;
	static int numberOfLynxes;
	static int numberOfTigers;

	static int numberOfDogs;

	
	
	
	public Zoo()   {
		this.name = "Zoo";
		this.location = "Tel Aviv-Yaffo";
        Arrays.fill(numOfFishColours, 0);
		// numberOfFishes = 10;
		numberOfPenguins = 3;
		numberOfLions = 4;
		numberOfTigers = 2;
		numberOfDogs = 1;
		numberOfLynxes= 1;

		penguins.add(0,new Penguin("Penny", 10, 200));
		penguins.add(1,new Penguin("Lenny", 10, 198));
		penguins.add(2,new Penguin("Tim", 10, 195));

		lions[0] = new Lion("Bird", 5, 36, Gender.Male);
		lions[1] = new Lion("Hungry", 20, 80, Gender.Male);
		lions[2] = new Lion("Skinny", 10, 40, Gender.Female);
		lions[3] = new Lion("Chunky", 24, 90, Gender.Female);

		tigers[0]= new Tiger("Mask",20,15,Gender.Male);
		tigers[1]= new Tiger("Husk",5,20,Gender.Female);


		lynxes[0]= new Lynx("Timpo",8,15,Male);

		dogs[0]= new Dog("Leo",8,10,DogType.Akita, Male);
	
		
		addFish(10);

	}

	private static String getName() {
		return name;
	}

	private static String getLocation() {
		return location;
	}

	public static int getNumberOfLions() {
		return numberOfLions;
	}

	public static int getNumberOfPenguins() {
		return numberOfPenguins;
	}
	
	public static int getNumberOfTigers() {
		return numberOfTigers;
	}
	
	public static int getNumberOfLynxes() {
		return numberOfLynxes;
	}

	public static int getNumberOfFishes() {
		return numberOfFishes;
	}
	
	public static String zooDetails() {
		String forPrint = "Name of the Zoo: " + name + " \nAddress: " + location + "\nin the Zoo there are: \n"
				+ numberOfLions + " Lions,\n" + numberOfTigers+" Tigers,\n" + numberOfLynxes+" Lynxes,\n" + numberOfDogs +" Dogs,\n" +numberOfPenguins + " Penguins,\n" + numberOfFishes + " Fishes.";

		return forPrint;

	}
	
	public static String printFishes() {
		if(numberOfFishes>0) {
			String Fishes_D="";
			String toPrint = "Number of fishes in the Aquarium: " + numberOfFishes + "\n";
			String All_colors = "The Colors of the Fishes: ";
			for (int i = 0; fishes[i] != null; i++) {
				Fishes_D+= (i+1)+")Type Of Fish: "+ fishes[i].toString()+" Age: "+ fishes[i].getAge() + " Length: " + String.format("%.2f",fishes[i].getLength()) + " Pattern: " + fishes[i].getPattern()+ " Happiness: "+ fishes[i].getHappiness() + ".\n";
				All_colors += fishes[i].getColours() + " ";
			}
			All_colors = AquariumFish.removeDuplicateWords(All_colors);
			return Fishes_D+ toPrint + All_colors;
		}
		else {
			String toPrint = "There is no Fishes in the Aquarium!";
			return toPrint;
		}
	}

	public static String printPenguins() {
		String forPrint = "The penguins in the Zoo are as follows:\n";
		for (Penguin p : penguins){
			forPrint += "Name: " + p.getName() +  " Age: " + p.getAge() +  " Height: "
					+ p.getHeight() + " Happiness: "+ p.getHappiness() + ".\n";
		}
		return forPrint;
	}

	public static String printLions() {
		String forPrint = "The lions in the Zoo are as follows:\n";
		for (int i = 0; lions[i] != null; i++) {
			forPrint += "Name: " + lions[i].getName() + " Age: " + lions[i].getAge() + " Weight: "
					+ lions[i].getWeight() + " Happiness: "+ lions[i].getHappiness() + ".\n";
		}
		return forPrint;
	}

	public static String printTigers(){
		String T_forPrint= "The Tigers in the zoo are:\n";
		for(int i=0 ; tigers[i]!=null;i++){
			T_forPrint+="Name: " + tigers[i].getName() + " Age: " + tigers[i].getAge() + " Weight: "
					+ tigers[i].getWeight() + "Happiness: "+ tigers[i].getHappiness() + ".\n";
		}
		return T_forPrint;
	}

	public static String printLynxes() {
		String forPrint = "The lynxes in the Zoo are as follows:\n";
		for (int i = 0; lynxes[i] != null; i++) {
			forPrint += "Name: " + lynxes[i].getName() + " Age: " + lynxes[i].getAge() + " Weight: "
					+ lynxes[i].getWeight() + " Happiness: "+ lynxes[i].getHappiness() + ".\n";
		}
		return forPrint;
	}

	public static String printDogs(){
		String forPrint = "The dogs in the zoo are as follows:\n";
		for(int i=0; dogs[i]!=null;i++){
			forPrint += "Name: " + dogs[i].getName() + " Age: " + dogs[i].getAge() + " Weight: "
					+ dogs[i].getWeight() +" Gender: "+ dogs[i].getGender() + " Type: "+ dogs[i].getType() + " Happiness: "+ dogs[i].getHappiness() + ".\n";
		}
		return forPrint;
	}

	public static Boolean illegalPenguin(double height_p) {
		return (height_p >= penguins.get(0).getHeight());

	}
	public static List<Penguin> addPenguin(Penguin newPenguin) {
		if (numberOfPenguins == penguins.size()) {
			List<Penguin> tempPenguinsArray = new ArrayList<Penguin>(penguins.size() * 2);
			tempPenguinsArray=penguins;
			tempPenguinsArray.add(numberOfPenguins,newPenguin);
			int sortby = Penguin.getLastSortWayused();
			if(sortby==1) {
				Penguin.SortByName(tempPenguinsArray);
			} else if (sortby==2) {
				Penguin.SortByHeight(tempPenguinsArray);
			} else if (sortby==3) {
				Penguin.SortByAge(tempPenguinsArray);
			}
			numberOfPenguins++;
			return penguins=tempPenguinsArray;
		}
		penguins.add(numberOfPenguins,newPenguin);
		int sortby = Penguin.getLastSortWayused();
		if(sortby==1) {
			Penguin.SortByName(penguins);
		} else if (sortby==2) {
			Penguin.SortByHeight(penguins);
		} else if (sortby==3) {
			Penguin.SortByAge(penguins);
		}
		numberOfPenguins++;
		return penguins;

	}
	
	public static void AddNewFish(int type_f, int age_f, double length_f, Colour[] singularFishColours,Pattern Pattern_F){
		AquariumFish new_fish=null;
			if(type_f == 3) {
				new_fish = new ClownFish(age_f, length_f);
			} else if(type_f == 2) {
				new_fish = new GoldFish(age_f, length_f);
			} else if (type_f == 1) {
				new_fish = new AquariumFish(age_f,length_f);
			}
			if(numberOfFishes+1 >= fishes.length){
            	AquariumFish[] N_Fishes = new AquariumFish[fishes.length*2];
            	for(int i=0 ; i< fishes.length;i++)
                	N_Fishes[i]=fishes[i];
            	N_Fishes[numberOfFishes]=new_fish;
            	numberOfFishes++;
            	fishes=N_Fishes;
            }
            fishes[numberOfFishes] = new_fish;
            numberOfFishes++;
            return;
	}
	//Type of Fish 1 = Reg Fish
	//Type of Fish 2 = GoldFish
	//Type of Fish 3 = ClownFish
	public static AquariumFish[] addFish(int num) {
		if(num+numberOfFishes > fishes.length) {
			AquariumFish[] concatArray = new AquariumFish[num+fishes.length*2];
			System.arraycopy(fishes, 0, concatArray, 0, fishes.length);
			for (int i = numberOfFishes; i < numberOfFishes+num; i++) {
				int ageTemp = (int)(Math.random() * 10 + 1);
				double lengthTemp = Math.random() * 10 + 1;
				int typeOfFish = (int)(Math.random() * 3 + 1);
				if(typeOfFish == 2) {
					concatArray[i] = new GoldFish(ageTemp, lengthTemp);
					continue;
				}
				if(typeOfFish == 3) {
					concatArray[i] = new ClownFish(ageTemp, lengthTemp);
					continue;
				}
				concatArray[i] = new AquariumFish(ageTemp,lengthTemp);
				concatArray[i].setColours(AquariumFish.randomColour());
				concatArray[i].setPattern(AquariumFish.randomPattern());
			}
			numberOfFishes+=num;
			return fishes = concatArray;
		}
		for (int i = numberOfFishes; i < numberOfFishes+num; i++) {
			int ageTemp ;
			double lengthTemp = Math.random() * 10 + 1;
			int typeOfFish = (int)(Math.random() * 3 + 1);
			if(typeOfFish == 2) {
				ageTemp = (int)(Math.random() * 12 + 1);
				fishes[i] = new GoldFish(ageTemp, lengthTemp);
				continue;
			}
			if(typeOfFish == 3) {
				ageTemp= (int)(Math.random() * 8 + 1);
				fishes[i] = new ClownFish(ageTemp, lengthTemp);
				continue;
			}
			ageTemp = (int)(Math.random() * 25 + 1);
			fishes[i] = new AquariumFish(ageTemp,lengthTemp);
			fishes[i].setColours(AquariumFish.randomColour());
			fishes[i].setPattern(AquariumFish.randomPattern());
			
		}
		numberOfFishes+=num;
		return fishes;
	}
	
	
	public static Lion[] addLion(Lion newLion) {
		if(numberOfLions == lions.length) {
		Lion[] tempLionArray = new Lion[numberOfLions*2];
		System.arraycopy(lions, 0, tempLionArray, 0, tempLionArray.length-1);
		tempLionArray[tempLionArray.length-1] = newLion;
		numberOfLions++;
		return lions = tempLionArray;
		}
		else {
			lions[numberOfLions] = newLion;
			numberOfLions++;
			return lions;
		}

	}

	public static Tiger[] addTiger(Tiger newTiger){
		if(numberOfTigers==tigers.length){
			Tiger[] tempTigerArray = new Tiger[numberOfTigers*2];
			System.arraycopy(tigers,0,tempTigerArray,0,tempTigerArray.length-1);
			tempTigerArray[tempTigerArray.length-1] = newTiger;
			numberOfTigers++;
			return tigers=tempTigerArray;
		}
		else{
			tigers[numberOfTigers]=newTiger;
			numberOfTigers++;
			return tigers;
		}
	}

	public static Dog[] addDog(Dog newDog){
		if(numberOfDogs==dogs.length){
			Dog[] tempDogsArray = new Dog[numberOfDogs*2];
			System.arraycopy(dogs,0,tempDogsArray,0,tempDogsArray.length-1);
			tempDogsArray[tempDogsArray.length-1] = newDog;
			numberOfTigers++;
			return dogs=tempDogsArray;
		}
		else{
			dogs[numberOfDogs]=newDog;
			numberOfDogs++;
			return dogs;
		}
	}

	public static Lynx[] addLynx(Lynx newLynx){
		if(numberOfLynxes==lynxes.length){
			Lynx[] tempLynxArray = new Lynx[numberOfLynxes*2];
			System.arraycopy(tigers,0,tempLynxArray,0,tempLynxArray.length-1);
			tempLynxArray[tempLynxArray.length-1] = newLynx;
			numberOfLynxes++;
			return lynxes=tempLynxArray;
		}
		else{
			lynxes[numberOfLynxes]=newLynx;
			numberOfLynxes++;
			return lynxes;
		}
	}
	
	public  int Feed_lions(){
		int food_lions=0;
		for (Lion lion : lions) {
			if(lion==null)
				break;
			food_lions += lion.howMuchMeatDoesLionEat();
			lion.setHappiness(max_happiness);
		}
		return food_lions;
	}

	public int Feed_tigers(){
		int food_tigers=0;
		for(Tiger tiger : tigers){
			if(tiger==null)
				break;
			food_tigers+=tiger.howMuchMeatDoesTigerEat();
			tiger.setHappiness(max_happiness);
		}
		return food_tigers;
	}

	public int Feed_lynxes() {
		int food_lynxes=0;
		for(Lynx lynx : lynxes) {
			if(lynx == null)
				break;
			food_lynxes += lynx.howMuchMeatDoesLynxEat();
			lynx.setHappiness(max_happiness);
		}
		return food_lynxes;
	}

	public int FeedDogs() {
		int food_dogs=0;
		for(Dog dog : dogs) {
			if(dog == null)
				break;
			food_dogs += dog.HowMuchDogEat();
			dog.setHappiness(max_happiness);
		}
		return food_dogs;
	}
	
	public double Feed_Fishes(){
		double food_fishes=0;
		for (AquariumFish fish : fishes) {
			if(fish==null)
				break;
			food_fishes += fish.HowMuchMealFish();
			fish.setHappiness(max_happiness);
		}
		return food_fishes;
	}

	public int Feed_penguins(){
		int count_food_p=0;
		// For Each Penguin a Fish was Eaten
		for (Penguin penguin : penguins){
			if(penguin==null)
				break;
			fishes = penguin.Feed_Penguins(fishes);
			if(numberOfFishes>0) {
				numberOfFishes--;
				count_food_p+=1;
				penguin.setHappiness(max_happiness); // Just the penguin that ate will return to level 100 of Happiness
			}
			else {
				break;
			}
		}
		return count_food_p;
	}



	public String Add_New_Penguin(String name_p,double height_p,int age_p) throws HeightException, AgeException {
		try {
		AgeException ageException = new AgeException();
		ageException.AgeValidator(age_p);
		HeightException heightException = new HeightException();
		heightException.HeightIsIllegal(height_p);
		}
		catch (HeightException e0){
			height_p = Main.heightExceptionPenguin();
			
		}
		catch(AgeException e1) {
			age_p = Main.ageExceptionPenguin();
			
		}
		Penguin newPenguin = new Penguin(name_p,age_p,height_p);
		addPenguin(newPenguin);
		return newPenguin.getName() + " Was added to the flock! :)";
	}

	public void AddNewLion(String name_l,double weight_l , int age_l , int lion_g ){
		Lion newLion = new Lion();
		newLion.setName(name_l);
		newLion.setAge(age_l);
		newLion.setWeight(weight_l);
		if(lion_g==1)
			newLion.setGender(Gender.Male);
		else
			newLion.setGender(Gender.Female);
		addLion(newLion);
	}
	public void AddNewTiger(String name_t,double weight_t , int age_t , int tiger_g ){
		Tiger newTiger = new Tiger();
		newTiger.setName(name_t);
		newTiger.setAge(age_t);
		newTiger.setWeight(age_t);
		if(tiger_g==1)
			newTiger.setGender(Gender.Male);
		else
			newTiger.setGender(Gender.Female);
		addTiger(newTiger);
	}

	public void AddNewLynx(String name_l,double weight_l , int age_l , int gender_l ){
		Lynx newLynx = new Lynx();
		newLynx.setName(name_l);
		newLynx.setAge(age_l);
		newLynx.setWeight(weight_l);
		if(gender_l==1)
			newLynx.setGender(Gender.Male);
		else
			newLynx.setGender(Gender.Female);
		addLynx(newLynx);
	}

	public void AddNewDog(String name_d , int age_d ,double weight_d , int dogtype , int gender){
		Gender DogGender;
		DogType Type;
		if(gender==1){
			DogGender=Gender.Male;
		}
		else {
			DogGender=Gender.Female;
		}
		if(dogtype==1){
			Type=DogType.Akita;
		} else if (dogtype==2) {
			Type=DogType.Bulldog;
		} else if (dogtype==3) {
			Type=DogType.Poodle;
		} else {
			Type=DogType.Terriers;
		}
		Dog newDog= new Dog(name_d,age_d,weight_d,Type,DogGender);
		addDog(newDog);
	}
	
	public String ListentoAllAnimalsinZoo(){
		String out="";
		out+= "1)Lions Noise: "+ lions[0].makeNoise() + " 2)Tigers Noise: " + tigers[0].makeNoise() + " 3)Penguins Noise: " + penguins.get(0).makeNoise() + " 4)Fishes Noise: " + fishes[0].makeNoise() + "5)Lynxes Noise: " + lynxes[0].makeNoise() + "6)Dogs Noise: " + dogs[0].makeNoise();
		return out;
	}
	
	public static String MostPopularFishColour() {
		int index = -1;
		int secondIndex = -1;
		int max = 0;
		int secondMax = 0;
		for(int i = 0; i < numOfFishColours.length; i++) {
			if(numOfFishColours[i] >= max) {
				secondMax = max;
				max = numOfFishColours[i];
				secondIndex = index;
				index = i;
			}
		}
		
		return "The most common colour in the aquarium are:\n"+Colour.values()[index]+" & " + Colour.values()[secondIndex];
		
	}

	public String ageOneYearAll(){
		String PrintAllDead="";
		for(int i=0;i<numberOfLynxes;i++){
			if(!lynxes[i].ageOneYear()){
				PrintAllDead+=lynxes[i].getName()+" is Dead because of his age\n";
				lynxes[i]=null;
				lynxes[i]=lynxes[numberOfLynxes-1];
				numberOfLynxes--;
				i--;
			} else{
				lynxes[i].setHappiness(lynxes[i].getHappiness()-(int)(Math.random() * 20 + 1));
				if(lynxes[i].getHappiness()<=0){
					PrintAllDead+=lynxes[i].getName() +" is Dead because of his Sadness:(\n";
					lynxes[i]=lynxes[numberOfLynxes-1];
					lynxes[numberOfLynxes-1]=null;
					numberOfLynxes--;
					i--;
				}
			}
		}
		for(int i=0;i<numberOfDogs;i++){
			if(!dogs[i].ageOneYear()){
				PrintAllDead+=dogs[i].getName()+" is Dead because of his age\n";
				dogs[i]=null;
				dogs[i]=dogs[numberOfDogs-1];
				numberOfDogs--;
				i--;
			} else{
				dogs[i].setHappiness(dogs[i].getHappiness()-(int)(Math.random() * 20 + 1));
				if(dogs[i].getHappiness()<=0){
					PrintAllDead+=dogs[i].getName() +" is Dead because of his Sadness:(\n";
					dogs[i]=dogs[numberOfDogs-1];
					dogs[numberOfDogs-1]=null;
					numberOfDogs--;
					i--;
				}
			}
		}
		for(int i=0;i<numberOfTigers;i++){
			if(!tigers[i].ageOneYear()){
				PrintAllDead+=tigers[i].getName()+" is Dead because of his age\n";
				tigers[i]=null;
				tigers[i]=tigers[numberOfTigers-1];
				numberOfTigers--;
				i--;
			} else{
				tigers[i].setHappiness(tigers[i].getHappiness()-(int)(Math.random() * 20 + 1));
				if(tigers[i].getHappiness()<=0){
					PrintAllDead+=tigers[i].getName() +" is Dead because of his Sadness:(\n";
					tigers[i]=tigers[numberOfTigers-1];
					tigers[numberOfTigers-1]=null;
					numberOfTigers--;
					i--;
				}
			}
		}
		for(int i=0;i<numberOfLions;i++){
			if(!lions[i].ageOneYear()){
				PrintAllDead+=lions[i].getName()+" is Dead because of his age\n";
				lions[i]=null;
				lions[i]=lions[numberOfLions-1];
				numberOfLions--;
				i--;
			} else{
				lions[i].setHappiness(lions[i].getHappiness()-(int)(Math.random() * 20 + 1));
				if(lions[i].getHappiness()<=0){
					PrintAllDead+=lions[i].getName() +" is Dead because of his Sadness:(\n";
					lions[i]=lions[numberOfLions-1];
					lions[numberOfLions-1]=null;
					numberOfLions--;
					i--;
				}
			}
		}
		for (int i=0;i<numberOfFishes;i++){
			if(!fishes[i].ageOneYear()){
				PrintAllDead+=fishes[i].toString()+" is Dead because of his age\n";
				fishes[i]=fishes[numberOfFishes-1];
				fishes[numberOfFishes-1]=null;
				numberOfFishes--;
				i--;
			} else {
				fishes[i].setHappiness(fishes[i].getHappiness()-(int)(Math.random() * 20 + 1));
				if(fishes[i].getHappiness()<=0){
					PrintAllDead+= fishes[i].toString() +" is Dead because of his Sadness:(\n";
					fishes[i]=fishes[numberOfFishes-1];
					fishes[numberOfFishes-1]=null;
					numberOfFishes--;
					i--;
				}
			}
		}
		for(int i=0;i<numberOfPenguins;i++){
			if(!penguins.get(i).ageOneYear()){
				PrintAllDead+=penguins.get(i).getName()+" is Dead because of his age\n";
				penguins.remove(i);
				numberOfPenguins--;
				i--;
			} else {
				penguins.get(i).setHappiness(penguins.get(i).getHappiness()-(int)(Math.random()*20+1));
				if(penguins.get(i).getHappiness()<=0){
					PrintAllDead+=penguins.get(i).getName()+" is Dead because of his Sadness\n";
					penguins.remove(i);
					numberOfPenguins--;
					i--;
				}
			}
		}
		return PrintAllDead;
	}
}