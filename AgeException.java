package ZooManagementSystem;

public class AgeException extends Exception {
	public AgeException() {
		super();
	}
	public AgeException(String message) {
		super(message);
	}
	
	public void AgeValidator(int age) throws AgeException {
		if(!(age >= 1 && age <= 20)) 
			throw new AgeException("Age of the penguin is illegal (please choose a number 1-20 (inclusive))");
	}
	
	
}
