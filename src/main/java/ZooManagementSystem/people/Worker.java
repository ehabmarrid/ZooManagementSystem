package ZooManagementSystem.people;

import ZooManagementSystem.data.WorkersData;
import ZooManagementSystem.zoo.Zoo;

public class Worker extends Human{
    private int WorkerNumber;
    private static String Username;


    public Worker(int workerNumber, int workerID, String firstName, String lastName, String birthDay, int phoneNumber, String username) {
        super(workerID,firstName,lastName,birthDay,phoneNumber);
        this.WorkerNumber = workerNumber;
        this.Username = username;
    }

    public static boolean ConnectToVMS(String username, String Password){
        for(int i=0;i< Zoo.getNumberOfWorkers();i++){
            WorkersData credentials = Zoo.getWorkerCredentials().get(i);
            if (Username.equals(username) && credentials.matchesPassword(Password))
                return true;
        }
        return false;
    }


}
