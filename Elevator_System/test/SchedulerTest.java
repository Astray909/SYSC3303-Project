import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SchedulerTest {

	Scheduler scheduler;
	List<ElevatorSystem> elevators;
	ElevatorSystem elevator1;
	
	@BeforeEach
	void setUp() {
		elevators = new ArrayList<ElevatorSystem>();
		elevator1 = new ElevatorSystem(1);
		elevators.add(elevator1);
		scheduler = new Scheduler(elevators);
	}
	
	@Test
	void testGetRequest() {
		Request request = new Request(0, true);
		scheduler.getRequest(request);
		assertEquals(elevator1.getTestRequest(), request);
	}
}
