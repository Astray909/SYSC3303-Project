import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * @Author Hassan Yusuf 101053489
 * Tests public methods of Floor class
 */
public class FloorTest {
	
	Floor floor;
	Scheduler scheduler;
	
	@BeforeEach
	void setUp() {
		List<ElevatorSystem> elevators = new ArrayList<ElevatorSystem>();
		ElevatorSystem elevator1 = new ElevatorSystem(1);
		elevators.add(elevator1);
		scheduler = new Scheduler(elevators);
		floor = new Floor(1, scheduler);
	}
	
	@Test
	void testGenerateRequest() {
		Request request = floor.generateRequest();
		assertEquals(request.getSource(), 1);
	}

	@Test
	void testRun() {
		//cannot be tested as run is an infinite while loop
	}
}
