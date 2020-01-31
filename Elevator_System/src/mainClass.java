import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mainClass {
	List<ElevatorSystem> elevators;
	Floor floors; 
	Scheduler scheduler;
	ElevatorSystem elevator1;
	public static void main (String[] args) {
		List<ElevatorSystem> elevators = new ArrayList<ElevatorSystem>();
		ElevatorSystem elevator1 = new ElevatorSystem(1);
		elevators.add(elevator1);
		Scheduler scheduler = new Scheduler(elevators);
		Floor floors = new Floor(1, scheduler); //Each floor needs a thread? 
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println("Please enter command.");
			String command = input.next();
			if (command.equals("quit")) {
				System.out.println("quitting...");
				break;
			}
			//TODO: Parse the command
		}
		
	}

}
