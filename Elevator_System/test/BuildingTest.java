import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * @Author Hassan Yusuf 101053489
 * Tests public methods of Building class
 */
class BuildingTest {
	
	Scheduler scheduler;
	List<ElevatorSystem> elevators;
	ElevatorSystem elevator1;
	Building building;

	@BeforeEach
	void setUp() {
		elevators = new ArrayList<ElevatorSystem>();
		elevator1 = new ElevatorSystem(1);
		elevators.add(elevator1);
		scheduler = new Scheduler(elevators);
		building = new Building(5, scheduler);
	}
	
	@Test
	void test() {
		
	}

}
