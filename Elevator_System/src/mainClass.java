import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Main class to simulate the elevator
 * @author zheji
 *
 */
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
		Building building = new Building(3, scheduler);
		Scanner input = new Scanner(System.in);
		
		building.startFloors();
		
		while (true) {
			System.out.println("Please enter command.");
			String command = input.next();
			if (command.equals("quit")) {
				System.out.println("quitting...");
				break;
			}
			//TODO: Parse the command
			int sourceFloor = Integer.valueOf((command.split("go"))[0]);
			int destFloor = Integer.valueOf((command.split("go"))[1]);
			Request request = new Request(sourceFloor, sourceFloor>destFloor);
			
		}
		
	}

}
