package ZooManagementSystem.app;

import ZooManagementSystem.animals.Penguin;
import ZooManagementSystem.common.Colour;
import ZooManagementSystem.common.Pattern;
import ZooManagementSystem.data.AnimalRepository;
import ZooManagementSystem.data.EnclosureRepository;
import ZooManagementSystem.data.FeedingScheduleRepository;
import ZooManagementSystem.data.MedicalRecordRepository;
import ZooManagementSystem.data.TicketReportRepository;
import ZooManagementSystem.data.WorkerAssignmentRepository;
import ZooManagementSystem.exceptions.AgeException;
import ZooManagementSystem.exceptions.HeightException;
import ZooManagementSystem.models.AnimalRecord;
import ZooManagementSystem.models.FeedingTask;
import ZooManagementSystem.models.MedicalRecord;
import ZooManagementSystem.models.RevenueReport;
import ZooManagementSystem.models.WorkerAssignment;
import ZooManagementSystem.services.AnimalService;
import ZooManagementSystem.services.FeedingService;
import ZooManagementSystem.services.MedicalHistoryService;
import ZooManagementSystem.services.TicketReportService;
import ZooManagementSystem.services.WorkerAssignmentService;
import ZooManagementSystem.tickets.VisitorsMangamentSys;
import ZooManagementSystem.zoo.Zoo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static ZooManagementSystem.common.Pattern.*;
import static ZooManagementSystem.zoo.Zoo.*;

public class Main {
    private static int choice;
    

