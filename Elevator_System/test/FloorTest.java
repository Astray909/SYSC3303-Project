import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * @Author Hassan Yusuf 101053489
 * Tests public methods of Floor class
 */
public class FloorTest {
	
	Floor floor;
	Scheduler scheduler;
	ElevatorSystem elevator1;
	
	@BeforeEach
	void setUp() {
		List<ElevatorSystem> elevators = new ArrayList<ElevatorSystem>();
		elevator1 = new ElevatorSystem(1);
		elevators.add(elevator1);
		scheduler = new Scheduler(elevators);
		floor = new Floor(1, scheduler);
	}
	
	@AfterEach
	void tearDown() {
		scheduler.getSchedulerSocket().close();
	}
	
	@Test
	void testGenerateRequest() {
		Request request = floor.generateRequest();
		assertEquals(request.getSource(), 1);
	}

	@Test
	void testSendRequest() {
		Request request = new Request(1, true, 2, "10:20:10");
		floor.sendRequest(request);
		scheduler.sendAndReceive();
		elevator1.sendAndReceive();
		assertTrue(elevator1.getTestRequest().equals(request));
	}
}
