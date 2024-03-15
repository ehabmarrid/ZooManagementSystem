package ZooManagementSystem;

public class HeightException extends Exception{

	
	public HeightException() {
		super();
	}
	
	public HeightException(String message) {
		super(message);
	}
	
	
	
	public void HeightIsIllegal(double height) throws HeightException {
		if(height > Zoo.penguins.get(0).getHeight())
			throw new HeightException("Height is taller than the leader of the flock! ("+ Zoo.penguins.get(0).getHeight()+")");
		
		if(!(height >= 1 && height <= Zoo.penguins.get(0).getHeight()))
			throw new HeightException("Height of the new Penguin is illegal (Please choose a number between 1-"+ Zoo.penguins.get(0).getHeight()+" (inclusive))");
		
	}
	


}