    public static void main(String[] args) throws HeightException, AgeException {
        Zoo zoo = new Zoo();
        zoo.fetchData(); // Fetch data from the database
        Scanner input= new Scanner(System.in);
        printMenu();
        System.out.println("Enter your option:");
        choice = input.nextInt();
        input.nextLine();
        while(choice!=15) {
            switch(choice) {
                case 1:
                    System.out.println(zooDetails());
                    break;
                case 2:
                    System.out.println(printPenguins());;
                    break;
                case 3:
                    add_penguin(zoo);
                    break;
                case 4:
                    System.out.println("All carnivorous animals: \n");
                    System.out.println(printLions());
                    System.out.println(printTigers());
					System.out.println(printLynxes());
                    break;
                case 5:
                    AddCarnivorousAnimal(zoo);
                    break;
                case 6:
                    System.out.println(printFishes());
                    System.out.println(MostPopularFishColour());
                    break;
                case 7:
                    add_new_fishes();
                    break;
                case 8:
                    System.out.println("The Lions ate: "+zoo.Feed_lions()+"kg of meat.");
                    System.out.println("The Tigers age: "+zoo.Feed_tigers()+"kg of meat.");
					System.out.println("The Lynxes ate: "+zoo.Feed_lynxes()+"kg of meat.");
                    System.out.println("The Fishes ate: "+ String.format("%.2f",zoo.Feed_Fishes()) +" meals.\n");
                    System.out.println("The Penguins ate : "+zoo.Feed_penguins()+" Fishes.\n");
                    break;
                case 9:
                    System.out.println(zoo.ListentoAllAnimalsinZoo());
                    break;
                case 10:
                    System.out.println(zoo.ageOneYearAll());
                    System.out.println("All Other animals have been Aged one year\n");
                    break;
                case 11:
                    SortPenguins();
                    break;
                case 12:
                    System.out.println(printDogs());
                    break;
                case 13:
                    AddNewDog(zoo);
                    break;
                case 14:
                    // calling the other system function in the main all other things will be in the class itself
                    ConnectVMS();
                    break;
                case 15:
                    System.out.println("Thank You!\nHave a nice day:)");
                    break;
                case 16:
                    animalDatabaseMenu(input);
                    break;
                case 17:
                    assignWorker(input);
                    break;
                case 18:
                    scheduleFeeding(input);
                    break;
                case 19:
                    completeFeeding(input);
                    break;
                case 20:
                    addMedicalRecord(input);
                    break;
                case 21:
                    showMedicalHistory(input);
                    break;
                case 22:
                    printRevenueReport(input);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
            printMenu();
            System.out.println("Enter your option:");
            choice=input.nextInt();
            input.nextLine();
        }
      //  return 0;
    }

    private static void printMenu() {
        System.out.println("\n1)Show The Zoo Details & Number of pets in it\n"
                + "2)Show all Penguins in the zoo\n"
                + "3)Add Penguin\n"
                + "4)Show all the carnivorous animals\n"
                + "5)Add a new carnivorous animal\n"
                + "6)Show all Fishies\n"
                + "7)Add Fishies to the Aquarium\n"
                + "8)Feed all pets in the zoo\n"
                + "9)Listen to all Animals in the Zoo\n"
                + "10)Make All Animals in the Zoo age One Year\n"
                + "11)Choose a way to sort the Penguins\n"
                + "12)Show all Dogs in the Zoo\n"
                + "13)Add a new Dog\n"
                + "14)Visitors Management System\n"
                + "15)Exit\n"
                + "16)Animal Database Management\n"
                + "17)Assign Worker\n"
                + "18)Schedule Feeding\n"
                + "19)Complete Feeding\n"
                + "20)Add Medical Record\n"
                + "21)Show Medical History\n"
                + "22)Ticket Revenue Report\n"
        );
    }

    private static void AddNewDog(Zoo zoo) {
        Scanner input_dog = new Scanner(System.in);
        System.out.println("Enter the Name of the new Dog: ");
        String name= input_dog.nextLine();
        System.out.println("Enter the Weight of the new Dog: ");
        double weight= input_dog.nextDouble();
        while (weight<=0){
            System.out.println("The Weight of the Dog Can't be Negative\n" +
                    "Please Re enter a new weight: ");
            weight=input_dog.nextDouble();
        }
        System.out.println("Enter the age of the new Dog: ");
        int age_d= input_dog.nextInt();
        while (age_d<=0 || age_d>=60){
            System.out.println("The Age of a dog is between 1-60\n" +
                    "Please re Enter a new responsible age: ");
            age_d=input_dog.nextInt();
        }
        System.out.println("Enter the Gender of the new Dog:"+"\n1)Male.\n2)Female. (Type 1 or 2)");
        int animal_gender = input_dog.nextInt();
        System.out.println("Enter the Type of the new Dog:"+"\n1)Akita.\n2)Bulldog.\n3)Poodle.\n4)Terriers.");
        int dogtype = input_dog.nextInt();
        zoo.AddNewDog(name,age_d,weight,dogtype,animal_gender);
    }

    public static void add_penguin(Zoo zoo) throws HeightException, AgeException{
        System.out.println("Enter the Name of the new penguin: ");
        Scanner input_Penguin = new Scanner(System.in);
        String name_p = input_Penguin.nextLine();
        System.out.println("Enter the height of the new penguin: ");
        double height_p = input_Penguin.nextDouble();
        System.out.println("Enter the age of the new penguin: ");
        int age_p = input_Penguin.nextInt();
        System.out.println(zoo.Add_New_Penguin(name_p,height_p,age_p));
    }

    public static void AddCarnivorousAnimal(Zoo zoo){
        System.out.println("Which carnivorous animal you want to add?\n");
        System.out.println("lion/tiger/lynx(Type the requested aninmal!)");
        int animal_choice = -1;
        Scanner input_carnivorous = new Scanner(System.in);
        String requested_animal= input_carnivorous.nextLine();
        if(requested_animal.equals("lion"))
            animal_choice=1;
        else if (requested_animal.equals("tiger"))
        	animal_choice=2;
        else if(requested_animal.equals("lynx"))
        	animal_choice=3;
        System.out.println("Enter the Name of the new "+requested_animal+": ");
        String name_animal= input_carnivorous.nextLine();
        System.out.println("Enter the Weight of the new "+requested_animal+": ");
        double weight_animal= input_carnivorous.nextDouble();
        while (weight_animal<=0){
            System.out.println("The Weight of "+requested_animal+"Can't be Negative\n" +
                    "Please Re enter a new weight: ");
            weight_animal=input_carnivorous.nextDouble();
        }
        System.out.println("Enter the age of the new "+requested_animal+": ");
        int age_animal= input_carnivorous.nextInt();
        while (age_animal<=0 || age_animal>=60){
            System.out.println("The Age of a carnivorous animal is between 1-60\n" +
                    "Please re Enter a new responsible age: ");
            age_animal=input_carnivorous.nextInt();
        }
        System.out.println("Enter the Gender of the new "+requested_animal+":"+"\n1)Male.\n2)Female. (Type 1 or 2)");
        int animal_gender = input_carnivorous.nextInt();
        if(animal_choice == 1)
            zoo.AddNewLion(name_animal,weight_animal,age_animal,animal_gender);
        else if(animal_choice == 2)
            zoo.AddNewTiger(name_animal,weight_animal,age_animal,animal_gender);
        else
        	zoo.AddNewLynx(name_animal, weight_animal, age_animal, animal_gender);
    }

    public static void add_new_fishes(){
        System.out.println("Choose an option:\n1)Add Fish With Details\n2)Add a number of random Fishes\n");
        Pattern Pattern_fish=null;
        Scanner input_choice = new Scanner(System.in);
        int choice_of_adding = input_choice.nextInt();
        if(choice_of_adding==1){
            System.out.println("What type of Fish would you like to add?\n1.Fish\n2.GoldFish\n3.ClownFish");
            int type_f = input_choice.nextInt();
        	System.out.println("Enter the Age of the Fish:");
            int age_f=input_choice.nextInt();
            while(age_f<=0 || age_f>15){
                System.out.println("Fish as they know on average lives between 10-15 years\n" +
                        "Please Re enter a new responsible age: ");
                age_f=input_choice.nextInt();
            }
            System.out.println("Enter the Length of the Fish:");
            double length_f=input_choice.nextDouble();
            while (length_f<=0){
                System.out.println("The length of a fish can't be Negative\n" +
                        "Please Re enter a new fish length: ");
                length_f=input_choice.nextDouble();
            }
            if(type_f == 2) {
                AddNewFish(type_f, age_f, length_f, null, null);
            	System.out.println("The GoldFish was successfully added");
            	return;
            }
            if(type_f == 3) {
                AddNewFish(type_f, age_f, length_f, null, null);
            	System.out.println("The ClownFish was successfully added");
            	return;
            }
            System.out.println("Enter the Pattern of the Fish: (Type one of these Patterns: SPOTS,STRIPES,CLEAR,DOTS )");
            input_choice.skip("[\r\n]+"); // To skip the input of the next line char
            String pattern = input_choice.nextLine();
            if (pattern.equals("DOTS")) {
                Pattern_fish= DOTS;
            }
            else if (pattern.equals("SPOTS")) {
                Pattern_fish=SPOTS;
            } else if (pattern.equals("STRIPES")) {
                Pattern_fish=STRIPES;
            } else if (pattern.equals("CLEAR")) {
                Pattern_fish=CLEAR;
            }
            Colour[] allColours = Colour.values();
            System.out.println("How many Colors is the Fish?\n");
            int number_of_colors= input_choice.nextInt();
            Colour[] singularFishColours = new Colour[number_of_colors];
            for(int i = 0; i<number_of_colors; i++) {
                boolean was_added=false;
                System.out.println("Choose the Colors :\n1)BLACK, 2)WHITE, 3)GREEN, 4)ORANGE, 5)BLUE, 6)YELLOW, 7)BROWN, 8)GOLD, 9)RED, 10)CYAN");
                int color_num = input_choice.nextInt();
                for(int j=0;singularFishColours[j]!=null;j++) {
                    if(singularFishColours[j].equals(allColours[color_num])) {
                        System.out.println("This colour was already added!");
                        was_added=true;
                        i--;
                        break;
                    }
                }
                if(!was_added)
                    singularFishColours[i] = allColours[color_num];
                was_added=false;
            }
            AddNewFish(type_f,age_f,length_f,singularFishColours,Pattern_fish);
            System.out.println("The Fish was successfully added");
        } else if (choice_of_adding==2) {
            System.out.println("How much Fishes you want to add?\n");
            int num_fishes= input_choice.nextInt();
            addFish(num_fishes);
            System.out.println(num_fishes+" new Fishes were Added Successfully\n");
        }
    }

    private static void animalDatabaseMenu(Scanner input) {
        System.out.println("\nAnimal Database Management\n"
                + "1) Add animal\n"
                + "2) Show animal by ID\n"
                + "3) Show all animals\n"
                + "4) Update animal\n"
                + "5) Delete animal\n"
                + "6) Back\n"
        );
        int animalChoice = readInt(input, "Enter your option:");
        switch (animalChoice) {
            case 1:
                createDatabaseAnimal(input);
                break;
            case 2:
                showDatabaseAnimal(input);
                break;
            case 3:
                showAllDatabaseAnimals();
                break;
            case 4:
                updateDatabaseAnimal(input);
                break;
            case 5:
                deleteDatabaseAnimal(input);
                break;
            case 6:
                break;
            default:
                System.out.println("Invalid animal database option.");
        }
    }

    private static void createDatabaseAnimal(Scanner input) {
        runDatabaseAction(() -> {
            AnimalService animalService = new AnimalService(new AnimalRepository(), new EnclosureRepository());
            AnimalRecord animal = readAnimalRecord(input, 0);
            int animalID = animalService.createAnimal(animal);
            System.out.println("Animal saved with ID: " + animalID);
        });
    }

    private static void showDatabaseAnimal(Scanner input) {
        runDatabaseAction(() -> {
            int animalID = readInt(input, "Enter animal ID:");
            AnimalRepository animalRepository = new AnimalRepository();
            AnimalRecord animal = animalRepository.findById(animalID).orElse(null);
            if (animal == null) {
                System.out.println("Animal was not found.");
            } else {
                printAnimalRecord(animal);
            }
        });
    }

    private static void showAllDatabaseAnimals() {
        runDatabaseAction(() -> {
            AnimalRepository animalRepository = new AnimalRepository();
            java.util.List<AnimalRecord> animals = animalRepository.findAll();
            if (animals.isEmpty()) {
                System.out.println("No animals found in the database.");
                return;
            }
            for (AnimalRecord animal : animals) {
                printAnimalRecord(animal);
            }
        });
    }

    private static void updateDatabaseAnimal(Scanner input) {
        runDatabaseAction(() -> {
            int animalID = readInt(input, "Enter animal ID to update:");
            AnimalService animalService = new AnimalService(new AnimalRepository(), new EnclosureRepository());
            AnimalRecord animal = readAnimalRecord(input, animalID);
            if (animalService.updateAnimal(animal)) {
                System.out.println("Animal updated.");
            } else {
                System.out.println("Animal was not found.");
            }
        });
    }

    private static void deleteDatabaseAnimal(Scanner input) {
        runDatabaseAction(() -> {
            int animalID = readInt(input, "Enter animal ID to delete:");
            AnimalService animalService = new AnimalService(new AnimalRepository(), new EnclosureRepository());
            if (animalService.deleteAnimal(animalID)) {
                System.out.println("Animal deleted.");
            } else {
                System.out.println("Animal was not found.");
            }
        });
    }

    private static AnimalRecord readAnimalRecord(Scanner input, int animalID) {
        String species = readText(input, "Enter species:");
        String name = readText(input, "Enter name:");
        int age = readInt(input, "Enter age:");
        String healthStatus = readText(input, "Enter health status:");
        String habitatType = readText(input, "Enter habitat type:");
        int zooID = readInt(input, "Enter zoo ID:");
        Integer enclosureID = readOptionalInt(input, "Enter enclosure ID (0 for none):");
        return new AnimalRecord(animalID, species, name, age, healthStatus, habitatType, zooID, enclosureID);
    }

    private static void assignWorker(Scanner input) {
        runDatabaseAction(() -> {
            WorkerAssignmentService assignmentService = new WorkerAssignmentService(new WorkerAssignmentRepository());
            int workerID = readInt(input, "Enter worker ID:");
            Integer animalID = readOptionalInt(input, "Enter animal ID (0 for none):");
            Integer enclosureID = readOptionalInt(input, "Enter enclosure ID (0 for none):");
            String responsibility = readText(input, "Enter responsibility:");
            LocalDate startDate = readDate(input, "Enter start date (yyyy-MM-dd):");
            String endDateText = readText(input, "Enter end date (yyyy-MM-dd, blank for active):");
            LocalDate endDate = endDateText.isBlank() ? null : LocalDate.parse(endDateText);
            int assignmentID = assignmentService.assignWorker(new WorkerAssignment(0, workerID, animalID, enclosureID, responsibility, startDate, endDate));
            System.out.println("Worker assignment saved with ID: " + assignmentID);
        });
    }

    private static void scheduleFeeding(Scanner input) {
        runDatabaseAction(() -> {
            FeedingService feedingService = new FeedingService(new FeedingScheduleRepository());
            Integer animalID = readOptionalInt(input, "Enter animal ID (0 for none):");
            Integer enclosureID = readOptionalInt(input, "Enter enclosure ID (0 for none):");
            LocalDateTime scheduledAt = readDateTime(input, "Enter scheduled time (yyyy-MM-dd HH:mm):");
            String foodType = readText(input, "Enter food type:");
            String amount = readText(input, "Enter amount:");
            int feedingID = feedingService.scheduleFeeding(new FeedingTask(0, animalID, enclosureID, scheduledAt, foodType, amount, false, null, null));
            System.out.println("Feeding scheduled with ID: " + feedingID);
        });
    }

    private static void completeFeeding(Scanner input) {
        runDatabaseAction(() -> {
            FeedingService feedingService = new FeedingService(new FeedingScheduleRepository());
            int feedingID = readInt(input, "Enter feeding ID:");
            int workerID = readInt(input, "Enter worker ID:");
            if (feedingService.completeFeeding(feedingID, workerID)) {
                System.out.println("Feeding marked as completed.");
            } else {
                System.out.println("Feeding was not found or was already completed.");
            }
        });
    }

    private static void addMedicalRecord(Scanner input) {
        runDatabaseAction(() -> {
            MedicalHistoryService medicalHistoryService = new MedicalHistoryService(new MedicalRecordRepository());
            int animalID = readInt(input, "Enter animal ID:");
            LocalDate visitDate = readDate(input, "Enter visit date (yyyy-MM-dd):");
            String diagnosis = readText(input, "Enter diagnosis:");
            String treatment = readText(input, "Enter treatment:");
            String veterinarian = readText(input, "Enter veterinarian:");
            String notes = readText(input, "Enter notes:");
            int recordID = medicalHistoryService.addRecord(new MedicalRecord(0, animalID, visitDate, diagnosis, treatment, veterinarian, notes));
            System.out.println("Medical record saved with ID: " + recordID);
        });
    }

    private static void showMedicalHistory(Scanner input) {
        runDatabaseAction(() -> {
            MedicalHistoryService medicalHistoryService = new MedicalHistoryService(new MedicalRecordRepository());
            int animalID = readInt(input, "Enter animal ID:");
            java.util.List<MedicalRecord> records = medicalHistoryService.historyForAnimal(animalID);
            if (records.isEmpty()) {
                System.out.println("No medical records found for this animal.");
                return;
            }
            for (MedicalRecord record : records) {
                System.out.println("Record #" + record.getRecordID()
                        + " | animal=" + record.getAnimalID()
                        + " | visitDate=" + record.getVisitDate()
                        + " | diagnosis=" + record.getDiagnosis()
                        + " | treatment=" + record.getTreatment()
                        + " | veterinarian=" + record.getVeterinarian()
                        + " | notes=" + record.getNotes());
            }
        });
    }

    private static void printRevenueReport(Scanner input) {
        runDatabaseAction(() -> {
            TicketReportService reportService = new TicketReportService(new TicketReportRepository());
            LocalDate fromDate = readDate(input, "Enter start date (yyyy-MM-dd):");
            LocalDate toDate = readDate(input, "Enter end date (yyyy-MM-dd):");
            RevenueReport report = reportService.revenueBetween(fromDate, toDate);
            System.out.println("Revenue Report");
            System.out.println("From: " + report.getFromDate());
            System.out.println("To: " + report.getToDate());
            System.out.println("Tickets sold: " + report.getTicketsSold());
            System.out.println("Total revenue: " + report.getTotalRevenue());
        });
    }

    private static void printAnimalRecord(AnimalRecord animal) {
        System.out.println("Animal #" + animal.getAnimalID()
                + " | species=" + animal.getSpecies()
                + " | name=" + animal.getName()
                + " | age=" + animal.getAge()
                + " | health=" + animal.getHealthStatus()
                + " | habitat=" + animal.getHabitatType()
                + " | zooID=" + animal.getZooID()
                + " | enclosureID=" + (animal.getEnclosureID() == null ? "none" : animal.getEnclosureID()));
    }

    private static int readInt(Scanner input, String prompt) {
        System.out.println(prompt);
        int value = input.nextInt();
        input.nextLine();
        return value;
    }

    private static Integer readOptionalInt(Scanner input, String prompt) {
        int value = readInt(input, prompt);
        return value == 0 ? null : value;
    }

    private static String readText(Scanner input, String prompt) {
        System.out.println(prompt);
        return input.nextLine();
    }

    private static LocalDate readDate(Scanner input, String prompt) {
        return LocalDate.parse(readText(input, prompt));
    }

    private static LocalDateTime readDateTime(Scanner input, String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(readText(input, prompt), formatter);
    }

    private static void runDatabaseAction(DatabaseAction action) {
        try {
            action.run();
        } catch (java.sql.SQLException e) {
            System.out.println("Database action failed. Make sure MySQL is running and DataBase.sql/sample.sql were loaded.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Action failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Database action failed: " + e.getMessage());
        }
    }

    private interface DatabaseAction {
        void run() throws Exception;
    }

    public static void SortPenguins(){
        System.out.println("Which way would you like to sort the penguins\n" +
                "1)By Name in Ascending Order\n" +
                "2)By Height in Descending Order\n" +
                "3)By Age in Ascending Order\n");
        Scanner in = new Scanner(System.in);
        int waytosort= in.nextInt();
        if(waytosort==1){
            Penguin.SortByName(getPenguins());
            Penguin.setLastSortWayused(1);
            System.out.println("Penguins now sorted By Name in Ascending Order\n");
        } else if (waytosort==2) {
            Penguin.SortByHeight(getPenguins());
            Penguin.setLastSortWayused(2);
            System.out.println("Penguins now sorted By Height in Descending Order\n\n");
        } else if (waytosort==3) {
            Penguin.SortByAge(getPenguins());
            Penguin.setLastSortWayused(3);
            System.out.println("Penguins now sorted By Age in Ascending Order\n");
        }
    }
    
    public static double heightExceptionPenguin() {
    	double height_p = -1;
    	Scanner input_Penguin = new Scanner(System.in);
    	while(height_p < 1 || height_p > 200) {
    	System.out.println("Height of the new Penguin is illegal (choose a number between 1-"+ Zoo.getPenguins().get(0).getHeight()+" (inclusive))\nPlease re-Enter the height of the new penguin: ");
    	height_p = input_Penguin.nextDouble();
    	}
    	return height_p;
    }
    
    public static int ageExceptionPenguin() {
    	int age_p = -1;
    	Scanner input_Penguin = new Scanner(System.in);
    	while(age_p < 1 || age_p > 20) {
    	System.out.println("Age of the penguin is illegal (choose a number 1-20 (inclusive)),\nPlease re-Enter the age of the new penguin: ");
        age_p = input_Penguin.nextInt();
    	}
        return age_p;
    }

    public static void ConnectVMS(){

        String user;
        String pass;
        Scanner input_worker = new Scanner(System.in);
        System.out.println("PLease Enter the worker USERNAME: ");
        user = input_worker.nextLine();
        System.out.println("Please Enter the Worker password: ");
        pass = input_worker.nextLine();
        VisitorsMangamentSys VisitorSys;
        if (Zoo.ConnectionToVMSCheck(user,pass)){
            System.out.println("Connected Successfully\n");
            VisitorSys = new VisitorsMangamentSys();
        }
        else
            System.out.println("Wrong User OR Password\n");
    }
}
