import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Main class to simulate the elevator
 * @author zheji
 *
 */
public class mainClass {
	
	public static void main (String[] args) {
		ElevatorSystem elevator = new ElevatorSystem(0);
		List<ElevatorSystem> elevators = new ArrayList<ElevatorSystem>();
		elevators.add(elevator);
		Scheduler scheduler = new Scheduler(elevators);
		CSVParser parser = new CSVParser("CSVfile");
		Building build = new Building(10, scheduler);
		build.parseRequest(parser.getRequests());
	}


}
